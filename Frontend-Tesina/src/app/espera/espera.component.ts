import { Component } from '@angular/core';
import * as L from 'leaflet';
import { Router } from '@angular/router';
import { AfterViewInit } from '@angular/core';
import 'leaflet/dist/leaflet.css';

@Component({
  selector: 'app-espera',
  imports: [],
  templateUrl: './espera.component.html',
  styleUrl: './espera.component.css',
})
export class EsperaComponent implements AfterViewInit {
  ngAfterViewInit(): void {
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
