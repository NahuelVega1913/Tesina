import { Component, inject } from '@angular/core';
import {
  AbstractControl,
  FormsModule,
  ReactiveFormsModule,
  UntypedFormControl,
  UntypedFormGroup,
  ValidationErrors,
  Validators,
} from '@angular/forms';
import { EmpleadosService } from '../services/empleados.service';
import { ServiciosService } from '../services/servicios.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-modificar-inspeccion',
  imports: [ReactiveFormsModule, FormsModule],
  templateUrl: './modificar-inspeccion.component.html',
  styleUrl: './modificar-inspeccion.component.css',
})
export class ModificarInspeccionComponent {
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
    cost: new UntypedFormControl('', [Validators.min(1)]),
    paymentStatus: new UntypedFormControl('', []),
    status: new UntypedFormControl('', []),
    type: new UntypedFormControl('', []),
    resultado: new UntypedFormControl('', []),
    estadoGeneral: new UntypedFormControl('', [Validators.required]),
    recomendaciones: new UntypedFormControl('', []),
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
              title: '¡Servicio modificada!',
              text: 'El Servicio fue modificado exitosamente',
              confirmButtonColor: '#3085d6',
            });
            window.location.href = '/consultarServicios';
          },
          error: (err) => {
            console.log(err);
            Swal.fire({
              icon: 'error',
              title: 'Oops...',
              text: 'Ocurrio un error al modificar el servicio',
            });
          },
        });
    } else {
      if (this.form.valid) {
        const entity: any = this.form.value;
        console.log(entity);
        const addSubscription = this.service
          .finishInspection(entity)
          .subscribe({
            next: () => {
              Swal.fire({
                icon: 'success',
                title: '¡Servicio modificada!',
                text: 'El Servicio fue modificado exitosamente',
                confirmButtonColor: '#3085d6',
              });
              window.location.href = '/consultarServicios';
            },
            error: (err) => {
              Swal.fire({
                icon: 'error',
                title: 'Oops...',
                text: 'Ocurrio un error al modificar el servicio',
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
