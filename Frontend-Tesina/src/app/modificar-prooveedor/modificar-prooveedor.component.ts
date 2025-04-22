import { Component, inject } from '@angular/core';
import {
  ReactiveFormsModule,
  UntypedFormControl,
  UntypedFormGroup,
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
    cuit: new UntypedFormControl('', []),
    phone: new UntypedFormControl('', []),
    adress: new UntypedFormControl('', []),
    category: new UntypedFormControl('', []),
    sate: new UntypedFormControl('', []),

    email: new UntypedFormControl('', []),
    country: new UntypedFormControl('', []),
    city: new UntypedFormControl('', []),
    remarks: new UntypedFormControl('', []),
  });

  ngOnInit(): void {
    //Called after the constructor, initializing input properties, and the first call to ngOnChanges.
    //Add 'implements OnInit' to the class.
    this.getProveedor();
  }

  getProveedor() {
    const id = localStorage.getItem('idProveedor') || 0;
    const getSubscription = this.service
      .getProveedorById(Number(id))
      .subscribe({
        next: (res) => {
          this.form.patchValue(res);
          console.log(this.form.value);
        },
        error: (err) => {
          console.log(err);
        },
      });
  }

  save() {
    if (this.form.valid) {
      this.form
        .get('city')
        ?.setValue(this.form.get('city')?.value.toUpperCase());
      const entity: any = this.form.value;
      console.log(entity);
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
