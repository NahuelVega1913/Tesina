import { Component, inject } from '@angular/core';
import {
  ReactiveFormsModule,
  UntypedFormControl,
  UntypedFormGroup,
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
    CUIT: new UntypedFormControl('', []),
    phone: new UntypedFormControl('', []),
    address: new UntypedFormControl('', []),
    category: new UntypedFormControl('', []),
    bancaryNumber: new UntypedFormControl('', []),
    typeOfContract: new UntypedFormControl('', []),
    dateOfEntry: new UntypedFormControl('', []),
    birthDate: new UntypedFormControl('', []),
    email: new UntypedFormControl('', []),
    salary: new UntypedFormControl('', []),
    workingDay: new UntypedFormControl('', []),
    position: new UntypedFormControl('', []),
    remarks: new UntypedFormControl('', []),
  });

  save() {
    if (this.form.valid) {
      const entity: any = this.form.value;
      console.log(entity);
      const addSubscription = this.service.registerEmployee(entity).subscribe({
        next: () => {
          Swal.fire({
            icon: 'success',
            title: '¡Usuario creado!',
            text: 'El proveedor fue registrado exitosamente',
            confirmButtonColor: '#3085d6',
          });

          this.router.navigate(['/proveedores']);
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
