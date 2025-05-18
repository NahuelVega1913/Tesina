import { Component, ElementRef, inject, ViewChild } from '@angular/core';
import * as L from 'leaflet';
import { Router } from '@angular/router';
import { AfterViewInit } from '@angular/core';
import 'leaflet/dist/leaflet.css';
import { ServiciosService } from '../services/servicios.service';
import { UsuarioService } from '../services/usuario.service';
import { FormsModule } from '@angular/forms';
import { NgClass } from '@angular/common';

@Component({
  selector: 'app-espera',
  imports: [FormsModule, NgClass],
  templateUrl: './espera.component.html',
  styleUrl: './espera.component.css',
})
export class EsperaComponent implements AfterViewInit {
  constructor(private router: Router) {}
  private service: ServiciosService = inject(ServiciosService);
  private userService = inject(UsuarioService);
  @ViewChild('mapContainer') mapContainer!: ElementRef;
  status: string = '';
  private mapInitialized = false;

  ngOnInit(): void {
    this.getUserInformation();
  }

  ngAfterViewInit(): void {
    // No hagas nada acá si hay *ngIf
  }

  ngAfterViewChecked(): void {
    if (!this.mapInitialized && this.mapContainer) {
      this.initMap();
      this.mapInitialized = true;
    }
  }

  initMap() {
    const map = L.map(this.mapContainer.nativeElement).setView(
      [-31.4188, -64.2327],
      15
    );

    L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
      attribution: '&copy; OpenStreetMap contributors',
    }).addTo(map);

    L.marker([-31.4188, -64.2327]).addTo(map).bindPopup('Ubicación central');

    setTimeout(() => {
      map.invalidateSize();
    }, 100);
  }

  getUserInformation() {
    this.service.getServiceStatus().subscribe({
      next: (res) => {
        this.status = res.status;
      },
      error: (err) => {
        console.log(err);
      },
    });
  }
}
