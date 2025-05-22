import { HttpClient, HttpHeaders } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root',
})
export class ChatbotService {
  private readonly http: HttpClient = inject(HttpClient);

  constructor() {}

  registerProveedor(message: string) {
    return this.http.post(
      `http://localhost:8080/chatinteligence/postmessage`,
      message,
      {
        headers: {
          Authorization: 'Bearer ' + localStorage.getItem('token'),
          'Content-Type': 'text/plain',
        },
        responseType: 'text' as 'json', // Para que Angular no lo trate como JSON
      }
    );
  }
}
