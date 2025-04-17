import { inject, Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  constructor() {}

  private readonly http: HttpClient = inject(HttpClient);

  crear(body: any): Observable<any> {
    return this.http.post<any>('http://localhost:8080/auth/register', body);
  }
}
