import {Component, OnInit} from '@angular/core';
import {Establishment, Reservation} from '../shared/interfaces';
import {DataEstablishmentService} from '../shared/services/data-establishment.service';
import {EstablishmentService} from '../services/establishment.service';
import {ActivatedRoute, Params} from '@angular/router';
import {NgbDateStruct, NgbTimeStruct} from '@ng-bootstrap/ng-bootstrap';
import {FormGroup} from '@angular/forms';
import {ReservationService} from '../services/reservation.service';
import {AuthService} from '../services/auth.service';
import {AlertService} from '../shared/services/alert.service';

@Component({
  selector: 'app-info-establishment',
  templateUrl: './info-establishment.component.html',
  styleUrls: ['./info-establishment.component.css']
})
export class InfoEstablishmentComponent implements OnInit {

  timeStart: NgbTimeStruct = {hour: 0, minute: 0, second: 0};
  timeEnd: NgbTimeStruct = {hour: 0, minute: 0, second: 0};
  hourStep = 1;
  minuteStep = 15;
  secondStep = 30;

  form: FormGroup;

  model: NgbDateStruct;

  reservation: Reservation;

  establishment: Establishment;

  constructor(private dataEstablishmentService: DataEstablishmentService,
              private reservationService: ReservationService,
              private establishmentService: EstablishmentService,
              private route: ActivatedRoute,
              private authService: AuthService,
              private alertService: AlertService) {
    this.establishment = dataEstablishmentService.establishment;
  }

  ngOnInit(): void {
    this.form = new FormGroup({});
    if (!this.establishment){
      this.route.params.subscribe((params: Params) => {
        this.establishmentService.getById(params.id)
          .subscribe(establishment => {
            this.establishment = establishment;
          });
        this.dataEstablishmentService.establishment = this.establishment;
      });
    }
  }

  submit(): void {
    const dateStart: Date = new Date(this.model.year, this.model.month, this.model.day, this.timeStart.hour, this.timeStart.minute);
    const dateEnd: Date = new Date(this.model.year, this.model.month, this.model.day, this.timeEnd.hour, this.timeEnd.minute);
    this.reservation = {
      startOfReservation: dateStart,
      endOfReservation: dateEnd,
      establishmentId: this.establishment.id,
      establishmentName: this.establishment.name,
      userFirstName: this.authService.user.firstName,
      userId: this.authService.user.id,
    };
    this.reservationService.saveReservation(this.reservation, this.establishment.clients_limit).subscribe(() => {
      this.timeStart = {hour: 0, minute: 0, second: 0};
      this.timeEnd = {hour: 0, minute: 0, second: 0};
      this.alertService.success('Réservation créée');
    });
  }
}
