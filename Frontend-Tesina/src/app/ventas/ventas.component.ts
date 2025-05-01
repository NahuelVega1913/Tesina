import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'app-ventas',
  imports: [FormsModule],
  templateUrl: './ventas.component.html',
  styleUrl: './ventas.component.css',
})
export class VentasComponent {
  category: string = '';
  search: string = '';
  lst: any[] = [];
  lstFiltered: any[] = [];
  filter() {}

  constructor(private router: Router) {}

  redirectTo(url: string) {
    this.router.navigate([`${url}`]);
  }
}
