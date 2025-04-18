import { Component, inject } from '@angular/core';
import { AuthService } from '../services/auth.service';
import {
  MinLengthValidator,
  ReactiveFormsModule,
  UntypedFormArray,
  UntypedFormControl,
  UntypedFormGroup,
} from '@angular/forms';
import Swal from 'sweetalert2';
import { Router } from '@angular/router';
import { NgClass } from '@angular/common';
import { min } from 'rxjs';

@Component({
  selector: 'app-registrarse',
  imports: [ReactiveFormsModule, NgClass],
  templateUrl: './registrarse.component.html',
  styleUrl: './registrarse.component.css',
})
export class RegistrarseComponent {
  private service: AuthService = inject(AuthService);

  constructor(private router: Router) {}

  form = new UntypedFormGroup({
    name: new UntypedFormControl('', []),
    lastname: new UntypedFormControl('', []),
    phone: new UntypedFormControl('', []),
    address: new UntypedFormControl('', []),
    email: new UntypedFormControl('', []),
    password: new UntypedFormControl('', []),
  });

  save() {
    if (this.form.valid) {
      const entity: any = this.form.value;
      const addSubscription = this.service.crear(entity).subscribe({
        next: () => {
          Swal.fire({
            icon: 'success',
            title: '¡Usuario creado!',
            text: 'El usuario fue registrado exitosamente',
            confirmButtonColor: '#3085d6',
          });

          this.router.navigate(['/login']);
        },
        error: (err) => {
          Swal.fire({
            icon: 'error',
            title: 'Oops...',
            text: 'Ocurrio un error al registrar el usuario',
          });
        },
      });
    }
  }
}
