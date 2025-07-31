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
    return this.http.get<any>(this.url + `turnos/getall`, {
      headers: {
        Authorization: 'Bearer ' + localStorage.getItem('token'),
        'Content-Type': 'application/json',
      },
    });
  }
  getAllTurnosHoy() {
    return this.http.get<any>(this.url + `turnos/hoy`, {
      headers: {
        Authorization: 'Bearer ' + localStorage.getItem('token'),
        'Content-Type': 'application/json',
      },
    });
  }
  postTurno(body: any) {
    return this.http.post<any>(this.url + `turnos/postturno`, body, {
      headers: {
        Authorization: 'Bearer ' + localStorage.getItem('token'),
        'Content-Type': 'application/json',
      },
    });
  }
  cancelarTurno(body: any) {
    return this.http.put<any>(this.url + `turnos/cancelar`, body, {
      headers: {
        Authorization: 'Bearer ' + localStorage.getItem('token'),
        'Content-Type': 'application/json',
      },
    });
  }
}
