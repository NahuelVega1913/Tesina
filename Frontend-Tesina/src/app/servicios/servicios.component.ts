import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { ServiciosService } from '../services/servicios.service';

@Component({
  selector: 'app-servicios',
  imports: [],
  templateUrl: './servicios.component.html',
  styleUrl: './servicios.component.css',
})
export class ServiciosComponent {
  haveService: boolean = false;
  constructor(
    private router: Router,
    private serviciosService: ServiciosService
  ) {}

  redirectTo(url: string) {
    console.log('Redirecting to:', url);
    this.router.navigate([`${url}`]);
  }

  registrarReparacion(data: any) {
    // Suponiendo que tienes un servicio para registrar la reparación
    this.serviciosService.postReparacion(data).subscribe({
      next: (res) => {
        // ...código de éxito...
        this.haveService = true; // Actualiza el estado
        // Redirige si quieres: this.router.navigate(['/espera']);
      },
      error: (err) => {
        // ...manejo de error...
      },
    });
  }
}
