import {Injectable} from '@angular/core';
import {HttpClient, HttpParams} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Reservation} from '../shared/interfaces';
import {environment} from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class ReservationService {

  constructor(private http: HttpClient) {
  }

  getByUser(id: number): Observable<Reservation[]> {
    return this.http.get<Reservation[]>(environment.urlReservationApi + '/byUser/' + id);
  }

  getByEstablishment(id: number): Observable<Reservation[]> {
    return this.http.get<Reservation[]>(environment.urlReservationApi + '/byEstabl/' + id);
  }

  saveReservation(reservation: Reservation, limit: number): Observable<void> {
    return this.http.post<void>(environment.urlReservationApi + '/add', reservation, {
      params: new HttpParams().set('limit', String(limit))
    });
  }

  getListNearestDispon(reservation: Reservation, limit: number): Observable<Reservation[]> {
    return this.http.post<Reservation[]>(environment.urlReservationApi + '/nearestAvailable', reservation, {
      params: new HttpParams().set('limit', String(limit))
    });
  }

  delete(reservationId: number): Observable<void> {
    return this.http.delete<void>(environment.urlReservationApi + '/' + reservationId);
  }

  validateReservation(validNum: string): Observable<void> {
    return this.http.get<void>(environment.urlReservationApi + '/valid/' + validNum);
  }
}
