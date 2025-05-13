import { Component, inject } from '@angular/core';
import {
  ReactiveFormsModule,
  UntypedFormControl,
  UntypedFormGroup,
} from '@angular/forms';
import { Router } from '@angular/router';
import { ServiciosService } from '../services/servicios.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-registrar-inspeccion',
  imports: [ReactiveFormsModule],
  templateUrl: './registrar-inspeccion.component.html',
  styleUrl: './registrar-inspeccion.component.css',
})
export class RegistrarInspeccionComponent {
  constructor(private router: Router) {}
  private service: ServiciosService = inject(ServiciosService);

  form = new UntypedFormGroup({
    id: new UntypedFormControl('0', []),
    nombreCompleto: new UntypedFormControl('', []),
    dni: new UntypedFormControl('', []),
    auto: new UntypedFormControl('', []),
    modelo: new UntypedFormControl('', []),
    observacionesPrevias: new UntypedFormControl('', []),
  });

  redirectTo(url: string) {
    console.log('Redirecting to:', url);
    window.location.href = url;
  }
  save() {
    if (this.form.valid) {
      const entity: any = this.form.value;
      console.log(entity);
      const addSubscription = this.service.postService(entity).subscribe({
        next: () => {
          window.location.href = '/espera';

          Swal.fire({
            icon: 'success',
            title: '¡Usuario creado!',
            text: 'El proveedor fue registrado exitosamente',
            confirmButtonColor: '#3085d6',
          });
        },
        error: (err) => {
          Swal.fire({
            icon: 'error',
            title: 'Oops...',
            text: 'Ocurrio un error al registrar el proveedor',
          });
        },
      });
    } else {
      Swal.fire({
        icon: 'error',
        title: 'Oops...',
        text: 'Complete todos los campos obligatorios',
      });
    }
  }
}
