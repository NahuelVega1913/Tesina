import { HttpClient, HttpHeaders } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { environment } from '../../enviroment';

@Injectable({
  providedIn: 'root',
})
export class EmpleadosService {
  private readonly http: HttpClient = inject(HttpClient);
  url = environment.apiUrl;

  constructor() {}

  getEmployees() {
    return this.http.get<any>(this.url + `employee/getAll`, {
      headers: {
        Authorization: 'Bearer ' + localStorage.getItem('token'),
        'Content-Type': 'application/json',
      },
    });
  }
  getEmployee(id: number) {
    return this.http.get<any>(this.url + `employee/getEmployee/${id}`, {
      headers: {
        Authorization: 'Bearer ' + localStorage.getItem('token'),
        'Content-Type': 'application/json',
      },
    });
  }
  deleteEmployee(id: number) {
    const headers = new HttpHeaders({
      Authorization: 'Bearer ' + localStorage.getItem('token'),
      'Content-Type': 'application/json',
    });

    return this.http.post<any>(
      this.url + `employee/deleteEmployee/` + id,
      {},
      { headers }
    );
  }

  registerEmployee(body: any) {
    return this.http.post<any>(this.url + `employee/postEmployee`, body, {
      headers: {
        Authorization: 'Bearer ' + localStorage.getItem('token'),
        'Content-Type': 'application/json',
      },
    });
  }
  putEmployee(body: any) {
    return this.http.put<any>(this.url + `employee/putEmployee`, body, {
      headers: {
        Authorization: 'Bearer ' + localStorage.getItem('token'),
        'Content-Type': 'application/json',
      },
    });
  }
}
