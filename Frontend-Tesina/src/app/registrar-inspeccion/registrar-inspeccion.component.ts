import { Component, inject } from '@angular/core';
import {
  AbstractControl,
  ReactiveFormsModule,
  UntypedFormControl,
  UntypedFormGroup,
  ValidationErrors,
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
    dni: new UntypedFormControl('', [this.digitLengthValidator(8)]),
    auto: new UntypedFormControl('', []),
    modelo: new UntypedFormControl('', [this.digitLengthValidator(4)]),
    observacionesPrevias: new UntypedFormControl('', []),
  });
  digitLengthValidator(length: number) {
    return (control: AbstractControl): ValidationErrors | null => {
      const value = control.value;
      if (value == null) return null;
      if (value < 0) return { negativeValue: true };
      const digits = value.toString().length;
      return digits === length ? null : { digitLength: true };
    };
  }

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
          this.router.navigate(['/espera']);

          Swal.fire({
            icon: 'success',
            title: '¡Servicio modificada!',
            text: 'El Servicio fue modificado exitosamente',
            confirmButtonColor: '#3085d6',
          });
        },
        error: (err) => {
          Swal.fire({
            icon: 'error',
            title: 'Oops...',
            text: 'Ocurrio un error al registrar el servicio',
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
