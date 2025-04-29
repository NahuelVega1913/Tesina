import { Component, inject } from '@angular/core';
import { CartService } from '../services/cart.service';
import { map } from 'rxjs';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-carrito',
  imports: [],
  templateUrl: './carrito.component.html',
  styleUrl: './carrito.component.css',
})
export class CarritoComponent {
  private service: CartService = inject(CartService);
  carrito: any[] = [];
  productos: any[] = [];

  ngOnInit(): void {
    //Called after the constructor, initializing input properties, and the first call to ngOnChanges.
    //Add 'implements OnInit' to the class.
    this.getCarrito();
    console.log(this.productos);
  }
  getCarrito() {
    const getSubscription = this.service.getCart().subscribe({
      next: (res) => {
        console.log(res);
        this.carrito = res;
        this.productos = res.spareList.map((spare: any) => ({
          id: spare.id,
          name: spare.name,
          price: spare.price,
          discaunt: spare.discaunt,
          active: spare.active,
          image1: spare.image1,
        }));
      },
      error: (err) => {
        console.log(err);
      },
    });
  }
  putCarrito(id: number) {
    const getSubscription = this.service.putProveedor(id).subscribe({
      next: (res) => {},
      error: (err) => {
        console.log(err);
      },
    });
    location.reload();
  }

  calculateSubtotal() {
    let subtotal = 0;
    this.productos.forEach((producto) => {
      subtotal += producto.price;
    });
    return subtotal;
  }
}
