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
  hasService: boolean = false; // Nuevo: indica si el usuario tiene servicio

  ngOnInit(): void {
    const rol = (localStorage.getItem('role') || '').toUpperCase();
    this.isAdmin = rol === 'ADMIN' || rol === 'SUPERADMIN';
    this.isUser = rol === 'USER';
    // Nuevo: obtener hasService de localStorage (guardado como string 'true'/'false')
    this.hasService = localStorage.getItem('hasService') === 'true';

    // Si es usuario y no tiene servicio, no cargar turnos
    if (this.isUser && !this.hasService) {
      // Mostrar cartel en el HTML, no cargar turnos
      return;
    }

    this.service?.getAllTurnos().subscribe((turnos) => {
      this.turnos = turnos;
    });

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
    // Si es USER y ya tiene un turno seleccionado, no permite seleccionar otro
    if (
      this.isUser &&
      this.turnoSeleccionado &&
      Object.keys(this.turnoSeleccionado).length > 0
    ) {
      Swal.fire(
        'Ya tienes un turno',
        'No puedes seleccionar otro turno hasta cancelar el actual.',
        'info'
      );
      return;
    }

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

  // Devuelve true si algún horario del día está lleno o si el día anterior hábil tuvo algún horario lleno
  isTurnoLleno(fecha: string, horaInicio: string): boolean {
    // Normaliza la hora a formato HH:mm
    let [h, m] = horaInicio.split(':').map(Number);
    if (isNaN(h)) return false;
    if (isNaN(m)) m = 0;
    const horaNorm = `${h.toString().padStart(2, '0')}:${m
      .toString()
      .padStart(2, '0')}`;

    // Lista de horarios ordenados
    const horarios = ['09:00', '11:00', '14:00', '16:00'];

    // --- 1. Para el día actual: si hay un horario lleno, todos los horarios desde ese (inclusive) hacia adelante están llenos ---
    // Busca el índice del horario actual
    const indexHorario = horarios.indexOf(horaNorm);
    if (indexHorario === -1) return false;

    // Busca si algún horario desde el actual (inclusive) hacia atrás está lleno
    for (let i = 0; i <= indexHorario; i++) {
      const turno = this.turnos.find(
        (t) =>
          t.fecha === fecha &&
          (t.horaInicio === horarios[i] ||
            t.horaInicio === horarios[i] + ':00') &&
          t.lugaresLibres <= 0
      );
      if (turno) return true;
    }

    // --- 2. Para el siguiente día hábil: si algún horario del día anterior está lleno, todos los horarios están llenos ---
    // Calcula el día anterior hábil
    const [anio, mes, dia] = fecha.split('-').map(Number);
    let d = new Date(anio, mes - 1, dia);
    d.setDate(d.getDate() - 1);
    while (d.getDay() === 0 || d.getDay() === 6) {
      d.setDate(d.getDate() - 1);
    }
    const fechaAnterior = d.toISOString().substring(0, 10);

    // Si algún horario del día anterior está lleno, todos los horarios de este día están llenos
    const algunLlenoAyer = this.turnos.some(
      (t) => t.fecha === fechaAnterior && t.lugaresLibres <= 0
    );
    if (algunLlenoAyer) return true;

    return false;
  }

  getFechaByIndex(index: number): string {
    const hoy = new Date();
    hoy.setDate(hoy.getDate() + index);
    return hoy.toISOString().substring(0, 10);
  }

  isHorarioPasado(fecha: string, hora: string): boolean {
    // Si el string es un rango, toma la hora de inicio
    const horaInicio = hora.includes('-') ? hora.split('-')[0].trim() : hora;
    const [h, m] = horaInicio.split(':').map(Number);
    const [anio, mes, dia] = fecha.split('-').map(Number);
    if (isNaN(h) || isNaN(m) || isNaN(anio) || isNaN(mes) || isNaN(dia))
      return false;
    // Construir string con zona horaria de Argentina (-03:00)
    const fechaHoraStr = `${anio}-${String(mes).padStart(2, '0')}-${String(
      dia
    ).padStart(2, '0')}T${String(h).padStart(2, '0')}:${String(m).padStart(
      2,
      '0'
    )}:00-03:00`;
    const fechaHoraTurno = Date.parse(fechaHoraStr);
    const ahora = Date.now();
    return fechaHoraTurno <= ahora;
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
