import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root',
})
export class UsuarioService {
  private readonly http: HttpClient = inject(HttpClient);
  constructor() {}

  getUsuario() {
    return this.http.get<any>(`http://localhost:8080/usuario`, {
      headers: {
        Authorization: 'Bearer ' + localStorage.getItem('token'),
        'Content-Type': 'application/json',
      },
    });
  }

  getUsuarioById(id: number) {}
  getUsuarioByEmail(email: string) {}

  putUsuario(body: any) {
    return this.http.put<any>(
      `http://localhost:8080/usuario/${body.id}`,
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
