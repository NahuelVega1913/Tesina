import { HttpClient, HttpHeaders } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root',
})
export class ServiciosService {
  private readonly http: HttpClient = inject(HttpClient);

  constructor() {}

  getServiceStatus() {
    return this.http.get<any>(
      `http://localhost:8080/services/getStatusService`,
      {
        headers: {
          Authorization: 'Bearer ' + localStorage.getItem('token'),
          'Content-Type': 'application/json',
        },
      }
    );
  }
  registrarIngreso(id: number) {
    const headers = new HttpHeaders({
      Authorization: 'Bearer ' + localStorage.getItem('token'),
      'Content-Type': 'application/json',
    });

    return this.http.post<any>(
      `http://localhost:8080/services/registerEntry/` + id,
      {},
      { headers }
    );
  }
  postService(body: any) {
    return this.http.post<any>(
      `http://localhost:8080/services/postService`,
      body,
      {
        headers: {
          Authorization: 'Bearer ' + localStorage.getItem('token'),
          'Content-Type': 'application/json',
        },
      }
    );
  }
  postReparacion(body: any) {
    return this.http.post<any>(
      `http://localhost:8080/services/postRepair`,
      body,
      {
        headers: {
          Authorization: 'Bearer ' + localStorage.getItem('token'),
          'Content-Type': 'application/json',
        },
      }
    );
  }
  postCustomizacion(body: any) {
    return this.http.post<any>(
      `http://localhost:8080/services/postCustomization`,
      body,
      {
        headers: {
          Authorization: 'Bearer ' + localStorage.getItem('token'),
          'Content-Type': 'application/json',
        },
      }
    );
  }
  getServices() {
    return this.http.get<any>(`http://localhost:8080/services/getAll`, {
      headers: {
        Authorization: 'Bearer ' + localStorage.getItem('token'),
        'Content-Type': 'application/json',
      },
    });
  }
  putService() {}
}
