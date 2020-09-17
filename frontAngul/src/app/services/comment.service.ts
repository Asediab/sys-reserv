import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Comment} from '../shared/interfaces';
import {environment} from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class CommentService {

  constructor(private http: HttpClient) { }

  getById(id: number): Observable<Comment>{
    return this.http.get<Comment>(environment.urlCommentApi + '/' + id);
  }

  save(comment: Comment, file?: File): Observable<void> {
    return this.http.post<void>(environment.urlCommentApi, comment);
  }

  // tslint:disable-next-line:typedef
  delete(commentId: number) {
    return this.http.delete<void>(environment.urlCommentApi + '/' + commentId);
  }

  modify(comment: Comment): Observable<void> {
    return this.http.put<void>(environment.urlCommentApi, comment);
  }
}
