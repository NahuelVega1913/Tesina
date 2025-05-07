import { Component, inject } from '@angular/core';
import { UntypedFormControl, UntypedFormGroup } from '@angular/forms';
import { EmpleadosService } from '../services/empleados.service';

@Component({
  selector: 'app-modificar-empleado',
  imports: [],
  templateUrl: './modificar-empleado.component.html',
  styleUrl: './modificar-empleado.component.css',
})
export class ModificarEmpleadoComponent {
  private service: EmpleadosService = inject(EmpleadosService);

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

  getProveedor() {
    const id = localStorage.getItem('idEmpleado') || 0;
    const getSubscription = this.service.getEmployees(Number(id)).subscribe({
      next: (res) => {
        this.form.patchValue(res);
        console.log(this.form.value);
        const fecha = new Date(res.registerDate);
        const año = fecha.getFullYear();
        const mes = String(fecha.getMonth() + 1).padStart(2, '0');
        const dia = String(fecha.getDate()).padStart(2, '0');
        const fechaFormateada = `${año}-${mes}-${dia}`;

        this.form.patchValue({
          ...res,
          registerDate: fechaFormateada,
        });
      },
      error: (err) => {
        console.log(err);
      },
    });
  }
}
