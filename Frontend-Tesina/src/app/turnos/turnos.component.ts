import { NgClass } from '@angular/common';
import { Component, inject } from '@angular/core';
import { TurnosService } from '../services/turnos.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-turnos',
  imports: [NgClass],
  templateUrl: './turnos.component.html',
  styleUrl: './turnos.component.css',
})
export class TurnosComponent {
  private service = inject(TurnosService);

  turnos: any[] = [];
  turnoSeleccionado: any = null;

  obtenerProximosDias(cantidad: number = 5): string[] {
    const dias: string[] = [];
    const hoy = new Date();
    for (let i = 0; i < cantidad; i++) {
      const dia = new Date(hoy);
      dia.setDate(hoy.getDate() + i);

      // Formato legible: Lunes 15/07
      const formato = dia.toLocaleDateString('es-AR', {
        weekday: 'long',
      });

      dias.push(formato);
    }
    return dias;
  }

  ngOnInit(): void {
    this.service.getAllTurnos().subscribe((turnos) => {
      this.turnos = turnos;
    });
  }

  async seleccionarTurno(turno: any) {
    const result = await Swal.fire({
      title: '¿Confirmar turno?',
      text: `¿Desea reservar el turno para el día ${turno.dia} en el horario ${turno.hora}?`,
      icon: 'question',
      showCancelButton: true,
      confirmButtonText: 'Sí, confirmar',
      cancelButtonText: 'Cancelar',
    });

    if (result.isConfirmed) {
      // Setear los valores requeridos antes de enviar
      const [horaInicioStr, horaFinStr] = turno.hora.split(' - ');
      const fecha = new Date();
      // Suponiendo que turno.dia es el nombre del día (ej: "lunes")
      // y queremos la fecha correspondiente al próximo día con ese nombre
      const diasSemana = [
        'domingo',
        'lunes',
        'martes',
        'miércoles',
        'jueves',
        'viernes',
        'sábado',
      ];
      const hoy = new Date();
      let diaIndex = diasSemana.indexOf(turno.dia.toLowerCase());
      if (diaIndex === -1) diaIndex = hoy.getDay();
      let diasHasta = (diaIndex - hoy.getDay() + 7) % 7;
      const fechaTurno = new Date(hoy);
      fechaTurno.setDate(hoy.getDate() + diasHasta);

      function parseHora(hora: string) {
        const [h, m] = hora.split(':').map(Number);
        const d = new Date(fechaTurno);
        d.setHours(h, m, 0, 0);
        return d.toISOString();
      }

      const emailUser = localStorage.getItem('email') || '';
      const serviceIdStr = localStorage.getItem('serviceId') || '1';
      const serviceId = parseInt(serviceIdStr, 10);

      const turnoAEnviar = {
        id: null,
        horaInicio: parseHora(horaInicioStr),
        horaFin: parseHora(horaFinStr),
        estado: 'pendiente',
        emailUser: emailUser,
        serviceId: serviceId,
      };

      this.service.postTurno(turnoAEnviar).subscribe({
        next: () => {
          this.turnoSeleccionado = turnoAEnviar;
          Swal.fire(
            'Turno reservado',
            'Su turno ha sido seleccionado.',
            'success'
          );
          // Actualizar la lista de turnos después de reservar
          this.service.getAllTurnos().subscribe((turnos) => {
            this.turnos = turnos;
          });
        },
        error: () => {
          Swal.fire('Error', 'No se pudo reservar el turno.', 'error');
        },
      });
    }
  }

  // Devuelve el estado del turno para un día y horario específico
  getEstadoTurno(dia: string, hora: string): 'lleno' | 'libre' {
    // Buscar si existe un turno reservado para ese día y horario
    const diasSemana = [
      'domingo',
      'lunes',
      'martes',
      'miércoles',
      'jueves',
      'viernes',
      'sábado',
    ];
    // Buscar la fecha correspondiente al día
    const hoy = new Date();
    let diaIndex = diasSemana.indexOf(dia.toLowerCase());
    if (diaIndex === -1) diaIndex = hoy.getDay();
    let diasHasta = (diaIndex - hoy.getDay() + 7) % 7;
    const fechaTurno = new Date(hoy);
    fechaTurno.setDate(hoy.getDate() + diasHasta);

    // Parsear hora inicio y fin
    const [horaInicioStr, horaFinStr] = hora.split(' - ');
    function parseHora(hora: string) {
      const [h, m] = hora.split(':').map(Number);
      const d = new Date(fechaTurno);
      d.setHours(h, m, 0, 0);
      return d.toISOString();
    }
    const horaInicio = parseHora(horaInicioStr);

    // Buscar si hay un turno reservado para ese horario
    const ocupado = this.turnos.some(
      (t) =>
        t.horaInicio &&
        t.horaInicio.substring(0, 16) === horaInicio.substring(0, 16) &&
        t.estado !== 'cancelado'
    );
    return ocupado ? 'lleno' : 'libre';
  }
}
