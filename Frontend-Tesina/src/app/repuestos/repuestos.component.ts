import { Component, inject } from '@angular/core';
import { Router } from '@angular/router';
import { windowCount } from 'rxjs';
import { NgScrollbarModule } from 'ngx-scrollbar';

import { RespuestosService } from '../services/respuestos.service';

@Component({
  selector: 'app-repuestos',
  imports: [NgScrollbarModule],
  templateUrl: './repuestos.component.html',
  styleUrl: './repuestos.component.css',
})
export class RepuestosComponent {
  constructor(private router: Router) {}
  lst: any[] = [];
  listFilteres: any[] = [];

  private service: RespuestosService = inject(RespuestosService);

  isOpen = false;

  change() {
    this.isOpen = !this.isOpen;
  }
  ngOnInit(): void {
    //Called after the constructor, initializing input properties, and the first call to ngOnChanges.
    //Add 'implements OnInit' to the class.
    this.getProveedores();
  }

  redirectTo(url: string, id: number) {
    window.location.href = url;
    localStorage.setItem('idRepuesto', id.toString());
    // this.router.navigate([`${url}`]);
  }
  redirectToTo(url: string) {
    window.location.href = url;
    // this.router.navigate([`${url}`]);
  }

  getProveedores() {
    const getSubscription = this.service.getSpares().subscribe({
      next: (res) => {
        this.lst = res;
        console.log(this.lst);
      },
      error: (err) => {
        console.log(err);
      },
    });
  }
}
