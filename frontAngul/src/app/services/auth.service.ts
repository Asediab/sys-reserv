import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {AuthResponse, Principal, User, UserLogin} from '../shared/interfaces';
import {Observable} from 'rxjs';
import {tap} from 'rxjs/operators';
import {environment} from '../../environments/environment';
import {UserService} from './user.service';
import {AlertService} from '../shared/services/alert.service';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private http: HttpClient,
              private userService: UserService,
              private alertService: AlertService) {
  }

  get token(): string {
    const expDate = new Date(localStorage.getItem('token_exp'));
    if (new Date() > expDate) {
      this.logout();
      return null;
    }
    return localStorage.getItem('token');
  }

  login(userLogin: UserLogin): Observable<any> {
    const data = `grant_type=password&username=${userLogin.email}&password=${userLogin.password}&scope=ui`;
    return this.http.post(environment.accessTokenUri, data, {
        headers: new HttpHeaders({
          Authorization: 'Basic YnJvd3NlcjpwYXNzd29yZA==',
          'Content-type': 'application/x-www-form-urlencoded; charset=utf-8'
        })
      }
    ).pipe(
      tap(this.setToken)
    );
  }

  logout(): void {
    this.setToken(null);
    this.setUser(null);
    this.alertService.success('À bie tôt!!');
  }

  isAuthenticated(): boolean {
    return !!this.token;
  }

  private setToken(response: AuthResponse | null): void {
    if (response) {
      const expDate = new Date(new Date().getTime() + +response.expires_in * 1000);
      localStorage.setItem('token', response.access_token);
      localStorage.setItem('token_exp', expDate.toString());
    } else {
      localStorage.clear();
    }
  }

  setUser(nu?: null): void {
    if (nu === null) {
      localStorage.clear();
    } else {
      this.userService.currentUser().subscribe(response => {
        const principal: Principal = response;
        const user: User = {
          establishmentId: principal.principal.establishmentId,
          firstName: principal.principal.firstName,
          id: principal.principal.id,
          lastName: principal.principal.lastName,
          roles: principal.principal.roles,
        };
        localStorage.setItem('user', JSON.stringify(user));
      });
    }
  }

  get user(): User {
    return JSON.parse(localStorage.getItem('user'));
  }
}
