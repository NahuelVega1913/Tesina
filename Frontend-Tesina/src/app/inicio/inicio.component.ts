import { Component, inject } from '@angular/core';

import { Router, RouterLink, RouterOutlet } from '@angular/router';
import { RouterModule } from '@angular/router';
import { UsuarioService } from '../services/usuario.service';

@Component({
  selector: 'app-inicio',
  imports: [RouterOutlet, RouterLink, RouterModule],
  templateUrl: './inicio.component.html',
  styleUrl: './inicio.component.css',
})
export class InicioComponent {
  SideBar = false;
  UserMenu = false;
  notificaciones = '';
  haveService = false;

  private service = inject(UsuarioService);
  OpenUserMenu() {
    this.UserMenu = !this.UserMenu;
  }
  OpenSideBar() {
    this.SideBar = !this.SideBar;
  }

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
    this.SideBar = false;
    this.UserMenu = false;
    this.nombre = localStorage.getItem('name');
    this.apellido = localStorage.getItem('lastname');
    this.rol = localStorage.getItem('role');

    this.getUserInformation();
  }
  getUserInformation() {
    const getSubscription = this.service.getUsuarioInformation().subscribe({
      next: (res) => {
        console.log(res);
        this.notificaciones = res.notificaciones;
        this.haveService = res.hasService;
      },
      error: (err) => {
        console.log(err);
      },
    });
  }

  inicioSesion() {
    const token = localStorage.getItem('token');
    return token !== null && token.trim() !== '';
  }

  calcultarIniciales() {
    const nombreArray = localStorage.getItem('name')?.split('') || [];
    const apellidoArray = localStorage.getItem('lastname')?.split('') || [];
    return nombreArray[0] + apellidoArray[0];
  }

  redirectTo(url: string) {
    this.router.navigate([`${url}`]);
  }
  moveTo(url: string) {
    window.location.href = url;
  }
  salir(url: string) {
    localStorage.clear();
    this.router.navigate([`${url}`]);
  }
}
