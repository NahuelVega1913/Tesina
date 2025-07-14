import { HttpClient, HttpHeaders } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { environment } from '../../enviroment';

@Injectable({
  providedIn: 'root',
})
export class CartService {
  private readonly http: HttpClient = inject(HttpClient);

  constructor() {}
  url = environment.apiUrl;

  getCart() {
    return this.http.get<any>(this.url + `cart/get`, {
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
      this.url + `cart/addProduct/` + id,
      {},
      { headers, responseType: 'text' as 'json' }
    );
  }
  putProveedor(id: number) {
    const headers = new HttpHeaders({
      Authorization: 'Bearer ' + localStorage.getItem('token'),
      'Content-Type': 'application/json',
    });

    return this.http.put<any>(this.url + `cart/putCart/` + id, {}, { headers });
  }
}
