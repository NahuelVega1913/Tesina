import { Component, inject } from '@angular/core';
import {
  ReactiveFormsModule,
  UntypedFormControl,
  UntypedFormGroup,
} from '@angular/forms';
import { Router } from '@angular/router';
import { RespuestosService } from '../services/respuestos.service';
import { NgClass } from '@angular/common';
import { ProveedoresService } from '../services/proveedores.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-modificar-repuesto',
  imports: [NgClass, ReactiveFormsModule],
  templateUrl: './modificar-repuesto.component.html',
  styleUrl: './modificar-repuesto.component.css',
})
export class ModificarRepuestoComponent {
  private service: RespuestosService = inject(RespuestosService);
  private serviceProviders: ProveedoresService = inject(ProveedoresService);

  repuesto: any = {};
  lstProviders: any[] = [];

  selectedFiles: File[] = [];
  constructor(private router: Router) {}

  form = new UntypedFormGroup({
    name: new UntypedFormControl('', []),
    price: new UntypedFormControl('', []),
    discaunt: new UntypedFormControl('', []),
    stock: new UntypedFormControl('', []),
    brand: new UntypedFormControl('', []),
    stars: new UntypedFormControl('', []),
    category: new UntypedFormControl('', []),
    provider: new UntypedFormControl('', []),
    urlImage: new UntypedFormControl('', []),
    city: new UntypedFormControl('', []),
    description: new UntypedFormControl('', []),
  });

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

  ngOnInit(): void {
    //Called after the constructor, initializing input properties, and the first call to ngOnChanges.
    //Add 'implements OnInit' to the class.
    this.getRepuesto();
    this.getProviders();
  }

  getRepuesto() {
    const id = localStorage.getItem('idRepuesto') || 0;
    const getSubscription = this.service.getSpareById(Number(id)).subscribe({
      next: (res) => {
        this.repuesto = res;
        this.form.patchValue({
          name: this.repuesto.name,
          price: this.repuesto.price,
          discaunt: this.repuesto.discaunt,
          provider: this.repuesto.provider,
          stock: this.repuesto.stock,
          brand: this.repuesto.brand,
          stars: this.repuesto.stars,
          category: this.repuesto.category,
          urlImage: this.repuesto.urlImage,
          city: this.repuesto.city,
          description: this.repuesto.description,
        });
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
      formData.append('providerId', this.form.value.provider || '');
      formData.append('id', localStorage.getItem('idRepuesto') || '');
      formData.append('stars', this.form.value.stars || '');
      formData.append('stock', this.form.value.stock || '');
      formData.append('brand', this.form.value.brand || '');
      formData.append('category', this.form.value.category || '');
      formData.append('description', this.form.value.description || '');

      // Adjuntar imágenes
      this.selectedFiles.forEach((file, index) => {
        formData.append(`image${index + 1}`, file);
      });

      const addSubscription = this.service.putSpare(formData).subscribe({
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
