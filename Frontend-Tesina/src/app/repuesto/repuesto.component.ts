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
import { CommentsService } from '../services/comments.service';

@Component({
  selector: 'app-repuesto',
  imports: [FormsModule],
  templateUrl: './repuesto.component.html',
  styleUrl: './repuesto.component.css',
})
export class RepuestoComponent {
  mp = {} as any;
  cantidad: number = 1;
  text: string = '';
  rol: any = '';

  constructor(private router: Router) {
    this.mp = new (window as any).MercadoPago(
      'APP_USR-367ee25d-7b5c-4b8d-a966-2cc25f227978',
      {
        locale: 'es-AR',
      }
    );
  }
  repuesto: any = {};
  comments: any[] = [];
  private service: RespuestosService = inject(RespuestosService);
  private cartService: CartService = inject(CartService);
  private mpService: MercadoPagoService = inject(MercadoPagoService);
  private commentService: CommentsService = inject(CommentsService);

  ngOnInit(): void {
    //Called after the constructor, initializing input properties, and the first call to ngOnChanges.
    //Add 'implements OnInit' to the class.
    this.getRepuesto();
    this.rol = localStorage.getItem('role');
    this.getAllComments();
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
  sendResponse(idComment: number) {
    const id = localStorage.getItem('idRepuesto') || 0;
    const body = {
      id: idComment,
      idSpare: Number(id),
      response: this.text,
      type: 'RESPONSE',
    };
    this.commentService.postComment(body).subscribe({
      next: (res) => {
        Swal.fire({
          icon: 'success',
          title: '¡Comentario Enviado!',
          confirmButtonColor: '#3085d6',
        });
      },
      error: (err) => {
        console.log(err);
      },
    });
    this.getAllComments();
    this.text = '';
  }
  sendComment() {
    const id = localStorage.getItem('idRepuesto') || 0;
    const body = {
      idSpare: Number(id),
      text: this.text,
      type: 'COMMENT',
    };
    this.commentService.postComment(body).subscribe({
      next: (res) => {
        Swal.fire({
          icon: 'success',
          title: '¡Comentario Enviado!',
          text: 'Responderemos lo antes posible',
          confirmButtonColor: '#3085d6',
        });
      },
      error: (err) => {
        console.log(err);
      },
    });
    this.getAllComments();
    this.text = '';
  }
  getAllComments() {
    const id = localStorage.getItem('idRepuesto') || 0;
    this.commentService.getAllComments(Number(id)).subscribe({
      next: (res) => {
        this.comments = res;
      },
      error: (err) => {
        console.log(err);
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
