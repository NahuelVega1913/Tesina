import { Component, inject } from '@angular/core';
import { RespuestosService } from '../services/respuestos.service';
import { Router } from '@angular/router';
import { CartService } from '../services/cart.service';

@Component({
  selector: 'app-repuesto',
  imports: [],
  templateUrl: './repuesto.component.html',
  styleUrl: './repuesto.component.css',
})
export class RepuestoComponent {
  constructor(private router: Router) {}
  repuesto: any = {};
  private service: RespuestosService = inject(RespuestosService);
  private cartService: CartService = inject(CartService);

  ngOnInit(): void {
    //Called after the constructor, initializing input properties, and the first call to ngOnChanges.
    //Add 'implements OnInit' to the class.
    this.getRepuesto();
  }

  getRepuesto() {
    const id = localStorage.getItem('idRepuesto') || 0;
    const getSubscription = this.service.getSpareById(Number(id)).subscribe({
      next: (res) => {
        this.repuesto = res;
      },
      error: (err) => {
        console.log(err);
      },
    });
  }
  agregarAlCarrito() {
    const idRepuesto = localStorage.getItem('idRepuesto') || 0;

    const getSubscription = this.cartService
      .registerProveedor(Number(idRepuesto))
      .subscribe({
        next: (res) => {
          this.repuesto = res;
        },
        error: (err) => {
          console.log(err);
        },
      });
  }
}
