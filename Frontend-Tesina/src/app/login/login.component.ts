import { Component, inject } from '@angular/core';
import Swal from 'sweetalert2';
import { AuthService } from '../services/auth.service';

@Component({
  selector: 'app-login',
  imports: [],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css',
})
export class LoginComponent {
  private service: AuthService = inject(AuthService);

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
    alert(body.email + body.password);
    const addSubscription = this.service.login(body).subscribe({
      next: () => {
        Swal.fire({
          icon: 'success',
          title: '¡Usuario creado!',
          text: 'El usuario fue registrado exitosamente',
          confirmButtonColor: '#3085d6',
        });
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
