import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { Form } from '@angular/forms';
import { environment } from '../../enviroment';

@Injectable({
  providedIn: 'root',
})
export class RespuestosService {
  private readonly http: HttpClient = inject(HttpClient);
  constructor() {}
  url = environment.apiUrl;

  getSpares() {
    return this.http.get<any>(this.url + `spares/getAll`, {
      headers: {
        Authorization: 'Bearer ' + localStorage.getItem('token'),
        'Content-Type': 'application/json',
      },
    });
  }
  getSpareById(id: number) {
    return this.http.get<any>(this.url + `spares/getSpare/${id}`, {
      headers: {
        Authorization: 'Bearer ' + localStorage.getItem('token'),
        'Content-Type': 'application/json',
      },
    });
  }
  postSpare(body: FormData) {
    return this.http.post<any>(this.url + `spares/postSpare`, body, {
      headers: {
        Authorization: 'Bearer ' + localStorage.getItem('token'),
      },
    });
  }
  putSpare(body: FormData) {
    return this.http.put<any>(this.url + `spares/putSpare`, body, {
      headers: {
        Authorization: 'Bearer ' + localStorage.getItem('token'),
      },
    });
  }
}
