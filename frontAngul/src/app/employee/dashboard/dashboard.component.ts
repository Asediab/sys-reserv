import {Component, OnInit} from '@angular/core';
import {AuthService} from '../../services/auth.service';
import {ReservationService} from '../../services/reservation.service';
import {EstablishmentService} from '../../services/establishment.service';
import {Establishment, Reservation} from '../../shared/interfaces';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {AlertService} from '../../shared/services/alert.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {

  reservations: Reservation[];

  establishment: Establishment;

  form: FormGroup;

  page = 1;

  constructor(private auth: AuthService,
              private reservationService: ReservationService,
              private establishmentService: EstablishmentService,
              private alertService: AlertService) {
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
    });
  }

  submit(): void {
    if (this.form.valid) {
      this.reservationService.validateReservation(this.form.value.validNumber)
        .subscribe(v => {
          this.form.reset();
          this.alertService.success('Reservation est validée');
          this.fetchReservations();
        });
    }
  }
}


