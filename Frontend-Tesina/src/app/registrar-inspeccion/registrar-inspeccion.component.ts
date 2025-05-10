import { Component } from '@angular/core';
import {
  ReactiveFormsModule,
  UntypedFormControl,
  UntypedFormGroup,
} from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'app-registrar-inspeccion',
  imports: [ReactiveFormsModule],
  templateUrl: './registrar-inspeccion.component.html',
  styleUrl: './registrar-inspeccion.component.css',
})
export class RegistrarInspeccionComponent {
  constructor(private router: Router) {}

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

  redirectTo(url: string) {
    console.log('Redirecting to:', url);
    window.location.href = url;
  }
  save() {
    this.redirectTo('/espera');
  }
}
