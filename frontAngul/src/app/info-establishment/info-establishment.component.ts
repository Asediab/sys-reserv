import {Component, OnInit} from '@angular/core';
import {Establishment, Reservation} from '../shared/interfaces';
import {DataEstablishmentService} from '../shared/services/data-establishment.service';
import {EstablishmentService} from '../services/establishment.service';
import {ActivatedRoute, Params} from '@angular/router';
import {NgbDate, NgbDateStruct, NgbModal, NgbTimeStruct} from '@ng-bootstrap/ng-bootstrap';
import {FormControl, FormGroup, Validators} from '@angular/forms';
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

  textModal: string;

  model: NgbDate;

  resOk = false;

  support = false;

  reservation: Reservation;

  establishment: Establishment;

  reservationsNearestDispon: Reservation[];

  constructor(private dataEstablishmentService: DataEstablishmentService,
              private reservationService: ReservationService,
              private establishmentService: EstablishmentService,
              private route: ActivatedRoute,
              private authService: AuthService,
              private alertService: AlertService,
              private modalService: NgbModal) {
    this.establishment = dataEstablishmentService.establishment;
  }

  ngOnInit(): void {
    this.form = new FormGroup({});
    if (!this.establishment) {
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
    this.reservation = {
      startOfReservation: dateStart,
      endOfReservation: dateEnd,
      establishmentId: this.establishment.id,
      establishmentName: this.establishment.name,
      userFirstName: this.authService.user.firstName,
      userId: this.authService.user.id,
    };
  }

  save(): void {
    if (this.checkWorkingHours(this.reservation)) {
      this.reservationService.saveReservation(this.reservation, this.establishment.clients_limit).subscribe(() => {
        this.timeStart = {hour: 0, minute: 0, second: 0};
        this.timeEnd = {hour: 0, minute: 0, second: 0};
        this.alertService.success('Réservation créée');
        this.support = false;
      }, error => {
        this.reservationService.getListNearestDispon(this.reservation, this.establishment.clients_limit).subscribe(v => {
          this.reservationsNearestDispon = v;
          this.support = true;
          console.log(v);
        });
      });
    } else {
      this.alertService.warning('Vous ne pouvez pas faire de réservation pour les heures non ouvrées');
    }
    this.modalService.dismissAll();
  }

  private checkWorkingHours(reservation: Reservation): boolean {
    if (this.checkEndReservNotLesStartReserv(reservation.startOfReservation, reservation.endOfReservation)) {
      switch (reservation.startOfReservation.getDay()) {
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
          return true;
        }
      }
    } else {
      this.alertService.success('L\'heure de fin de la réservation ne peut pas être antérieure de début de la réservation');
    }
  }

  private compareDate(dateReserv: Date, date2: Date): number {
    const dateTimeTable: Date = new Date(date2);
    const dateRes: Date = new Date(dateReserv);
    const dateCompTimeTable: Date = new Date(dateRes.getFullYear(), dateRes.getMonth(), dateRes.getDate(), dateTimeTable.getHours(),
      dateTimeTable.getMinutes());

    const same = dateRes.getTime() === dateCompTimeTable.getTime();
    if (same) {
      return 0;
    }

    // Check if the first is greater than second
    if (dateRes > dateCompTimeTable ) {
      return 1;
    }

    // Check if the first is less than second
    if (dateRes < dateCompTimeTable) {
      return -1;
    }
  }

  private checkEndReservNotLesStartReserv(start: Date, end: Date): boolean {
    const date1 = new Date(start);
    const date2 = new Date(end);
    if (date1.getTime() === date2.getTime() && date1 > date2) {
      return false;
    }
    if (date1 < date2) {
      return true;
    }
  }

  // tslint:disable-next-line:max-line-length
  private compareDateByTimeTable(reservation: Reservation, dateAMStart: Date, dateAMEnd: Date, datePMStart: Date, datePMEnd: Date): boolean {
    // tslint:disable-next-line:max-line-length
    if (((this.compareDate(reservation.startOfReservation, dateAMStart) === 1 || this.compareDate(reservation.startOfReservation, dateAMStart) === 0) &&
      // tslint:disable-next-line:max-line-length
      (this.compareDate(reservation.startOfReservation, dateAMEnd) === -1 || this.compareDate(reservation.startOfReservation, dateAMEnd) === 0))
      &&
      // tslint:disable-next-line:max-line-length
      ((this.compareDate(reservation.endOfReservation, dateAMStart) === 1 || this.compareDate(reservation.endOfReservation, dateAMStart) === 0) &&
        // tslint:disable-next-line:max-line-length
        ((this.compareDate(reservation.endOfReservation, dateAMEnd) === -1 || this.compareDate(reservation.endOfReservation, dateAMEnd) === 0)))) {
      return true;
      // tslint:disable-next-line:max-line-length
    } else if (((this.compareDate(reservation.startOfReservation, datePMStart) === 1 || this.compareDate(reservation.startOfReservation, datePMStart) === 0) &&
      // tslint:disable-next-line:max-line-length
      (this.compareDate(reservation.startOfReservation, datePMEnd) === -1 || this.compareDate(reservation.startOfReservation, datePMEnd) === 0))
      &&
      // tslint:disable-next-line:max-line-length
      ((this.compareDate(reservation.endOfReservation, datePMStart) === 1 || this.compareDate(reservation.endOfReservation, datePMStart) === 0) &&
        // tslint:disable-next-line:max-line-length
        ((this.compareDate(reservation.endOfReservation, datePMEnd) === -1 || this.compareDate(reservation.endOfReservation, datePMEnd) === 0)))) {
      return true;
    } else {
      return false;
    }
  }

  check(content): void {
    this.resOk = false;
    this.textModal = '...';
    if (this.model) {
      this.submit();
      console.log(this.reservation, this.checkWorkingHours(this.reservation));

      if (this.checkWorkingHours(this.reservation)) {
        this.reservationService.timeDispon(this.reservation, this.establishment.clients_limit).subscribe(value => {
          this.textModal = 'La réservation pour l\'heure et la date choisies est disponible.';
          this.resOk = true;
        }, error => {
          this.textModal = 'Malheureusement, pour l\'heure et la date sélectionnées, la réservation n\'est pas possible en raison du dépassement de la limite de visiteurs';
        });
      } else {
        this.textModal = 'Vous ne pouvez pas faire de réservation pour les heures non ouvrées';
      }

    } else {
      this.textModal = 'Sélectionnez la date et l\'heure souhaitées Pour vérifier les places disponibles';
    }
    this.open(content);
  }

  open(content): void {
    this.modalService.open(content, {ariaLabelledBy: 'modal-basic-title'}).result.then((result) => {
    }, (reason) => {
    });
  }
}
