import { HttpClient, HttpHeaders } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root',
})
export class CommentsService {
  urlBase: string = 'http://localhost:8080/';

  private readonly http: HttpClient = inject(HttpClient);

  getAllComments(id: number) {
    return this.http.get<any>(this.urlBase + `commnent/getcommentsbyId/` + id, {
      headers: {
        Authorization: 'Bearer ' + localStorage.getItem('token'),
        'Content-Type': 'application/json',
      },
    });
  }
  postComment(body: any) {
    return this.http.post<any>(this.urlBase + `commnent/postcomment`, body, {
      headers: {
        Authorization: 'Bearer ' + localStorage.getItem('token'),
        'Content-Type': 'application/json',
      },
    });
  }

  constructor() {}
}
