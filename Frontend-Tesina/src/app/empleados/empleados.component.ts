import { Component, inject } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { EmpleadosService } from '../services/empleados.service';

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

  private service: EmpleadosService = inject(EmpleadosService);

  constructor(private router: Router) {}

  filter() {}
  ngOnInit(): void {
    //Called after the constructor, initializing input properties, and the first call to ngOnChanges.
    //Add 'implements OnInit' to the class.
    this.getEmployees();
  }

  redirectTo(url: string) {
    this.router.navigate([`${url}`]);
  }
  moveTo(url: string, id: number) {
    localStorage.setItem('idEmpleado', id.toString());
    this.router.navigate([`${url}`]);
  }

  getEmployees() {
    const getSubscription = this.service.getEmployees().subscribe({
      next: (res) => {
        this.lst = res;
        this.lstFiltered = res;
        console.log(this.lst);
      },
      error: (err) => {
        console.log(err);
      },
    });
  }
}
