import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { Form } from '@angular/forms';

@Injectable({
  providedIn: 'root',
})
export class RespuestosService {
  private readonly http: HttpClient = inject(HttpClient);
  constructor() {}

  getSpares() {
    return this.http.get<any>(`http://localhost:8080/spares/getAll`, {
      headers: {
        Authorization: 'Bearer ' + localStorage.getItem('token'),
        'Content-Type': 'application/json',
      },
    });
  }
  getSpareById(id: number) {
    return this.http.get<any>(`http://localhost:8080/spares/getSpare/${id}`, {
      headers: {
        Authorization: 'Bearer ' + localStorage.getItem('token'),
        'Content-Type': 'application/json',
      },
    });
  }
  postSpare(body: FormData) {
    return this.http.post<any>(`http://localhost:8080/spares/postSpare`, body, {
      headers: {
        Authorization: 'Bearer ' + localStorage.getItem('token'),
      },
    });
  }
  putSpare(body: FormData) {
    return this.http.put<any>(`http://localhost:8080/spares/putSpare`, body, {
      headers: {
        Authorization: 'Bearer ' + localStorage.getItem('token'),
      },
    });
  }
}
