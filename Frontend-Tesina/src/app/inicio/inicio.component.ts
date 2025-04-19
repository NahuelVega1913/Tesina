import { Component } from '@angular/core';

import { Router, RouterLink, RouterOutlet } from '@angular/router';

@Component({
  selector: 'app-inicio',
  imports: [RouterOutlet],
  templateUrl: './inicio.component.html',
  styleUrl: './inicio.component.css',
})
export class InicioComponent {
  constructor(private router: Router) {}

  nombre = localStorage.getItem('name');
  apellido = localStorage.getItem('lastname');
  rol = localStorage.getItem('role');

  rolAMostrar() {
    if (this.rol === 'ADMIN') {
      return 'Administrador';
    } else if (this.rol === 'USER') {
      return 'Usuario';
    } else if (this.rol === 'SUPERADMIN') {
      return 'Super Administrador';
    } else {
      return 'Empleado';
    }
  }

  calcultarIniciales() {
    const nombreArray = localStorage.getItem('name')?.split('') || [];
    const apellidoArray = localStorage.getItem('lastname')?.split('') || [];
    return nombreArray[0] + apellidoArray[0];
  }

  redirectTo(url: string) {
    this.router.navigate([`${url}`]);
  }
}
