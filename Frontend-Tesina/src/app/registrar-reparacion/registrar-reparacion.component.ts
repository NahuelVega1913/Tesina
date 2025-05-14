import { Component } from '@angular/core';
import {
  ReactiveFormsModule,
  UntypedFormControl,
  UntypedFormGroup,
} from '@angular/forms';

@Component({
  selector: 'app-registrar-reparacion',
  imports: [ReactiveFormsModule],
  templateUrl: './registrar-reparacion.component.html',
  styleUrl: './registrar-reparacion.component.css',
})
export class RegistrarReparacionComponent {
  form = new UntypedFormGroup({
    id: new UntypedFormControl('0', []),
    nombreCompleto: new UntypedFormControl('', []),
    dni: new UntypedFormControl('', []),
    auto: new UntypedFormControl('', []),
    modelo: new UntypedFormControl('', []),
    observacionesPrevias: new UntypedFormControl('', []),
  });

  save() {}
}
