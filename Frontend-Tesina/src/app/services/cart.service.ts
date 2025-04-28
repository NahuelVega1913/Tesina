import { HttpClient, HttpHeaders } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root',
})
export class CartService {
  private readonly http: HttpClient = inject(HttpClient);

  constructor() {}

  getCart() {
    return this.http.get<any>(`http://localhost:8080/cart/get`, {
      headers: {
        Authorization: 'Bearer ' + localStorage.getItem('token'),
        'Content-Type': 'application/json',
      },
    });
  }
  registerProveedor(id: number) {
    const headers = new HttpHeaders({
      Authorization: 'Bearer ' + localStorage.getItem('token'),
      'Content-Type': 'application/json',
    });

    return this.http.post<any>(
      `http://localhost:8080/cart/addProduct/` + id,
      {},
      { headers }
    );
  }
}
