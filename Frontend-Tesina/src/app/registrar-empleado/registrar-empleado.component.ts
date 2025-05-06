import { Component } from '@angular/core';
import {
  ReactiveFormsModule,
  UntypedFormControl,
  UntypedFormGroup,
} from '@angular/forms';

@Component({
  selector: 'app-registrar-empleado',
  imports: [ReactiveFormsModule],
  templateUrl: './registrar-empleado.component.html',
  styleUrl: './registrar-empleado.component.css',
})
export class RegistrarEmpleadoComponent {
  form = new UntypedFormGroup({
    name: new UntypedFormControl('', []),
    cuit: new UntypedFormControl('', []),
    phone: new UntypedFormControl('', []),
    address: new UntypedFormControl('', []),
    category: new UntypedFormControl('', []),
    email: new UntypedFormControl('', []),
    country: new UntypedFormControl('', []),
    city: new UntypedFormControl('', []),
    remarks: new UntypedFormControl('', []),
  });

  save() {}
}
