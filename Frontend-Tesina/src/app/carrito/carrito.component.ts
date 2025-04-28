import { Component, inject } from '@angular/core';
import { CartService } from '../services/cart.service';

@Component({
  selector: 'app-carrito',
  imports: [],
  templateUrl: './carrito.component.html',
  styleUrl: './carrito.component.css',
})
export class CarritoComponent {
  private service: CartService = inject(CartService);
  carrito: any[] = [];

  ngOnInit(): void {
    //Called after the constructor, initializing input properties, and the first call to ngOnChanges.
    //Add 'implements OnInit' to the class.
    this.getCarrito();
  }
  getCarrito() {
    const getSubscription = this.service.getCart().subscribe({
      next: (res) => {
        console.log(res);
        this.carrito = res;
      },
      error: (err) => {
        console.log(err);
      },
    });
  }
}
