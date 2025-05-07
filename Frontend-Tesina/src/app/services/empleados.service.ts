import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root',
})
export class EmpleadosService {
  private readonly http: HttpClient = inject(HttpClient);

  constructor() {}

  getEmployees() {
    return this.http.get<any>(`http://localhost:8080/employee/getAll`, {
      headers: {
        Authorization: 'Bearer ' + localStorage.getItem('token'),
        'Content-Type': 'application/json',
      },
    });
  }
  getEmployee(id: number) {
    return this.http.get<any>(
      `http://localhost:8080/employee/getEmployee/` + id,
      {
        headers: {
          Authorization: 'Bearer ' + localStorage.getItem('token'),
          'Content-Type': 'application/json',
        },
      }
    );
  }
  registerEmployee(body: any) {
    return this.http.post<any>(
      `http://localhost:8080/employee/postEmployee`,
      body,
      {
        headers: {
          Authorization: 'Bearer ' + localStorage.getItem('token'),
          'Content-Type': 'application/json',
        },
      }
    );
  }
  putEmployee(body: any) {
    return this.http.put<any>(
      `http://localhost:8080/employee/putEmployee`,
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
