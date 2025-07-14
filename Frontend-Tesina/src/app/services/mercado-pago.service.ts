import { HttpClient, HttpHeaders } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { environment } from '../../enviroment';

@Injectable({
  providedIn: 'root',
})
export class MercadoPagoService {
  // Inicializa Mercado Pago con tu clave pública

  private readonly http: HttpClient = inject(HttpClient);
  url = environment.apiUrl;

  comprarProducto(body: any) {
    return this.http.post<any>(this.url + `sales/payProduct`, body, {
      headers: {
        Authorization: 'Bearer ' + localStorage.getItem('token'),
        'Content-Type': 'application/json',
      },
    });
  }
  comprarProductos(body: any) {
    return this.http.post<any>(this.url + `sales/payProducts`, body, {
      headers: {
        Authorization: 'Bearer ' + localStorage.getItem('token'),
        'Content-Type': 'application/json',
      },
    });
  }
  getVentas() {
    return this.http.get<any>(this.url + `sales/getAll`, {
      headers: {
        Authorization: 'Bearer ' + localStorage.getItem('token'),
        'Content-Type': 'application/json',
      },
    });
  }
  getDetails(id: number) {
    return this.http.get<any>(this.url + `sales/getDetails/${id}`, {
      headers: {
        Authorization: 'Bearer ' + localStorage.getItem('token'),
        'Content-Type': 'application/json',
      },
    });
  }
  pagarServicio(id: number) {
    const headers = new HttpHeaders({
      Authorization: 'Bearer ' + localStorage.getItem('token'),
      'Content-Type': 'application/json',
    });

    return this.http.post<any>(
      this.url + `services/payMPService/` + id,
      {},
      { headers }
    );
  }
  pagarSeña(id: number) {
    const headers = new HttpHeaders({
      Authorization: 'Bearer ' + localStorage.getItem('token'),
      'Content-Type': 'application/json',
    });

    return this.http.post<any>(
      this.url + `services/payMPSeña/` + id,
      {},
      { headers }
    );
  }

  constructor() {}
}
