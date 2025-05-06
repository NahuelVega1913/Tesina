import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'app-empleados',
  imports: [FormsModule],
  templateUrl: './empleados.component.html',
  styleUrl: './empleados.component.css',
})
export class EmpleadosComponent {
  category: string = '';
  search: string = '';
  lst: any[] = [];
  lstFiltered: any[] = [];

  constructor(private router: Router) {}

  filter() {}

  redirectTo(url: string) {
    this.router.navigate([`${url}`]);
  }
}
