import { HttpClient, HttpHeaders } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { environment } from '../../enviroment';

@Injectable({
  providedIn: 'root',
})
export class ServiciosService {
  private readonly http: HttpClient = inject(HttpClient);

  constructor() {}
  registerBudget(id: number, budget: number) {
    console.log(
      'Registering budget for service ID:',
      id,
      'with budget:',
      budget
    );
    const headers = new HttpHeaders({
      Authorization: 'Bearer ' + localStorage.getItem('token'),
      'Content-Type': 'application/json',
    });

    return this.http.post<any>(this.url + `services/postbudget/${id}`, budget, {
      headers,
    });
  }
  url = environment.apiUrl;

  aceptBudget(id: number) {
    const headers = new HttpHeaders({
      Authorization: 'Bearer ' + localStorage.getItem('token'),
      'Content-Type': 'application/json',
    });

    return this.http.post<any>(
      this.url + `services/acceptBudget/` + id,
      {},
      { headers }
    );
  }
  cancelService(id: number) {
    const headers = new HttpHeaders({
      Authorization: 'Bearer ' + localStorage.getItem('token'),
      'Content-Type': 'application/json',
    });

    return this.http.post<any>(
      this.url + `services/cancelService/` + id,
      {},
      { headers }
    );
  }

  declineBudget(id: number) {
    const headers = new HttpHeaders({
      Authorization: 'Bearer ' + localStorage.getItem('token'),
      'Content-Type': 'application/json',
    });

    return this.http.post<any>(
      `http://localhost:8080/services/declineBudget/` + id,
      {},
      { headers }
    );
  }

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
  pagarEfectivo(id: number) {
    const headers = new HttpHeaders({
      Authorization: 'Bearer ' + localStorage.getItem('token'),
      'Content-Type': 'application/json',
    });

    return this.http.post<any>(
      `http://localhost:8080/services/payService/` + id,
      {},
      { headers }
    );
  }
  retireCar(id: number) {
    const headers = new HttpHeaders({
      Authorization: 'Bearer ' + localStorage.getItem('token'),
      'Content-Type': 'application/json',
    });

    return this.http.post<any>(
      `http://localhost:8080/services/retireCar/` + id,
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
  finishInspection(body: any) {
    return this.http.post<any>(
      `http://localhost:8080/services/finishInspection`,
      body,
      {
        headers: {
          Authorization: 'Bearer ' + localStorage.getItem('token'),
          'Content-Type': 'application/json',
        },
      }
    );
  }
  finishRepair(body: any) {
    return this.http.post<any>(
      `http://localhost:8080/services/finishRepair`,
      body,
      {
        headers: {
          Authorization: 'Bearer ' + localStorage.getItem('token'),
          'Content-Type': 'application/json',
        },
      }
    );
  }
  finishCustomization(body: any) {
    return this.http.post<any>(
      `http://localhost:8080/services/finishCustomization`,
      body,
      {
        headers: {
          Authorization: 'Bearer ' + localStorage.getItem('token'),
          'Content-Type': 'application/json',
        },
      }
    );
  }
  getServiceById(id: number) {
    return this.http.get<any>(
      `http://localhost:8080/services/getService/${id}`,
      {
        headers: {
          Authorization: 'Bearer ' + localStorage.getItem('token'),
          'Content-Type': 'application/json',
        },
      }
    );
  }
  pasarAProceso(id: number, idEmpleado: number) {
    const headers = new HttpHeaders({
      Authorization: 'Bearer ' + localStorage.getItem('token'),
      'Content-Type': 'application/json',
    });

    return this.http.put<any>(
      `http://localhost:8080/services/putEmployees/${id}/${idEmpleado}`,
      {}, // el cuerpo va vacío si no necesitas enviar datos
      { headers } // opciones, incluyendo headers
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
