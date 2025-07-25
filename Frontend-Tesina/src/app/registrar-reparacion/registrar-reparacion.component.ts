import { Component, inject } from '@angular/core';
import {
  AbstractControl,
  ReactiveFormsModule,
  UntypedFormControl,
  UntypedFormGroup,
  ValidationErrors,
  Validators,
} from '@angular/forms';
import { Router } from '@angular/router';
import { ServiciosService } from '../services/servicios.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-registrar-reparacion',
  imports: [ReactiveFormsModule],
  templateUrl: './registrar-reparacion.component.html',
  styleUrl: './registrar-reparacion.component.css',
})
export class RegistrarReparacionComponent {
  constructor(private router: Router) {}
  private service: ServiciosService = inject(ServiciosService);

  form = new UntypedFormGroup({
    id: new UntypedFormControl('0', []),
    nombreCompleto: new UntypedFormControl('', []),
    dni: new UntypedFormControl('', [this.digitLengthValidator(8)]),
    auto: new UntypedFormControl('', []),
    modelo: new UntypedFormControl('', [this.digitLengthValidator(4)]),
    observacionesPrevias: new UntypedFormControl('', [Validators.required]),
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

  save() {
    if (this.form.valid) {
      const entity: any = this.form.value;
      console.log(entity);
      const addSubscription = this.service.postReparacion(entity).subscribe({
        next: () => {
          window.location.href = '/espera';

          Swal.fire({
            icon: 'success',
            title: '¡Servicio registrado!',
            text: 'El servicio fue registrado exitosamente',
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
