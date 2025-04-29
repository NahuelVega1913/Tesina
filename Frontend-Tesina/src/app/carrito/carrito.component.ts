import { Component, inject } from '@angular/core';
import { CartService } from '../services/cart.service';
import { map } from 'rxjs';
import Swal from 'sweetalert2';
import {
  FormsModule,
  UntypedFormArray,
  UntypedFormControl,
  UntypedFormGroup,
} from '@angular/forms';

@Component({
  selector: 'app-carrito',
  imports: [FormsModule],
  templateUrl: './carrito.component.html',
  styleUrl: './carrito.component.css',
})
export class CarritoComponent {
  private service: CartService = inject(CartService);
  carrito: any[] = [];
  productos: any[] = [];
  form = new UntypedFormGroup({
    Cantidades: new UntypedFormArray([]),
  });

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
          cantidad: 1,
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
      subtotal += producto.price * producto.cantidad;
    });
    return subtotal;
  }
  calculateDiscaunt() {
    let total = 0;
    this.productos.forEach((producto) => {
      const descuento =
        (producto.discaunt / 100) * producto.price * producto.cantidad;
      const precioConDescuento = producto.price * producto.cantidad;
      total += descuento;
    });
    return total;
  }
  calculateTotal() {
    let total = 0;
    this.productos.forEach((producto) => {
      const descuento = (producto.discaunt / 100) * producto.price;
      const precioConDescuento = producto.price - descuento;
      total += precioConDescuento * producto.cantidad;
    });
    return total;
  }
  decrementarUnidad(index: number) {
    if (this.productos[index].cantidad <= 1) {
    } else {
      this.productos[index].cantidad--;
    }
  }
  aumentarUnidad(index: number) {
    if (this.productos[index].cantidad >= 5) {
    } else {
      this.productos[index].cantidad++;
    }
  }
}
