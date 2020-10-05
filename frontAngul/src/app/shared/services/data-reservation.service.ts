import { Injectable } from '@angular/core';
import {Reservation} from '../interfaces';

@Injectable({
  providedIn: 'root'
})
export class DataReservationService {

  constructor() { }

  reservation: Reservation;


}
