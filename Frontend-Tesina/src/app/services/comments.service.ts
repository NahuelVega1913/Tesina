import { HttpClient, HttpHeaders } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { environment } from '../../enviroment';

@Injectable({
  providedIn: 'root',
})
export class CommentsService {
  url = environment.apiUrl;

  private readonly http: HttpClient = inject(HttpClient);

  getAllComments(id: number) {
    return this.http.get<any>(this.url + `commnent/getcommentsbyId/` + id, {
      headers: {
        Authorization: 'Bearer ' + localStorage.getItem('token'),
        'Content-Type': 'application/json',
      },
    });
  }
  postComment(body: any) {
    return this.http.post<any>(this.url + `commnent/postcomment`, body, {
      headers: {
        Authorization: 'Bearer ' + localStorage.getItem('token'),
        'Content-Type': 'application/json',
      },
    });
  }

  constructor() {}
}
