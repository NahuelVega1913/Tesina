import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root',
})
export class ProveedoresService {
  constructor() {}

  private readonly http: HttpClient = inject(HttpClient);

  getProveedores() {
    return this.http.get<any>(`http://localhost:8080/providers/getAll`, {
      headers: {
        Authorization: 'Bearer ' + localStorage.getItem('token'),
        'Content-Type': 'application/json',
      },
    });
  }
  getProveedorById(id: number) {}
  registerProveedor(body: any) {
    return this.http.post<any>(
      `http://localhost:8080/providers/postProvider`,
      body,
      {
        headers: {
          Authorization: 'Bearer ' + localStorage.getItem('token'),
          'Content-Type': 'application/json',
        },
      }
    );
  }
  putProveedor(body: any) {
    return this.http.put<any>(
      `http://localhost:8080/providers/putProvider`,
      body,
      {
        headers: {
          Authorization: 'Bearer ' + localStorage.getItem('token'),
          'Content-Type': 'application/json',
        },
      }
    );
  }
}
