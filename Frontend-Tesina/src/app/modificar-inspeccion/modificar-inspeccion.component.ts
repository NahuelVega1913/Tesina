import { Component, inject } from '@angular/core';
import {
  ReactiveFormsModule,
  UntypedFormControl,
  UntypedFormGroup,
} from '@angular/forms';
import { EmpleadosService } from '../services/empleados.service';

@Component({
  selector: 'app-modificar-inspeccion',
  imports: [ReactiveFormsModule],
  templateUrl: './modificar-inspeccion.component.html',
  styleUrl: './modificar-inspeccion.component.css',
})
export class ModificarInspeccionComponent {
  private serviceEmpleados: EmpleadosService = inject(EmpleadosService);
  lstEmpleados: any[] = [];

  form = new UntypedFormGroup({
    id: new UntypedFormControl('0', []),
    nombreCompleto: new UntypedFormControl('', []),
    dni: new UntypedFormControl('', []),
    auto: new UntypedFormControl('', []),
    modelo: new UntypedFormControl('', []),
    observacionesPrevias: new UntypedFormControl('', []),
  });

  getEmpleados() {
    const getSubscription = this.serviceEmpleados.getEmployees().subscribe({
      next: (res) => {
        this.lstEmpleados = res;
        console.log(this.lstEmpleados);
      },
      error: (err) => {
        console.log(err);
      },
    });
  }

  save() {}
}
