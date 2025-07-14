import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { environment } from '../../enviroment';

@Injectable({
  providedIn: 'root',
})
export class ProveedoresService {
  constructor() {}

  private readonly http: HttpClient = inject(HttpClient);
  url = environment.apiUrl;

  getProveedores() {
    return this.http.get<any>(this.url + `providers/getAll`, {
      headers: {
        Authorization: 'Bearer ' + localStorage.getItem('token'),
        'Content-Type': 'application/json',
      },
    });
  }

  getProveedorById(id: number) {
    return this.http.get<any>(this.url + `providers/getProvider/${id}`, {
      headers: {
        Authorization: 'Bearer ' + localStorage.getItem('token'),
        'Content-Type': 'application/json',
      },
    });
  }

  registerProveedor(body: any) {
    return this.http.post<any>(this.url + `providers/postProvider`, body, {
      headers: {
        Authorization: 'Bearer ' + localStorage.getItem('token'),
        'Content-Type': 'application/json',
      },
    });
  }
  putProveedor(body: any) {
    return this.http.put<any>(this.url + `providers/putProvider`, body, {
      headers: {
        Authorization: 'Bearer ' + localStorage.getItem('token'),
        'Content-Type': 'application/json',
      },
    });
  }
}
