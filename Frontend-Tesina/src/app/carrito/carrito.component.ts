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
import { Router } from '@angular/router';
import { MercadoPagoService } from '../services/mercado-pago.service';

@Component({
  selector: 'app-carrito',
  imports: [FormsModule],
  templateUrl: './carrito.component.html',
  styleUrl: './carrito.component.css',
})
export class CarritoComponent {
  private service: CartService = inject(CartService);
  private mpService: MercadoPagoService = inject(MercadoPagoService);

  carrito: any[] = [];
  productos: any[] = [];
  form = new UntypedFormGroup({
    Cantidades: new UntypedFormArray([]),
  });
  mp = {} as any;

  constructor(private router: Router) {
    this.mp = new (window as any).MercadoPago(
      'APP_USR-367ee25d-7b5c-4b8d-a966-2cc25f227978',
      {
        locale: 'es-AR',
      }
    );
  }
  ngOnInit(): void {
    //Called after the constructor, initializing input properties, and the first call to ngOnChanges.
    //Add 'implements OnInit' to the class.
    this.getCarrito();
    console.log(this.productos);
    this.mp.bricks().create('wallet', 'wallet_container', {
      initialization: {
        preferenceId: 'YOUR_PREFERENCE_ID', // Reemplaza con tu ID de preferencia
      },
      customization: {
        texts: {
          action: 'pay',
          valueProp: 'security_details',
        },
      },
    });
  }
  comprarRepuesto() {
    const body = this.productos.map((producto) => ({
      idSpare: producto.id,
      quantity: producto.cantidad,
    }));

    const getSubscription = this.mpService.comprarProductos(body).subscribe({
      next: (res) => {
        window.location.href = res.init_point;
      },
      error: (err) => {
        console.log(err);
      },
    });
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
