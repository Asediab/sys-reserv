import { Injectable } from '@angular/core';
import {Establishment} from '../interfaces';

@Injectable({
  providedIn: 'root'
})
export class DataEstablishmentService {

  constructor() { }

  establishment: Establishment;
}
