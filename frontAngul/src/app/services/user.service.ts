import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {User} from '../shared/interfaces';
import {Observable} from 'rxjs';
import {environment} from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http: HttpClient) { }

  created(user: User): Observable<void> {
    return this.http.post<void>(environment.urlUserApi, user);
  }

  getUserById(id: number): Observable<void> {
    return this.http.get<void>(environment.urlUserApi + '/' + id);
  }

  getUserByEstablishmentId(id: number): Observable<void> {
    return this.http.get<void>(environment.urlUserApi + '/establish/' + id);
  }

  currentUser(): Observable<any> {
    return this.http.get<any>(environment.userInfoUri);
  }
}
