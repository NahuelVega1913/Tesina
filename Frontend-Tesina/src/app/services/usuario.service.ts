import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { environment } from '../../enviroment';

@Injectable({
  providedIn: 'root',
})
export class UsuarioService {
  private readonly http: HttpClient = inject(HttpClient);
  constructor() {}
  url = environment.apiUrl;

  getUsuario() {
    return this.http.get<any>(this.url + `users/getUser`, {
      headers: {
        Authorization: 'Bearer ' + localStorage.getItem('token'),
        'Content-Type': 'application/json',
      },
    });
  }
  getAllUsers() {
    return this.http.get<any>(this.url + `users/getall`, {
      headers: {
        Authorization: 'Bearer ' + localStorage.getItem('token'),
        'Content-Type': 'application/json',
      },
    });
  }
  getUsuarioInformation() {
    return this.http.get<any>(this.url + `users/getUserInformation`, {
      headers: {
        Authorization: 'Bearer ' + localStorage.getItem('token'),
        'Content-Type': 'application/json',
      },
    });
  }

  getUsuarioById(id: number) {}
  getUsuarioByEmail(email: string) {}

  putUsuario(body: any) {
    return this.http.put<any>(this.url + `users/putUser`, body, {
      headers: {
        Authorization: 'Bearer ' + localStorage.getItem('token'),
        'Content-Type': 'application/json',
      },
    });
  }
}
