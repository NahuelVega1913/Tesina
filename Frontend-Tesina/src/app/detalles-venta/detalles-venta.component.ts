import { Component, inject } from '@angular/core';
import { MercadoPagoService } from '../services/mercado-pago.service';

@Component({
  selector: 'app-detalles-venta',
  imports: [],
  templateUrl: './detalles-venta.component.html',
  styleUrl: './detalles-venta.component.css',
})
export class DetallesVentaComponent {
  private service: MercadoPagoService = inject(MercadoPagoService);
}
