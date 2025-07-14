import { HttpClient, HttpHeaders } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { environment } from '../../enviroment';

@Injectable({
  providedIn: 'root',
})
export class NotificacionesService {
  private readonly http: HttpClient = inject(HttpClient);

  constructor() {}
  url = environment.apiUrl;

  getAllNotifications() {
    return this.http.get<any>(this.url + `notifications/getAllNotifications`, {
      headers: {
        Authorization: 'Bearer ' + localStorage.getItem('token'),
        'Content-Type': 'application/json',
      },
    });
  }
  markAllAsRead() {
    const headers = new HttpHeaders({
      Authorization: 'Bearer ' + localStorage.getItem('token'),
      'Content-Type': 'application/json',
    });

    return this.http.put<any>(
      this.url + `notifications/putNotification`,
      {},
      { headers, responseType: 'text' as 'json' }
    );
  }
}
