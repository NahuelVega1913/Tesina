import { Component, inject } from '@angular/core';
import { RespuestosService } from '../services/respuestos.service';
import { Router } from '@angular/router';
import { NgScrollbarModule } from 'ngx-scrollbar';
import { FormsModule, NgModel } from '@angular/forms';
import { ServiciosService } from '../services/servicios.service';

@Component({
  selector: 'app-consultar-servicios',
  imports: [NgScrollbarModule, FormsModule],
  templateUrl: './consultar-servicios.component.html',
  styleUrl: './consultar-servicios.component.css',
})
export class ConsultarServiciosComponent {
  constructor(private router: Router) {}
  lst: any[] = [];
  listFilteres: any[] = [];
  counter: number = 0;
  status: string = '';
  search: string = '';
  paymentStatus: string = ''; // Se añade la variable para el estado de pago

  private service: ServiciosService = inject(ServiciosService);
  rol: string | null = '';
  isOpen = false;
  filter() {
    this.listFilteres = this.lst.filter((item) => {
      // Filtro por nombre del auto
      const matchesName =
        this.search === '' ||
        (item.auto &&
          item.auto.toLowerCase().includes(this.search.toLowerCase()));
      // Filtro por estado del servicio
      const matchesStatus = this.status === '' || item.status === this.status;
      // Filtro por estado de pago (comparación estricta y uppercase)
      const matchesPayment =
        this.paymentStatus === '' ||
        (item.paymentStatus &&
          item.paymentStatus.toUpperCase() ===
            this.paymentStatus.toUpperCase());
      return matchesName && matchesStatus && matchesPayment;
    });
    // Forzar actualización de la vista
    this.listFilteres = [...this.listFilteres];
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
    this.rol = localStorage.getItem('role');
  }

  redirectTo(url: string, id: number) {
    window.location.href = url;
    localStorage.setItem('status', 'IN_QUEUE');
    localStorage.setItem('idServicio', id.toString());
    // this.router.navigate([`${url}`]);
  }
  redirectToProccess(url: string, id: number) {
    window.location.href = url;
    localStorage.setItem('status', 'PROCESS');
    localStorage.setItem('idServicio', id.toString());
  }
  redirectToTo(url: string) {
    window.location.href = url;
    // this.router.navigate([`${url}`]);
  }
  moveTo(id: number, type: string) {
    localStorage.setItem('idServicio', id.toString());
    if (type == 'CUSTOMIZATION') {
      this.router.navigate(['/consultar-customizacion']);
    } else if (type == 'REPAIR') {
      this.router.navigate(['/consultar-reparacion']);
    } else if (type == 'INSPECTION') {
      this.router.navigate(['/consultar-inspeccion']);
    }
  }
  retireCar(id: number) {
    const getSubscription = this.service.retireCar(id).subscribe({
      next: (res) => {
        this.getProveedores();
      },
      error: (err) => {
        console.log(err);
      },
    });
  }
  pagar(id: number) {
    const getSubscription = this.service.pagarEfectivo(id).subscribe({
      next: (res) => {
        this.getProveedores();
      },
      error: (err) => {
        console.log(err);
      },
    });
  }

  getProveedores() {
    const getSubscription = this.service.getServices().subscribe({
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
  registeEntry(id: number) {
    const getSubscription = this.service.registrarIngreso(id).subscribe({
      next: (res) => {
        console.log(this.lst);
        this.getProveedores();
      },
      error: (err) => {
        console.log(err);
      },
    });
  }
}
