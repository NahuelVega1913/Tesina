import { Component, inject } from '@angular/core';
import {
  FormsModule,
  ReactiveFormsModule,
  UntypedFormControl,
  UntypedFormGroup,
} from '@angular/forms';
import Swal from 'sweetalert2';
import { EmpleadosService } from '../services/empleados.service';
import { ServiciosService } from '../services/servicios.service';

@Component({
  selector: 'app-modificar-reparacion',
  imports: [FormsModule, ReactiveFormsModule],
  templateUrl: './modificar-reparacion.component.html',
  styleUrl: './modificar-reparacion.component.css',
})
export class ModificarReparacionComponent {
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
    if (this.status == 'PROCESS') {
      this.form.get('idEmpleado')?.disable();
    }
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

  save() {
    if (this.status == 'IN_QUEUE') {
      const id = localStorage.getItem('idServicio') || 0;
      const getSubscription = this.service
        .pasarAProceso(Number(id), Number(this.form.value.idEmpleado))
        .subscribe({
          next: (res) => {
            console.log(this.form.value);

            Swal.fire({
              icon: 'success',
              title: '¡Usuario creado!',
              text: 'El proveedor fue registrado exitosamente',
              confirmButtonColor: '#3085d6',
            });
          },
          error: (err) => {
            console.log(err);
            Swal.fire({
              icon: 'error',
              title: 'Oops...',
              text: 'Ocurrio un error al registrar el proveedor',
            });
          },
        });
    } else {
      if (this.form.valid) {
        const entity: any = this.form.value;
        console.log(entity);
        const addSubscription = this.service.finishRepair(entity).subscribe({
          next: () => {
            Swal.fire({
              icon: 'success',
              title: '¡Usuario creado!',
              text: 'El proveedor fue registrado exitosamente',
              confirmButtonColor: '#3085d6',
            });
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
}
