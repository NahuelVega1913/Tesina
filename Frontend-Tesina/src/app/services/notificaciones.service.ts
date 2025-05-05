import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root',
})
export class NotificacionesService {
  private readonly http: HttpClient = inject(HttpClient);

  constructor() {}

  getAllNotifications() {
    return this.http.get<any>(
      `http://localhost:8080/notifications/getAllNotifications`,
      {
        headers: {
          Authorization: 'Bearer ' + localStorage.getItem('token'),
          'Content-Type': 'application/json',
        },
      }
    );
  }
}
