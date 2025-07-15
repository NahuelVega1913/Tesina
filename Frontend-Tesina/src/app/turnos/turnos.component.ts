import { Component } from '@angular/core';

@Component({
  selector: 'app-turnos',
  imports: [],
  templateUrl: './turnos.component.html',
  styleUrl: './turnos.component.css',
})
export class TurnosComponent {
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
}
