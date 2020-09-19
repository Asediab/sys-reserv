import { Injectable } from '@angular/core';
import {Subject} from 'rxjs';

export type AlertType = 'suc' | 'war' | 'dang';

export interface Alert{
  type: AlertType;
  text: string;
}

@Injectable({
  providedIn: 'root'
})
export class AlertService {
  public alert$ = new Subject<Alert>();

  constructor() { }

  success(text: string): void  {
    this.alert$.next({type: 'suc', text});
  }

  warning(text: string): void {
    this.alert$.next({type: 'war', text});
  }

  danger(text: string): void {
    this.alert$.next({type: 'dang', text});
  }
}
