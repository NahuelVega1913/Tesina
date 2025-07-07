import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root',
})
export class CommentsService {
  urlBase: string = 'http://localhost:8080/api/';

  constructor() {}
}
