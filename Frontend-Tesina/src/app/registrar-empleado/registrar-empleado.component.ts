import { Component, inject } from '@angular/core';
import {
  AbstractControl,
  ReactiveFormsModule,
  UntypedFormControl,
  UntypedFormGroup,
  ValidationErrors,
  Validators,
} from '@angular/forms';
import { EmpleadosService } from '../services/empleados.service';
import { Router } from '@angular/router';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-registrar-empleado',
  imports: [ReactiveFormsModule],
  templateUrl: './registrar-empleado.component.html',
  styleUrl: './registrar-empleado.component.css',
})
export class RegistrarEmpleadoComponent {
  private service: EmpleadosService = inject(EmpleadosService);
  constructor(private router: Router) {}

  form = new UntypedFormGroup({
    fullName: new UntypedFormControl('', []),
    cuit: new UntypedFormControl('', [this.digitLengthValidator(11)]),
    phone: new UntypedFormControl('', [this.digitLengthValidator(12)]),
    address: new UntypedFormControl('', []),
    category: new UntypedFormControl('', []),
    bancaryNumber: new UntypedFormControl('', [this.digitLengthValidator(22)]),
    typeOfContract: new UntypedFormControl('', []),
    dateOfEntry: new UntypedFormControl('', []),
    birthDate: new UntypedFormControl('', [this.ageValidator(16)]),
    email: new UntypedFormControl('', [Validators.email]),
    salary: new UntypedFormControl('', this.noNegativeValidator()),
    workingDay: new UntypedFormControl('', []),
    position: new UntypedFormControl('', []),
    remarks: new UntypedFormControl('', []),
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
  toPlainString(num: any): string {
    if (typeof num === 'string') return num;
    if (typeof num === 'number') {
      // Si es notación científica, conviértelo
      const str = num.toString();
      if (str.indexOf('e') !== -1) {
        return num.toLocaleString('fullwide', { useGrouping: false });
      }
      return str;
    }
    return '';
  }
  noNegativeValidator() {
    return (control: AbstractControl): ValidationErrors | null => {
      const value = control.value;
      if (value == null) return null;
      return value < 0 ? { negativeValue: true } : null;
    };
  }
  ageValidator(minAge: number) {
    return (control: AbstractControl): ValidationErrors | null => {
      const value = control.value;
      if (!value) return null;
      const birthDate = new Date(value);
      const today = new Date();
      let age = today.getFullYear() - birthDate.getFullYear();
      const m = today.getMonth() - birthDate.getMonth();
      if (m < 0 || (m === 0 && today.getDate() < birthDate.getDate())) {
        age--;
      }
      return age >= minAge ? null : { minAge: true };
    };
  }

  onBancaryNumberChange(event: any) {
    const value = event.target.value;
    this.form.get('bancaryNumber')?.setValue(value);
  }
  onlyNumberInput(event: KeyboardEvent) {
    const charCode = event.key.charCodeAt(0);
    // Permite solo números (0-9)
    if (charCode < 48 || charCode > 57) {
      event.preventDefault();
    }
  }

  save() {
    if (this.form.valid) {
      const entity: any = this.form.value;
      console.log(entity);

      if (entity.bancaryNumber != null) {
        entity.bancaryNumber = this.toPlainString(entity.bancaryNumber);
      }
      const addSubscription = this.service.registerEmployee(entity).subscribe({
        next: () => {
          Swal.fire({
            icon: 'success',
            title: '¡Usuario creado!',
            text: 'El empleado fue registrado exitosamente',
            confirmButtonColor: '#3085d6',
          });

          this.router.navigate(['/empleados']);
        },
        error: (err) => {
          Swal.fire({
            icon: 'error',
            title: 'Oops...',
            text: 'Ocurrio un error al registrar el empleado',
          });
        },
      });
    } else {
      this.form.markAllAsTouched();
      Swal.fire({
        icon: 'error',
        title: 'Oops...',
        text: 'Complete todos los campos obligatorios',
      });
    }
  }
}
