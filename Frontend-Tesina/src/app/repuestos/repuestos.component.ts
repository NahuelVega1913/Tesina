import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { windowCount } from 'rxjs';

@Component({
  selector: 'app-repuestos',
  imports: [],
  templateUrl: './repuestos.component.html',
  styleUrl: './repuestos.component.css',
})
export class RepuestosComponent {
  constructor(private router: Router) {}
  isOpen = false;

  change() {
    this.isOpen = !this.isOpen;
  }

  redirectTo(url: string) {
    window.location.href = url;
    // this.router.navigate([`${url}`]);
  }
}
