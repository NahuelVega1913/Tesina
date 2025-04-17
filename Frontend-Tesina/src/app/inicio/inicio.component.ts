import { Component } from '@angular/core';

import { Router, RouterLink, RouterOutlet } from '@angular/router';

@Component({
  selector: 'app-inicio',
  imports: [RouterOutlet],
  templateUrl: './inicio.component.html',
  styleUrl: './inicio.component.css',
})
export class InicioComponent {
  constructor(private router: Router) {}

  redirectTo(url: string) {
    this.router.navigate([`${url}`]);
  }
}
