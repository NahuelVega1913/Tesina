import { Component, ElementRef, inject, ViewChild } from '@angular/core';
import * as L from 'leaflet';
import { Router } from '@angular/router';
import { AfterViewInit } from '@angular/core';
import 'leaflet/dist/leaflet.css';
import { ServiciosService } from '../services/servicios.service';
import { UsuarioService } from '../services/usuario.service';
import { FormsModule } from '@angular/forms';
import { NgClass } from '@angular/common';
import { MercadoPagoService } from '../services/mercado-pago.service';

@Component({
  selector: 'app-espera',
  imports: [FormsModule, NgClass],
  templateUrl: './espera.component.html',
  styleUrl: './espera.component.css',
})
export class EsperaComponent implements AfterViewInit {
  id: number = 0;
  Object: any = {};
  mp = {} as any;

  private service: ServiciosService = inject(ServiciosService);
  private mpService: MercadoPagoService = inject(MercadoPagoService);

  private userService = inject(UsuarioService);
  @ViewChild('mapContainer') mapContainer!: ElementRef;
  status: string = '';
  private mapInitialized = false;

  constructor(private router: Router) {
    this.mp = new (window as any).MercadoPago(
      'APP_USR-367ee25d-7b5c-4b8d-a966-2cc25f227978',
      {
        locale: 'es-AR',
      }
    );
  }

  ngOnInit(): void {
    this.getUserInformation();
    this.mp.bricks().create('wallet', 'wallet_container', {
      initialization: {
        preferenceId: 'YOUR_PREFERENCE_ID', // Reemplaza con tu ID de preferencia
      },
      customization: {
        texts: {
          action: 'pay',
          valueProp: 'security_details',
        },
      },
    });
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
  getServicio() {
    const getSubscription = this.service.getServiceById(this.id).subscribe({
      next: (res) => {
        this.Object = res;
      },
      error: (err) => {
        console.log(err);
      },
    });
  }
  pagarServicio() {
    const getSubscription = this.mpService.pagarServicio(this.id).subscribe({
      next: (res) => {
        window.location.href = res.init_point;
      },
      error: (err) => {
        console.log(err);
      },
    });
  }
  pagarSena() {
    const getSubscription = this.mpService.pagarSeña(this.id).subscribe({
      next: (res) => {
        window.location.href = res.init_point;
      },
      error: (err) => {
        console.log(err);
      },
    });
  }

  getUserInformation() {
    this.service.getServiceStatus().subscribe({
      next: (res) => {
        this.status = res.status;
        this.id = res.id;
        this.getServicio();
      },
      error: (err) => {
        console.log(err);
      },
    });
  }
}
