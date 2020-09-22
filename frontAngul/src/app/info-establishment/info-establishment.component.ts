import {Component, OnInit} from '@angular/core';
import {Establishment, Reservation} from '../shared/interfaces';
import {DataEstablishmentService} from '../shared/services/data-establishment.service';
import {EstablishmentService} from '../services/establishment.service';
import {ActivatedRoute, Params} from '@angular/router';
import {NgbDate, NgbDateStruct, NgbTimeStruct} from '@ng-bootstrap/ng-bootstrap';
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

  model: NgbDate;

  support = false;

  reservation: Reservation;

  establishment: Establishment;

  reservationsNearestDispon: Reservation[];

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
    const dateStart: Date = new Date(this.model.year, this.model.month - 1, this.model.day, this.timeStart.hour, this.timeStart.minute);
    const dateEnd: Date = new Date(this.model.year, this.model.month - 1, this.model.day, this.timeEnd.hour, this.timeEnd.minute);
    console.log(dateStart);
    console.log(this.model);
    this.reservation = {
      startOfReservation: dateStart,
      endOfReservation: dateEnd,
      establishmentId: this.establishment.id,
      establishmentName: this.establishment.name,
      userFirstName: this.authService.user.firstName,
      userId: this.authService.user.id,
    };
    this.save(this.reservation);
  }

  private save(reservation: Reservation): void {
    if (this.checkWorkingHours(reservation)) {
      this.reservationService.saveReservation(reservation, this.establishment.clients_limit).subscribe(() => {
        this.timeStart = {hour: 0, minute: 0, second: 0};
        this.timeEnd = {hour: 0, minute: 0, second: 0};
        this.alertService.success('Réservation créée');
        this.support = false;
      }, error => {
        this.reservationService.getListNearestDispon(reservation, this.establishment.clients_limit).subscribe(v => {
          this.reservationsNearestDispon = v;
          this.support = true;
          console.log(v);
        });
      });
    } else {
      this.alertService.warning('Vous ne pouvez pas faire de réservation pour les heures non ouvrées');
    }
  }

  private checkWorkingHours(reservation: Reservation): boolean {
    if (this.checkEndReservNotLesStartReserv(reservation.startOfReservation, reservation.endOfReservation)) {
      switch (reservation.startOfReservation.getDay()){
        case 1 : {
          return this.compareDateByTimeTable(reservation, this.dataEstablishmentService.establishment.timeTable.mondayAMStart,
            this.dataEstablishmentService.establishment.timeTable.mondayAMEnd,
            this.dataEstablishmentService.establishment.timeTable.mondayPMStart,
            this.dataEstablishmentService.establishment.timeTable.mondayPMEnd);
        }
        case 2 : {
          return this.compareDateByTimeTable(reservation, this.dataEstablishmentService.establishment.timeTable.tuesdayAMStart,
            this.dataEstablishmentService.establishment.timeTable.tuesdayAMEnd,
            this.dataEstablishmentService.establishment.timeTable.tuesdayPMStart,
            this.dataEstablishmentService.establishment.timeTable.tuesdayPMEnd);
        }
        case 3 : {
          return this.compareDateByTimeTable(reservation, this.dataEstablishmentService.establishment.timeTable.wednesdayAMStart,
            this.dataEstablishmentService.establishment.timeTable.wednesdayAMEnd,
            this.dataEstablishmentService.establishment.timeTable.wednesdayPMStart,
            this.dataEstablishmentService.establishment.timeTable.wednesdayPMEnd);
        }
        case 4 : {
          return this.compareDateByTimeTable(reservation, this.dataEstablishmentService.establishment.timeTable.thursdayAMStart,
            this.dataEstablishmentService.establishment.timeTable.thursdayAMEnd,
            this.dataEstablishmentService.establishment.timeTable.thursdayPMStart,
            this.dataEstablishmentService.establishment.timeTable.thursdayPMEnd);
        }
        case 5 : {
          return this.compareDateByTimeTable(reservation, this.dataEstablishmentService.establishment.timeTable.fridayAMStart,
            this.dataEstablishmentService.establishment.timeTable.fridayAMEnd,
            this.dataEstablishmentService.establishment.timeTable.fridayPMStart,
            this.dataEstablishmentService.establishment.timeTable.fridayPMEnd);
        }
        case 6 : {
          return this.compareDateByTimeTable(reservation, this.dataEstablishmentService.establishment.timeTable.saturdayAMStart,
            this.dataEstablishmentService.establishment.timeTable.saturdayAMEnd,
            this.dataEstablishmentService.establishment.timeTable.saturdayPMStart,
            this.dataEstablishmentService.establishment.timeTable.saturdayPMEnd);
        }
        case 0 : {
          return this.compareDateByTimeTable(reservation, this.dataEstablishmentService.establishment.timeTable.sundayAMStart,
            this.dataEstablishmentService.establishment.timeTable.sundayAMEnd,
            this.dataEstablishmentService.establishment.timeTable.sundayPMStart,
            this.dataEstablishmentService.establishment.timeTable.sundayPMEnd);
        }
        default : {
          return false;
        }
      }
    } else {
      this.alertService.success('L\'heure de fin de la réservation ne peut pas être antérieure au début de la réservation');
    }
  }

  private compareDate(dateReserv: Date, date2: Date): number {
    const dateTimeTable: Date = new Date(date2);
    const dateRes: Date = new Date(dateReserv);
    const dateCompTimeTable: Date = new Date(dateRes.getFullYear(), dateRes.getMonth(), dateRes.getDate(), dateTimeTable.getHours(),
      dateTimeTable.getMinutes());

    const same = dateRes.getTime() === dateCompTimeTable.getTime();
    if (same) { return 0; }

    // Check if the first is greater than second
    if (dateRes > dateCompTimeTable) { return 1; }

    // Check if the first is less than second
    if (dateRes < dateCompTimeTable) { return -1; }
  }

  private checkEndReservNotLesStartReserv(start: Date, end: Date): boolean {
    const date1 = new Date(start);
    const date2 = new Date(end);
    if (date1.getTime() === date2.getTime() && date1 > date2) {return false; }
    if (date1 < date2) {return true; }
  }

  // tslint:disable-next-line:max-line-length
  private compareDateByTimeTable(reservation: Reservation, dateAMStart: Date, dateAMEnd: Date, datePMStart: Date, datePMEnd: Date): boolean {
    if ((this.compareDate(reservation.startOfReservation, dateAMStart) === -1 ||
      (this.compareDate(reservation.startOfReservation, datePMEnd) === 1))
      &&
      (this.compareDate(reservation.startOfReservation, dateAMEnd) === 1 ||
        (this.compareDate(reservation.startOfReservation, datePMStart) === -1))) {
      return false;
    } else if ((this.compareDate(reservation.endOfReservation, dateAMStart) === -1 ||
      (this.compareDate(reservation.endOfReservation, datePMEnd) === 1))
      &&
      (this.compareDate(reservation.endOfReservation, dateAMEnd) === 1 ||
        (this.compareDate(reservation.endOfReservation, datePMStart) === -1))) {
      return false;
    } else {
      return true;
    }
  }
}
