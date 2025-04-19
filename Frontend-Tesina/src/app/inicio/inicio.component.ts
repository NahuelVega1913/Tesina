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

  nombre: string | null = '';
  apellido: string | null = '';
  rol: string | null = '';

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
  ngOnInit(): void {
    //Called after the constructor, initializing input properties, and the first call to ngOnChanges.
    //Add 'implements OnInit' to the class.
    this.nombre = localStorage.getItem('name');
    this.apellido = localStorage.getItem('lastname');
    this.rol = localStorage.getItem('role');
  }

  calcultarIniciales() {
    const nombreArray = localStorage.getItem('name')?.split('') || [];
    const apellidoArray = localStorage.getItem('lastname')?.split('') || [];
    return nombreArray[0] + apellidoArray[0];
  }

  redirectTo(url: string) {
    this.router.navigate([`${url}`]);
  }
  salir(url: string) {
    localStorage.clear();
    this.router.navigate([`${url}`]);
  }
}
