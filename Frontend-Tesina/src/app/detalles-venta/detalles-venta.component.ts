import { Component, inject } from '@angular/core';
import { MercadoPagoService } from '../services/mercado-pago.service';
import { DatePipe } from '@angular/common';

@Component({
  selector: 'app-detalles-venta',
  imports: [DatePipe],
  templateUrl: './detalles-venta.component.html',
  styleUrl: './detalles-venta.component.css',
})
export class DetallesVentaComponent {
  venta: any = null;

  private service: MercadoPagoService = inject(MercadoPagoService);

  ngOnInit(): void {
    //Called after the constructor, initializing input properties, and the first call to ngOnChanges.
    //Add 'implements OnInit' to the class.
    this.getDetails();
  }

  getDetails() {
    const id = localStorage.getItem('idVenta');
    const getSubscription = this.service.getDetails(Number(id)).subscribe({
      next: (res) => {
        this.venta = res;
        this.venta.details = res.details.map((detail: any) => {
          return {
            ...detail,
            product: detail.product.name,
            quantity: detail.quantity,
            price: detail.price,
          };
        });
        console.log(this.venta.details);
      },
      error: (err) => {
        console.log(err);
      },
    });
  }
}
