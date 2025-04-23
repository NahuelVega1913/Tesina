import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-repuestos',
  imports: [],
  templateUrl: './repuestos.component.html',
  styleUrl: './repuestos.component.css',
})
export class RepuestosComponent {
  constructor(private router: Router) {}

  redirectTo(url: string) {
    this.router.navigate([`${url}`]);
  }
}
