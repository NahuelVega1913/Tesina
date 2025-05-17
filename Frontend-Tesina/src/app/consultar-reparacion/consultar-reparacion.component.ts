import { Component, inject } from '@angular/core';
import {
  FormsModule,
  ReactiveFormsModule,
  UntypedFormControl,
  UntypedFormGroup,
} from '@angular/forms';
import Swal from 'sweetalert2';
import { ServiciosService } from '../services/servicios.service';
import { EmpleadosService } from '../services/empleados.service';

@Component({
  selector: 'app-consultar-reparacion',
  imports: [ReactiveFormsModule, FormsModule],
  templateUrl: './consultar-reparacion.component.html',
  styleUrl: './consultar-reparacion.component.css',
})
export class ConsultarReparacionComponent {
  private serviceEmpleados: EmpleadosService = inject(EmpleadosService);
  private service: ServiciosService = inject(ServiciosService);

  lstEmpleados: any[] = [];
  status: string = '';
  empleado: any = '';

  form = new UntypedFormGroup({
    id: new UntypedFormControl('0', []),
    nombreCompleto: new UntypedFormControl('', []),
    dni: new UntypedFormControl('', []),
    auto: new UntypedFormControl('', []),
    modelo: new UntypedFormControl('', []),
    observacionesPrevias: new UntypedFormControl('', []),
    idEmpleado: new UntypedFormControl('', []),
    cost: new UntypedFormControl('', []),
    paymentStatus: new UntypedFormControl('', []),
    status: new UntypedFormControl('', []),
    type: new UntypedFormControl('', []),
    techniclaDiagnosis: new UntypedFormControl('', []),
    tasksPerformed: new UntypedFormControl('', []),
    sparesUsed: new UntypedFormControl('', []),
  });
  ngOnInit(): void {
    //Called after the constructor, initializing input properties, and the first call to ngOnChanges.
    //Add 'implements OnInit' to the class.
    this.getEmpleados();
    this.getService();
    this.status = localStorage.getItem('status') || '';
    this.form.get('cost')?.disable();
    this.form.get('cost')?.disable();
    this.form.get('idEmpleado')?.disable();
    this.form.get('paymentStatus')?.disable();
    this.form.get('type')?.disable();
    this.form.get('techniclaDiagnosis')?.disable();
    this.form.get('tasksPerformed')?.disable();
    this.form.get('sparesUsed')?.disable();
  }

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
  getService() {
    const id = localStorage.getItem('idServicio') || 0;
    const getSubscription = this.service.getServiceById(Number(id)).subscribe({
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
