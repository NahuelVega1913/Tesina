import { inject, Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../enviroment';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  constructor() {}

  private readonly http: HttpClient = inject(HttpClient);

  url = environment.apiUrl;

  crear(body: any): Observable<any> {
    return this.http.post<any>(this.url + 'auth/register', body);
  }
  login(body: any): Observable<any> {
    return this.http.post<any>(this.url + 'auth/authenticate', body, {
      headers: { 'Content-Type': 'application/json' },
    });
  }
}
