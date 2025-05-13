import { Component, inject } from '@angular/core';
import * as L from 'leaflet';
import { Router } from '@angular/router';
import { AfterViewInit } from '@angular/core';
import 'leaflet/dist/leaflet.css';
import { ServiciosService } from '../services/servicios.service';
import { UsuarioService } from '../services/usuario.service';

@Component({
  selector: 'app-espera',
  imports: [],
  templateUrl: './espera.component.html',
  styleUrl: './espera.component.css',
})
export class EsperaComponent implements AfterViewInit {
  constructor(private router: Router) {}
  private service: ServiciosService = inject(ServiciosService);
  private userService = inject(UsuarioService);

  status: string = '';

  ngAfterViewInit(): void {
    if (this.status === 'WAITING') {
      const map = L.map('map').setView([-31.4188, -64.2327], 15);

      L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
        attribution: '&copy; OpenStreetMap contributors',
      }).addTo(map);

      L.marker([-31.4188, -64.2327]).addTo(map).bindPopup('Ubicación central');

      setTimeout(() => {
        map.invalidateSize();
      }, 100); // puede ajustarse si hace falta
    }
  }
  ngOnInit(): void {
    //Called after the constructor, initializing input properties, and the first call to ngOnChanges.
    //Add 'implements OnInit' to the class.
  }
  getUserInformation() {
    const getSubscription = this.userService.getUsuarioInformation().subscribe({
      next: (res) => {
        console.log(res);
        //this.notificaciones = res.notificaciones;
        //this.haveService = res.hasService;
      },
      error: (err) => {
        console.log(err);
      },
    });
  }
}
