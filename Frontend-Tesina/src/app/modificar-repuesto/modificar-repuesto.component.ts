import { Component, inject } from '@angular/core';
import {
  AbstractControl,
  ReactiveFormsModule,
  UntypedFormControl,
  UntypedFormGroup,
  ValidationErrors,
  Validators,
} from '@angular/forms';
import { Router } from '@angular/router';
import { RespuestosService } from '../services/respuestos.service';
import { NgClass } from '@angular/common';
import { ProveedoresService } from '../services/proveedores.service';
import Swal from 'sweetalert2';
import { Pipe, PipeTransform } from '@angular/core';

@Pipe({ name: 'fileUrl', standalone: true })
export class FileUrlPipe implements PipeTransform {
  transform(file: File): string {
    return URL.createObjectURL(file);
  }
}

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

  selectedFiles: (File | string)[] = [];
  constructor(private router: Router) {}

  form = new UntypedFormGroup({
    name: new UntypedFormControl('', []),
    price: new UntypedFormControl('', [
      this.noNegativeValidator,
      Validators.required,
    ]),
    discaunt: new UntypedFormControl('', [
      this.noNegativeValidator,
      this.maxNumberValidator(100),
    ]),
    stock: new UntypedFormControl('', [
      this.noNegativeValidator,
      Validators.min(0),
    ]),
    brand: new UntypedFormControl('', []),
    category: new UntypedFormControl('', []),
    urlImage: new UntypedFormControl('', []),
    provider: new UntypedFormControl('', []),
    stars: new UntypedFormControl('', [
      this.noNegativeValidator,
      this.maxNumberValidator(5),
    ]),
    city: new UntypedFormControl('', []),
    description: new UntypedFormControl('', []),
  });

  maxNumberValidator(max: number) {
    return (control: AbstractControl): ValidationErrors | null => {
      const value = control.value;
      if (value == null || value === '') return null;
      const num = Number(value);
      return isNaN(num) || num > max ? { maxNumber: true } : null;
    };
  }
  getImageUrl(file: any): string {
    if (file instanceof File) {
      return URL.createObjectURL(file);
    }
    return file;
  }
  noNegativeValidator(control: AbstractControl): ValidationErrors | null {
    const value = control.value;
    if (value == null || value === '') return null;
    const num = Number(value);
    // Permite cero como valor válido
    return isNaN(num) || num < 0 ? { negativeValue: true } : null;
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
    this.getRepuesto();
    this.getProviders();
    console.log(this.repuesto.urlImages[0]);
  }

  getRepuesto() {
    const id = localStorage.getItem('idRepuesto') || 0;
    const getSubscription = this.service.getSpareById(Number(id)).subscribe({
      next: async (res) => {
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
        // Convierte las imágenes URL en File usando fetch y Blob
        if (Array.isArray(this.repuesto.urlImages)) {
          this.selectedFiles = [];
          for (let i = 0; i < this.repuesto.urlImages.length; i++) {
            const url = this.repuesto.urlImages[i];
            if (url) {
              try {
                const response = await fetch(url);
                const blob = await response.blob();
                // Extrae la extensión del archivo de la url o usa jpg por defecto
                const extMatch = url.match(/\.(\w+)(\?|$)/);
                const ext = extMatch ? extMatch[1] : 'jpg';
                const file = new File([blob], `imagen${i + 1}.${ext}`, {
                  type: blob.type,
                });
                this.selectedFiles.push(file);
              } catch (e) {
                // Si falla, ignora esa imagen
              }
            }
          }
        } else {
          this.selectedFiles = [];
        }
      },
      error: (err) => {
        console.log(err);
      },
    });
  }

  onFileChange(event: Event) {
    const input = event.target as HTMLInputElement;
    if (input.files) {
      const newFiles = Array.from(input.files);
      // Solo agrega los nuevos archivos si no están ya en selectedFiles (por nombre y tipo)
      newFiles.forEach((file) => {
        // Evita duplicados por nombre de archivo
        if (
          !this.selectedFiles.some(
            (f) => f instanceof File && (f as File).name === file.name
          )
        ) {
          this.selectedFiles.push(file);
        }
      });
    }
  }

  eliminarImagen(index: number) {
    if (this.selectedFiles && this.selectedFiles[index]) {
      this.selectedFiles.splice(index, 1);
    }
    // Si quieres que también se elimine del array original de urlImages para reflejar el cambio en el backend:
    if (this.repuesto && Array.isArray(this.repuesto.urlImages)) {
      // Busca si la imagen eliminada era una de las originales (comparando nombre o url)
      // Si es un File creado desde una url, compara el nombre del archivo con la url original
      const file = this.selectedFiles[index];
      if (file instanceof File) {
        // Busca el nombre generado en getRepuesto
        const fileName = file.name;
        // Busca la url correspondiente en urlImages
        const urlIndex = this.repuesto.urlImages.findIndex((url: string) => {
          // Extrae el nombre base de la url para comparar
          const urlNameMatch = url.match(/([^\/?#]+)(?:\?|#|$)/i);
          return urlNameMatch && fileName.includes(urlNameMatch[1]);
        });
        if (urlIndex !== -1) {
          this.repuesto.urlImages.splice(urlIndex, 1);
        }
      }
      // Si era un string (url) directamente
      if (typeof file === 'string') {
        const urlIndex = this.repuesto.urlImages.indexOf(file);
        if (urlIndex !== -1) {
          this.repuesto.urlImages.splice(urlIndex, 1);
        }
      }
    }
  }

  save() {
    if (this.form.valid) {
      const formData = new FormData();

      // Agregar campos normales
      formData.append('name', this.form.value.name || '');
      formData.append(
        'price',
        this.form.value.price != null ? this.form.value.price : ''
      );
      formData.append(
        'discaunt',
        this.form.value.discaunt != null ? this.form.value.discaunt : ''
      );
      formData.append('providerId', this.form.value.provider || '');
      formData.append('id', localStorage.getItem('idRepuesto') || '');
      formData.append(
        'stars',
        this.form.value.stars != null ? this.form.value.stars : ''
      );
      formData.append(
        'stock',
        this.form.value.stock != null ? this.form.value.stock : ''
      );
      formData.append('brand', this.form.value.brand || '');
      formData.append('category', this.form.value.category || '');
      formData.append('description', this.form.value.description || '');

      // Siempre envía image1, image2, image3, image4 en orden, usando '' si no hay imagen
      for (let i = 0; i < 4; i++) {
        const file = this.selectedFiles[i];
        if (file instanceof File) {
          formData.append(`image${i + 1}`, file);
        } else if (typeof file === 'string' && file) {
          formData.append(`image${i + 1}`, file);
        } else {
          formData.append(`image${i + 1}`, '');
        }
      }

      const addSubscription = this.service.putSpare(formData).subscribe({
        next: () => {
          Swal.fire({
            icon: 'success',
            title: '¡Repuesto modificado!',
            text: 'El repuesto fue modificado exitosamente',
            confirmButtonColor: '#3085d6',
          });

          this.router.navigate(['/repuestos']);
        },
        error: (err) => {
          Swal.fire({
            icon: 'error',
            title: 'Oops...',
            text: 'Ocurrió un error al modificar el repuesto',
          });
        },
      });
    } else {
      this.form.markAllAsTouched();
      Swal.fire({
        icon: 'error',
        title: 'Oops...',
        text: 'Completa todos los campos requeridos',
      });
    }
  }
}
