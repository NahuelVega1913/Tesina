import { Component, inject } from '@angular/core';
import {
  AbstractControl,
  ReactiveFormsModule,
  UntypedFormControl,
  UntypedFormGroup,
  ValidationErrors,
} from '@angular/forms';
import { EmpleadosService } from '../services/empleados.service';
import Swal from 'sweetalert2';
import { Router } from '@angular/router';

@Component({
  selector: 'app-modificar-empleado',
  imports: [ReactiveFormsModule],
  templateUrl: './modificar-empleado.component.html',
  styleUrl: './modificar-empleado.component.css',
})
export class ModificarEmpleadoComponent {
  private service: EmpleadosService = inject(EmpleadosService);
  constructor(private router: Router) {}

  form = new UntypedFormGroup({
    fullName: new UntypedFormControl('', []),
    cuit: new UntypedFormControl('', []),
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
  ngOnInit(): void {
    //Called after the constructor, initializing input properties, and the first call to ngOnChanges.
    //Add 'implements OnInit' to the class.
    this.getEmployee();
  }
  digitLengthValidator(length: number) {
    return (control: AbstractControl): ValidationErrors | null => {
      const value = control.value;
      if (value == null) return null;
      if (value < 0) return { negativeValue: true };

      const digits = value.toString().length;
      return digits === length ? null : { digitLength: true };
    };
  }

  getEmployee() {
    const id = localStorage.getItem('idEmpleado') || 0;
    const getSubscription = this.service.getEmployee(Number(id)).subscribe({
      next: (res) => {
        this.form.patchValue(res);
        console.log(res);
        console.log(this.form.value);
        const fecha = new Date(res.birthDate);
        const año = fecha.getFullYear();
        const mes = String(fecha.getMonth() + 1).padStart(2, '0');
        const dia = String(fecha.getDate()).padStart(2, '0');
        const fechaFormateada = `${año}-${mes}-${dia}`;

        const fecha1 = new Date(res.dateOfEntry);
        const año1 = fecha.getFullYear();
        const mes1 = String(fecha1.getMonth() + 1).padStart(2, '0');
        const dia1 = String(fecha1.getDate()).padStart(2, '0');
        const fechaFormateada1 = `${año1}-${mes1}-${dia1}`;
        this.form.patchValue({
          ...res,
          birthDate: fechaFormateada,
          dateOfEntry: fechaFormateada1,
        });
      },
      error: (err) => {
        console.log(err);
      },
    });
  }
  save() {
    if (this.form.valid) {
      const entity: any = this.form.value;
      entity.id = localStorage.getItem('idEmpleado');
      console.log(entity);
      const addSubscription = this.service.putEmployee(entity).subscribe({
        next: () => {
          Swal.fire({
            icon: 'success',
            title: '¡Empleado Modificado!',
            text: 'El empleado fue modificado exitosamente',
            confirmButtonColor: '#3085d6',
          });

          this.router.navigate(['/empleados']);
        },
        error: (err) => {
          Swal.fire({
            icon: 'error',
            title: 'Oops...',
            text: 'Ocurrio un error al modificar el empleado',
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
