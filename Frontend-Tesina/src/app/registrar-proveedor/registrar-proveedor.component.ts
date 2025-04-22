import { NgClass } from '@angular/common';
import { Component, inject } from '@angular/core';
import {
  ReactiveFormsModule,
  UntypedFormControl,
  UntypedFormGroup,
} from '@angular/forms';
import Swal from 'sweetalert2';
import { ProveedoresComponent } from '../proveedores/proveedores.component';
import { ProveedoresService } from '../services/proveedores.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-registrar-proveedor',
  imports: [NgClass, ReactiveFormsModule],
  templateUrl: './registrar-proveedor.component.html',
  styleUrl: './registrar-proveedor.component.css',
})
export class RegistrarProveedorComponent {
  private service: ProveedoresService = inject(ProveedoresService);

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

  save() {
    if (this.form.valid) {
      const entity: any = this.form.value;
      const addSubscription = this.service.registerProveedor(entity).subscribe({
        next: () => {
          Swal.fire({
            icon: 'success',
            title: '¡Usuario creado!',
            text: 'El proveedor fue registrado exitosamente',
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
