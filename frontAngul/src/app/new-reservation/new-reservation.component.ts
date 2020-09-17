import { Component, OnInit } from '@angular/core';
import {FormGroup} from '@angular/forms';
import {DataEstablishmentService} from '../shared/services/data-establishment.service';
import {Reservation} from '../shared/interfaces';
import {ReservationService} from '../services/reservation.service';
import {NgbTimepickerConfig, NgbTimeStruct} from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-new-reservation',
  templateUrl: './new-reservation.component.html',
  styleUrls: ['./new-reservation.component.css']
})
export class NewReservationComponent implements OnInit {

  time: NgbTimeStruct = {hour: 0, minute: 0, second: 0};
  hourStep = 1;
  minuteStep = 15;
  secondStep = 30;

  form: FormGroup;

  reservation: Reservation;

  constructor(private dataEstablishmentService: DataEstablishmentService,
              private reservationService: ReservationService) {
  }

  ngOnInit(): void {
    this.form = new FormGroup({});
  }

  submit(): void {
  }
}
