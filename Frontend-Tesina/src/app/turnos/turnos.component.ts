import { CommonModule, NgClass } from '@angular/common';
import { Component, inject } from '@angular/core';
import { TurnosService } from '../services/turnos.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-turnos',
  imports: [NgClass, CommonModule],
  templateUrl: './turnos.component.html',
  styleUrl: './turnos.component.css',
})
export class TurnosComponent {
  private service = inject(TurnosService);

  turnos: any[] = [];
  turnoSeleccionado: any = null;
  isAdmin: boolean = false;
  isUser: boolean = false;

  ngOnInit(): void {
    this.service.getAllTurnos().subscribe((turnos) => {
      this.turnos = turnos;
    });
    const rol = (localStorage.getItem('role') || '').toUpperCase();
    this.isAdmin = rol === 'ADMIN' || rol === 'SUPERADMIN';
    this.isUser = rol === 'USER';
    // Si el usuario es USER y no hay turnoSeleccionado, inicializa con un objeto vacío para mostrar el botón
    if (this.isUser && !this.turnoSeleccionado) {
      this.turnoSeleccionado = {};
    }
  }

  obtenerProximosDias(cantidad: number = 6): string[] {
    const dias: string[] = [];
    const hoy = new Date();
    for (let i = 0; i < cantidad; i++) {
      const dia = new Date(hoy);
      dia.setDate(hoy.getDate() + i);
      const formato = dia.toLocaleDateString('es-AR', {
        weekday: 'long',
      });
      dias.push(formato);
    }
    return dias;
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
      const [horaInicioStr, horaFinStr] = turno.hora.split(' - ');
      const fechaTurnoStr = turno.fecha; // formato YYYY-MM-DD

      function parseHora(hora: string) {
        const [h, m] = hora.split(':').map(Number);
        const [anio, mes, dia] = fechaTurnoStr.split('-').map(Number);
        const d = new Date(anio, mes - 1, dia, h, m, 0, 0);
        const yyyy = d.getFullYear();
        const mm = String(d.getMonth() + 1).padStart(2, '0');
        const dd = String(d.getDate()).padStart(2, '0');
        const hh = String(d.getHours()).padStart(2, '0');
        const min = String(d.getMinutes()).padStart(2, '0');
        return `${yyyy}-${mm}-${dd}T${hh}:${min}:00`;
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
        fecha: fechaTurnoStr, // Guarda la fecha exacta
      };

      this.service.postTurno(turnoAEnviar).subscribe({
        next: () => {
          this.turnoSeleccionado = {
            ...turnoAEnviar,
            dia: turno.dia,
            hora: turno.hora,
            fecha: fechaTurnoStr, // Guarda la fecha exacta para comparar en el HTML
          };
          Swal.fire(
            'Turno reservado',
            'Su turno ha sido seleccionado.',
            'success'
          );
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

  // Cambia la firma para recibir fecha y horaInicio
  getEstadoTurno(fecha: string, horaInicio: string): 'lleno' | 'libre' {
    const turno = this.turnos.find(
      (t) => t.fecha === fecha && t.horaInicio === horaInicio
    );
    if (turno) {
      return turno.lugaresLibres === 0 || turno.lugaresLibres < 0
        ? 'lleno'
        : 'libre';
    }
    return 'libre';
  }

  isTurnoLleno(fecha: string, horaInicio: string): boolean {
    // Normaliza la hora a formato HH:mm (soporta '9:00', '09:00', etc)
    let [h, m] = horaInicio.split(':').map(Number);
    if (isNaN(h)) return false;
    if (isNaN(m)) m = 0;
    const horaNorm = `${h.toString().padStart(2, '0')}:${m
      .toString()
      .padStart(2, '0')}`;
    const turno = this.turnos.find(
      (t) =>
        t.fecha === fecha &&
        (t.horaInicio === horaNorm || t.horaInicio === horaInicio) // por si el backend ya lo manda igual
    );
    return !!turno && turno.lugaresLibres <= 0;
  }

  getFechaByIndex(index: number): string {
    const hoy = new Date();
    hoy.setDate(hoy.getDate() + index);
    return hoy.toISOString().substring(0, 10);
  }

  isHorarioPasado(fecha: string, hora: string): boolean {
    const hoy = new Date();
    const fechaActualStr = hoy.toISOString().substring(0, 10);
    if (fecha !== fechaActualStr) return false;
    // Si el string es un rango, toma la hora de inicio
    const horaInicio = hora.includes('-') ? hora.split('-')[0].trim() : hora;
    const [h, m] = horaInicio.split(':').map(Number);
    // Crear fecha local con la hora deseada
    const [anio, mes, dia] = fecha.split('-').map(Number);
    const fechaHora = new Date(anio, mes - 1, dia, h, m, 0, 0);
    return hoy >= fechaHora;
  }

  getLugaresLibres(fecha: string, horaInicio: string): number | string {
    // Normaliza la hora a formato HH:mm
    let [h, m] = horaInicio.split(':').map(Number);
    if (isNaN(h)) return 5;
    if (isNaN(m)) m = 0;
    const horaNorm = `${h.toString().padStart(2, '0')}:${m
      .toString()
      .padStart(2, '0')}`;
    const turno = this.turnos.find(
      (t) =>
        t.fecha === fecha &&
        (t.horaInicio === horaNorm || t.horaInicio === horaInicio)
    );
    return turno ? turno.lugaresLibres : 5;
  }

  // Acción extra para el botón: cancelar turno
  accionExtraUsuario() {
    if (!this.turnoSeleccionado) return;
    // Construir el objeto con los campos requeridos por el backend
    const turnoCancel = {
      id: this.turnoSeleccionado.id ?? null,
      horaInicio: this.turnoSeleccionado.horaInicio ?? null,
      horaFin: this.turnoSeleccionado.horaFin ?? null,
      estado: this.turnoSeleccionado.estado ?? null,
      EmailUser:
        this.turnoSeleccionado.emailUser ??
        this.turnoSeleccionado.EmailUser ??
        null,
      ServiceId:
        this.turnoSeleccionado.serviceId ??
        this.turnoSeleccionado.ServiceId ??
        null,
    };
    if (this.service) {
    }
    this.service.cancelarTurno(turnoCancel).subscribe({
      next: () => {
        this.turnoSeleccionado = null;
        Swal.fire(
          'Turno cancelado',
          'El turno fue cancelado correctamente.',
          'success'
        );
        this.service.getAllTurnos().subscribe((turnos) => {
          this.turnos = turnos;
        });
      },
      error: () => {
        Swal.fire('Error', 'No se pudo cancelar el turno.', 'error');
      },
    });
  }
}
