import { Component, inject } from '@angular/core';
import { RespuestosService } from '../services/respuestos.service';
import { Router } from '@angular/router';

import { CartService } from '../services/cart.service';
import {
  FormGroup,
  FormBuilder,
  Validators,
  FormsModule,
} from '@angular/forms';
import Swal from 'sweetalert2';
import { MercadoPagoService } from '../services/mercado-pago.service';

@Component({
  selector: 'app-repuesto',
  imports: [FormsModule],
  templateUrl: './repuesto.component.html',
  styleUrl: './repuesto.component.css',
})
export class RepuestoComponent {
  mp = {} as any;
  cantidad: number = 1;

  constructor(private router: Router) {
    this.mp = new (window as any).MercadoPago(
      'APP_USR-2ee82b80-dd39-4c23-a203-8574bafc82bf',
      {
        locale: 'es-AR',
      }
    );
  }
  repuesto: any = {};
  private service: RespuestosService = inject(RespuestosService);
  private cartService: CartService = inject(CartService);
  private mpService: MercadoPagoService = inject(MercadoPagoService);

  ngOnInit(): void {
    //Called after the constructor, initializing input properties, and the first call to ngOnChanges.
    //Add 'implements OnInit' to the class.
    this.getRepuesto();
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
    const id = localStorage.getItem('idRepuesto') || 0;
    const body = {
      idSpare: Number(id),
      quantity: this.cantidad,
    };
    const getSubscription = this.mpService.comprarProducto(body).subscribe({
      next: (res) => {
        window.location.href = res.init_point;
      },
      error: (err) => {
        console.log(err);
      },
    });
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
        next: () => {
          console.log('Se ejecutó el next');
          Swal.fire({
            icon: 'success',
            title: '¡Producto Agregado !',
            text: 'Disfruta de tu Visita',
            confirmButtonColor: '#3085d6',
          });
        },
        error: (err) => {
          console.log(err);
        },
      });
  }
}
