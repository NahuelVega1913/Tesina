import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { environment } from '../../enviroment';

@Injectable({
  providedIn: 'root',
})
export class TurnosService {
  private readonly http: HttpClient = inject(HttpClient);
  constructor() {}
  url = environment.apiUrl;

  getAllTurnos() {
    return this.http.get<any>(this.url + `turnos/getAll`, {
      headers: {
        Authorization: 'Bearer ' + localStorage.getItem('token'),
        'Content-Type': 'application/json',
      },
    });
  }
  postUsuario(body: any) {
    return this.http.post<any>(this.url + `turnos/postTurno`, body, {
      headers: {
        Authorization: 'Bearer ' + localStorage.getItem('token'),
        'Content-Type': 'application/json',
      },
    });
  }
}
