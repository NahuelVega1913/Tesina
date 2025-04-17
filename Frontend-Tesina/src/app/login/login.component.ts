import { Component } from '@angular/core';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-login',
  imports: [],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css',
})
export class LoginComponent {
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

    fetch('http://localhost:8080/auth/authenticate', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(body),
    })
      .then((response) => response.json())
      .then((data) => {
        Swal.fire({
          icon: 'success',
          title: '¡Usuario creado!',
          text: 'El usuario fue registrado exitosamente',
          confirmButtonColor: '#3085d6',
        });
        // Aquí puedes manejar la respuesta del servidor, como redirigir al usuasrio o mostrar un mensaje de éxito
      })
      .catch((error) => {
        Swal.fire({
          icon: 'error',
          title: 'Oops...',
          text: 'Ocurrio un error al registrar el usuario',
        });
        // Aquí puedes manejar el error, como mostrar un mensaje de error al usuario
      });
  }
}
