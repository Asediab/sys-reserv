import { Component, OnInit } from '@angular/core';
import {Reservation} from '../../shared/interfaces';
import {AuthService} from '../../services/auth.service';
import {ReservationService} from '../../services/reservation.service';
import {NgbDate} from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-history',
  templateUrl: './history.component.html',
  styleUrls: ['./history.component.css']
})
export class HistoryComponent implements OnInit {

  constructor(private auth: AuthService,
              private reservationService: ReservationService) { }

  reservations: Reservation[];

  reservationsAll: Reservation[];

  model: NgbDate;

  page = 1;

  ngOnInit(): void {
    this.fetchedReservations();
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

  findAll(): void {
    this.fetchedReservations();
  }

  fetchedReservations(): void {
    this.reservationService.getByEstablishmentActiveFalse(this.auth.user.establishmentId).subscribe(reser => {
      this.reservations = reser;
      this.reservations.sort((a: Reservation, b: Reservation) => {
        return +new Date(a.startOfReservation) - +new Date(b.startOfReservation);
      });
      this.reservationsAll = this.reservations;
    });
  }
}
