import { Component, inject } from '@angular/core';
import { ServiciosService } from '../services/servicios.service';
import { EmpleadosService } from '../services/empleados.service';
import {
  ReactiveFormsModule,
  UntypedFormControl,
  UntypedFormGroup,
} from '@angular/forms';
import Swal from 'sweetalert2';
import { Router } from '@angular/router';

@Component({
  selector: 'app-generar-presupuesto',
  imports: [ReactiveFormsModule],
  templateUrl: './generar-presupuesto.component.html',
  styleUrl: './generar-presupuesto.component.css',
})
export class GenerarPresupuestoComponent {
  constructor(private router: Router) {}

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
    materialsUsed: new UntypedFormControl('', []),
    budget: new UntypedFormControl('', []),
  });
  ngOnInit(): void {
    //Called after the constructor, initializing input properties, and the first call to ngOnChanges.
    //Add 'implements OnInit' to the class.
    this.getService();
    this.status = localStorage.getItem('status') || '';

    this.form.get('idEmpleado')?.disable();
    this.form.get('dni')?.disable();
    this.form.get('nombreCompleto')?.disable();
    this.form.get('modelo')?.disable();
    this.form.get('auto')?.disable();
    this.form.get('observacionesPrevias')?.disable();
    this.form.get('cost')?.disable();
    this.form.get('paymentStatus')?.disable();
    this.form.get('status')?.disable();
    this.form.get('type')?.disable();
    this.form.get('budget');
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

  registerBudget() {
    const id = localStorage.getItem('idServicio') || 0;
    const budgetControl = this.form.get('budget');
    const budget = budgetControl ? budgetControl.value : null;
    const getSubscription = this.service
      .registerBudget(Number(id), budget)
      .subscribe({
        next: (res) => {
          Swal.fire({
            icon: 'success',
            title: 'Presupuesto generado',
            text: 'El presupuesto se ha generado correctamente.',
            confirmButtonColor: '#3085d6',
          });
          this.router.navigate(['consultarServicios']);
          //   window.location.href = '/consultarServicios';
        },
        error: (err) => {
          Swal.fire({
            icon: 'error',
            title: 'Error al generar presupuesto',
            text: 'No se pudo generar el presupuesto. Inténtalo de nuevo más tarde.',
            confirmButtonColor: '#3085d6',
          });
        },
      });
  }
}
