import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root',
})
export class MercadoPagoService {
  // Inicializa Mercado Pago con tu clave pública

  private readonly http: HttpClient = inject(HttpClient);

  comprarProducto(body: any) {
    return this.http.post<any>(`http://localhost:8080/sales/payProduct`, body, {
      headers: {
        Authorization: 'Bearer ' + localStorage.getItem('token'),
        'Content-Type': 'application/json',
      },
    });
  }

  constructor() {}
}
