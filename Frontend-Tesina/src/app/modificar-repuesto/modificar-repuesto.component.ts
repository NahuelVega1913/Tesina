import { Component, inject } from '@angular/core';
import {
  ReactiveFormsModule,
  UntypedFormControl,
  UntypedFormGroup,
} from '@angular/forms';
import { Router } from '@angular/router';
import { RespuestosService } from '../services/respuestos.service';
import { NgClass } from '@angular/common';

@Component({
  selector: 'app-modificar-repuesto',
  imports: [NgClass, ReactiveFormsModule],
  templateUrl: './modificar-repuesto.component.html',
  styleUrl: './modificar-repuesto.component.css',
})
export class ModificarRepuestoComponent {
  private service: RespuestosService = inject(RespuestosService);
  repuesto: any = {};

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

  ngOnInit(): void {
    //Called after the constructor, initializing input properties, and the first call to ngOnChanges.
    //Add 'implements OnInit' to the class.
    this.getRepuesto();
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

  save() {}
}
