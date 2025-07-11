import { Component } from '@angular/core';
import { AfterViewInit } from '@angular/core';
import { Router } from '@angular/router';
declare const initFlowbite: any;
declare var window: any;
setTimeout(() => {
  if (window.initFlowbite) {
    window.initFlowbite();
  }
}, 0);
@Component({
  selector: 'app-terminos-condiciones',
  imports: [],
  templateUrl: './terminos-condiciones.component.html',
  styleUrl: './terminos-condiciones.component.css',
})
export class TerminosCondicionesComponent implements AfterViewInit {
  constructor(private router: Router) {
    this.router.events.subscribe(() => {
      setTimeout(() => initFlowbite(), 0); // Espera al render
    });
  }

  ngAfterViewInit() {
    initFlowbite();
  }
}
