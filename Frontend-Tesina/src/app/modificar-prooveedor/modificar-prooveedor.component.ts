import { Component, inject } from '@angular/core';
import {
  AbstractControl,
  ReactiveFormsModule,
  UntypedFormControl,
  UntypedFormGroup,
  ValidationErrors,
  Validators,
} from '@angular/forms';
import Swal from 'sweetalert2';
import { ProveedoresService } from '../services/proveedores.service';
import { Router } from '@angular/router';
import { NgClass } from '@angular/common';

@Component({
  selector: 'app-modificar-prooveedor',
  imports: [NgClass, ReactiveFormsModule],
  templateUrl: './modificar-prooveedor.component.html',
  styleUrl: './modificar-prooveedor.component.css',
})
export class ModificarProoveedorComponent {
  private service: ProveedoresService = inject(ProveedoresService);

  constructor(private router: Router) {}

  form = new UntypedFormGroup({
    name: new UntypedFormControl('', []),
    cuit: new UntypedFormControl('', [this.digitLengthValidator(11)]),
    phone: new UntypedFormControl('', [this.digitLengthValidator(12)]),
    adress: new UntypedFormControl('', []),
    category: new UntypedFormControl('', []),
    state: new UntypedFormControl('', []),
    registerDate: new UntypedFormControl('', []),
    email: new UntypedFormControl('', [Validators.email]),
    country: new UntypedFormControl('', []),
    city: new UntypedFormControl('', []),
    remarks: new UntypedFormControl('', []),
  });
  // calculateFecha() {
  //   const fechaFormateada = this.form
  //     .get('registerDate')
  //     ?.getRawValue()
  //     .toISOString()
  //     .split('T')[0]; // "2023-11-01"
  // }

  ngOnInit(): void {
    //Called after the constructor, initializing input properties, and the first call to ngOnChanges.
    //Add 'implements OnInit' to the class.
    this.getProveedor();
  }
  digitLengthValidator(length: number) {
    return (control: AbstractControl): ValidationErrors | null => {
      const value = control.value;
      if (value == null) return null;
      const digits = value.toString().length;
      return digits === length ? null : { digitLength: true };
    };
  }

  getProveedor() {
    const id = localStorage.getItem('idProveedor') || 0;
    const getSubscription = this.service
      .getProveedorById(Number(id))
      .subscribe({
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
    if (this.form.valid) {
      const entity: any = this.form.value;
      entity.id = localStorage.getItem('idProveedor');
      console.log(entity);
      const addSubscription = this.service.putProveedor(entity).subscribe({
        next: () => {
          Swal.fire({
            icon: 'success',
            title: '¡Proveedor actualizado!',
            text: 'El proveedor fue actualizado exitosamente',
            confirmButtonColor: '#3085d6',
          });

          this.router.navigate(['/proveedores']);
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
