import { Component, inject, numberAttribute } from '@angular/core';
import { Router } from '@angular/router';
import { takeWhile, windowCount } from 'rxjs';
import { NgScrollbarModule } from 'ngx-scrollbar';

import { RespuestosService } from '../services/respuestos.service';
import { FormsModule, NgModel } from '@angular/forms';

@Component({
  selector: 'app-repuestos',
  imports: [NgScrollbarModule, FormsModule],
  templateUrl: './repuestos.component.html',
  styleUrl: './repuestos.component.css',
})
export class RepuestosComponent {
  constructor(private router: Router) {}
  lst: any[] = [];
  listFilteres: any[] = [];
  counter: number = 0;
  category: string = '';
  search: string = '';

  private service: RespuestosService = inject(RespuestosService);

  isOpen = false;
  filter() {
    this.listFilteres = this.lst.filter((item) => {
      return (
        item.name.toLowerCase().includes(this.search.toLowerCase()) &&
        (this.category === '' || item.category === this.category)
      );
    });
  }
  count(categoryGiven: string) {
    this.counter = 0;
    for (let i = 0; i < this.listFilteres.length; i++) {
      if (this.listFilteres[i].category == categoryGiven) {
        this.counter++;
      }
    }
    return this.counter;
  }

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
        this.listFilteres = res;
        console.log(this.lst);
      },
      error: (err) => {
        console.log(err);
      },
    });
  }
}
