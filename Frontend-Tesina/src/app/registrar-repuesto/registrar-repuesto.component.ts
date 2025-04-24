import { NgClass } from '@angular/common';
import { Component } from '@angular/core';
import {
  ReactiveFormsModule,
  UntypedFormControl,
  UntypedFormGroup,
} from '@angular/forms';

@Component({
  selector: 'app-registrar-repuesto',
  imports: [NgClass, ReactiveFormsModule],
  templateUrl: './registrar-repuesto.component.html',
  styleUrl: './registrar-repuesto.component.css',
})
export class RegistrarRepuestoComponent {
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
