import { Component, inject } from '@angular/core';
import {
  FormsModule,
  ReactiveFormsModule,
  UntypedFormControl,
  UntypedFormGroup,
} from '@angular/forms';
import { EmpleadosService } from '../services/empleados.service';
import { ServiciosService } from '../services/servicios.service';
import Swal from 'sweetalert2';
import { Router } from '@angular/router';

@Component({
  selector: 'app-consultar-customizacion',
  imports: [FormsModule, ReactiveFormsModule],
  templateUrl: './consultar-customizacion.component.html',
  styleUrl: './consultar-customizacion.component.css',
})
export class ConsultarCustomizacionComponent {
  private serviceEmpleados: EmpleadosService = inject(EmpleadosService);
  private service: ServiciosService = inject(ServiciosService);
  constructor(private router: Router) {}

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
    taskRealized: new UntypedFormControl('', []),
  });
  ngOnInit(): void {
    //Called after the constructor, initializing input properties, and the first call to ngOnChanges.
    //Add 'implements OnInit' to the class.
    this.getEmpleados();
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
    this.form.get('materialsUsed')?.disable();
    this.form.get('taskRealized')?.disable();
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
        this.status = res.status; // <-- Asegura que status se actualiza con el valor real del servicio
      },
      error: (err) => {
        console.log(err);
      },
    });
  }
  cancelService() {
    const id = localStorage.getItem('idServicio') || 0;
    Swal.fire({
      title: 'Esta seguro de esta accion?',
      showDenyButton: true,
      confirmButtonText: 'Si',
      denyButtonText: `Cancelar`,
    }).then((result) => {
      /* Read more about isConfirmed, isDenied below */
      if (result.isConfirmed) {
        const addSubscription = this.service
          .cancelService(Number(id))
          .subscribe({
            next: () => {
              Swal.fire({
                icon: 'success',
                title: '¡Servicio Modificado!',
                text: 'El servicio fue modificado exitosamente',
                confirmButtonColor: '#3085d6',
              });

              this.router.navigate(['/consultarServicios']);
            },
            error: (err) => {
              Swal.fire({
                icon: 'error',
                title: 'Oops...',
                text: 'Ocurrio un error al modificar el servicio',
              });
            },
          });
      } else if (result.isDenied) {
        Swal.fire('Changes are not saved', '', 'info');
      }
    });
  }
}
