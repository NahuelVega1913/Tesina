import { HttpClient, HttpHeaders } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { environment } from '../../enviroment';


@Injectable({
  providedIn: 'root',
})
export class ChatbotService {
  private readonly http: HttpClient = inject(HttpClient);

  constructor() {}
  url = environment.apiUrl;


  registerProveedor(message: string) {
    return this.http.post(
      this.url +`chatinteligence/postmessage`,
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
