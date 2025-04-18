import { Component, inject } from '@angular/core';
import Swal from 'sweetalert2';
import { AuthService } from '../services/auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  imports: [],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css',
})
export class LoginComponent {
  private service: AuthService = inject(AuthService);
  constructor(private router: Router) {}

  goToRegister() {
    window.location.href = '/registrarse';
  }

  login() {
    const email = (document.getElementById('email') as HTMLInputElement).value;
    const password = (document.getElementById('password') as HTMLInputElement)
      .value;

    const body = {
      email: email,
      password: password,
    };
    const addSubscription = this.service.login(body).subscribe({
      next: () => {
        Swal.fire({
          icon: 'success',
          title: '¡Bienvenido!',
          text: ' Inicio de sesion exitoso',
          confirmButtonColor: '#3085d6',
        });

        this.router.navigate(['/inicio']);
      },
      error: (err) => {
        Swal.fire({
          icon: 'error',
          title: 'Oops...',
          text: 'Usuario o contraseña invalidos',
        });
      },
    });
  }
}
