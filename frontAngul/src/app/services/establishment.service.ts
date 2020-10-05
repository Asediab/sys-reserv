import { Injectable } from '@angular/core';
import {HttpClient, HttpParams} from '@angular/common/http';
import {Establishment} from '../shared/interfaces';
import {Observable} from 'rxjs';
import {environment} from '../../environments/environment';


@Injectable({
  providedIn: 'root'
})
export class EstablishmentService {

  constructor(private http: HttpClient) { }

  getAll(): Observable<Establishment[]> {
    return this.http.get<Establishment[]>(environment.urlEstablishmentApi);
  }

  search(name: string, type: string): Observable<Establishment[]> {
    const a = name ? name : '';
    const b = type ? type : '';
    let params = new HttpParams();
    params = params.append('name', a);
    params = params.append('type', b);
    console.log('Searching', params);
    return this.http.get<Establishment[]>(environment.urlEstablishmentApi, {params});
  }

  getById(id: number): Observable<Establishment>{
    return this.http.get<Establishment>(environment.urlEstablishmentApi + '/' + id);
  }

  save(establishment: Establishment, file?: File): Observable<void> {
    const formData = new FormData();
    formData.append('establishment', JSON.stringify(establishment));
    formData.append('file', file);
    return this.http.post<void>(environment.urlEstablishmentApi, formData);
  }

  // tslint:disable-next-line:typedef
  delete(establishmentId: number) {
    return this.http.delete<void>(environment.urlEstablishmentApi + '/' + establishmentId);
  }

  getImage(name: string): Observable<File> {
    return this.http.get<File>(environment.urlEstablishmentApi + '/image?name=' + name);
  }
}
