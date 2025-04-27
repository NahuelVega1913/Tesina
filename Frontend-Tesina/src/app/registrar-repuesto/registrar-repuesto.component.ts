import { NgClass } from '@angular/common';
import { Component, inject } from '@angular/core';
import {
  ReactiveFormsModule,
  UntypedFormControl,
  UntypedFormGroup,
} from '@angular/forms';
import { Router } from '@angular/router';
import Swal from 'sweetalert2';
import { RespuestosService } from '../services/respuestos.service';
import { ProveedoresService } from '../services/proveedores.service';

@Component({
  selector: 'app-registrar-repuesto',
  imports: [NgClass, ReactiveFormsModule],
  templateUrl: './registrar-repuesto.component.html',
  styleUrl: './registrar-repuesto.component.css',
})
export class RegistrarRepuestoComponent {
  private service: RespuestosService = inject(RespuestosService);
  private serviceProviders: ProveedoresService = inject(ProveedoresService);

  selectedFiles: File[] = [];
  lstProviders: any[] = [];

  constructor(private router: Router) {}

  form = new UntypedFormGroup({
    name: new UntypedFormControl('', []),
    price: new UntypedFormControl('', []),
    discaunt: new UntypedFormControl('', []),
    stock: new UntypedFormControl('', []),
    brand: new UntypedFormControl('', []),
    category: new UntypedFormControl('', []),
    urlImage: new UntypedFormControl('', []),
    provider: new UntypedFormControl('', []),
    stars: new UntypedFormControl('', []),
    city: new UntypedFormControl('', []),
    description: new UntypedFormControl('', []),
  });
  ngOnInit(): void {
    //Called after the constructor, initializing input properties, and the first call to ngOnChanges.
    //Add 'implements OnInit' to the class.
    this.getProviders();
  }

  onFileChange(event: Event) {
    const input = event.target as HTMLInputElement;
    if (input.files) {
      this.selectedFiles = Array.from(input.files);
    }
  }
  getProviders() {
    const getSubscription = this.serviceProviders.getProveedores().subscribe({
      next: (res) => {
        this.lstProviders = res;
        console.log(this.lstProviders);
      },
      error: (err) => {
        console.log(err);
      },
    });
  }

  save() {
    if (this.form.valid) {
      const formData = new FormData();

      // Agregar campos normales
      formData.append('name', this.form.value.name || '');
      formData.append('price', this.form.value.price || '');
      formData.append('discaunt', this.form.value.discaunt || '');
      formData.append('stars', this.form.value.stars || '');
      formData.append('idProvider', this.form.value.provider || '');
      formData.append('stock', this.form.value.stock || '');
      formData.append('brand', this.form.value.brand || '');
      formData.append('category', this.form.value.category || '');
      formData.append('description', this.form.value.description || '');
      formData.append('city', this.form.value.city || '');

      // Adjuntar imágenes
      this.selectedFiles.forEach((file, index) => {
        formData.append(`image${index + 1}`, file);
      });

      // Enviar al backend
      const addSubscription = this.service.postSpare(formData).subscribe({
        next: () => {
          Swal.fire({
            icon: 'success',
            title: '¡Repuesto creado!',
            text: 'El repuesto fue registrado exitosamente',
            confirmButtonColor: '#3085d6',
          });

          this.router.navigate(['/repuestos']);
        },
        error: (err) => {
          Swal.fire({
            icon: 'error',
            title: 'Oops...',
            text: 'Ocurrió un error al registrar el repuesto',
          });
        },
      });
    }
  }
}
