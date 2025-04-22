import { Component, inject } from '@angular/core';
import { Router } from '@angular/router';
import { ProveedoresService } from '../services/proveedores.service';

@Component({
  selector: 'app-proveedores',
  imports: [],
  templateUrl: './proveedores.component.html',
  styleUrl: './proveedores.component.css',
})
export class ProveedoresComponent {
  private service: ProveedoresService = inject(ProveedoresService);

  lst: any[] = [];

  constructor(private router: Router) {}
  ngOnInit(): void {
    //Called after the constructor, initializing input properties, and the first call to ngOnChanges.
    //Add 'implements OnInit' to the class.
    this.getProveedores();
  }

  getProveedores() {
    const getSubscription = this.service.getProveedores().subscribe({
      next: (res) => {
        this.lst = res;
        console.log(this.lst);
      },
      error: (err) => {
        console.log(err);
      },
    });
  }

  redirectTo(url: string) {
    this.router.navigate([`${url}`]);
  }
}
