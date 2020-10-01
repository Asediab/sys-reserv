import {Component, OnInit} from '@angular/core';
import {AuthService} from '../../services/auth.service';
import {ReservationService} from '../../services/reservation.service';
import {EstablishmentService} from '../../services/establishment.service';
import {Establishment, Reservation} from '../../shared/interfaces';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {AlertService} from '../../shared/services/alert.service';
import {NgbDate, NgbModal} from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {

  reservations: Reservation[];

  reservationsAll: Reservation[];

  establishment: Establishment;

  reservationValid: Reservation;

  form: FormGroup;

  model: NgbDate;

  page = 1;

  constructor(private auth: AuthService,
              private reservationService: ReservationService,
              private establishmentService: EstablishmentService,
              private alertService: AlertService,
              private modalService: NgbModal) {
  }

  ngOnInit(): void {
    this.form = new FormGroup({
      validNumber: new FormControl('', Validators.required),
    });
    this.establishmentService.getById(this.auth.user.establishmentId)
      .subscribe(establishment => {this.establishment = establishment; });
    this.fetchReservations();
  }

  fetchReservations(): void {
    this.reservationService.getByEstablishment(this.auth.user.establishmentId).subscribe(reser => {
      this.reservations = reser;
      this.reservations.sort((a: Reservation, b: Reservation) => {
        return +new Date(a.startOfReservation) - +new Date(b.startOfReservation);
      });
      this.reservationsAll = this.reservations;
    });
  }

  submit(): void {
    if (this.form.valid) {
      this.reservationService.validateReservation(this.form.value.validNumber)
        .subscribe(v => {
          this.form.reset();
          this.modalService.dismissAll();
          this.alertService.success('Reservation est validée');
          this.fetchReservations();
        });
    }
  }

  open(content): void {
    this.modalService.open(content, {ariaLabelledBy: 'modal-basic-title'}).result.then((result) => {
    }, (reason) => {
    });
    const reserv: Reservation[] = this.reservations.filter(t => t.validateNumber === this.form.value.validNumber);
    this.reservationValid = reserv[0];
  }

  find(): void {
    const dateStart: Date = new Date(this.model.year, this.model.month - 1, this.model.day);
    console.log(dateStart);
    this.reservations = this.reservationsAll.filter(value => {
      return this.compare(dateStart, value) === 0;
    });
  }

  compare(start: Date, reservation: Reservation): number {
    const date1: Date = new Date(start);
    const date2: Date = new Date(reservation.startOfReservation);
    const same = date1.getDate() === date2.getDate();
    if (same) {
      return 0;
    }
    if (date1.getDate() > date2.getDate() ) {
      return 1;
    }
    if (date1.getDate() < date2.getDate()) {
      return -1;
    }
  }
}


