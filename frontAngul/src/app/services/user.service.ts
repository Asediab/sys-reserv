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

  getUserById(id: number): Observable<User> {
    return this.http.get<User>(environment.urlUserApi + '/' + id);
  }

  getUsersByEstablishmentId(id: number): Observable<User[]> {
    return this.http.get<User[]>(environment.urlUserApi + '/establish/' + id);
  }

  currentUser(): Observable<any> {
    return this.http.get<any>(environment.userInfoUri);
  }
}
