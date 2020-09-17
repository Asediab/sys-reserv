import {Component, OnInit} from '@angular/core';
import {Reservation} from '../shared/interfaces';
import {ReservationService} from '../services/reservation.service';

@Component({
  selector: 'app-my-reservations',
  templateUrl: './my-reservations.component.html',
  styleUrls: ['./my-reservations.component.css']
})
export class MyReservationsComponent implements OnInit {

  reservations: Reservation[];

  page = 1;


  constructor(private reservationService: ReservationService) {
  }

  ngOnInit(): void {
    this.fetchReservations();
  }

  fetchReservations(): void {
    this.reservationService.getByUser(1)
      .subscribe(reser => {
        this.reservations = reser;
        this.reservations.sort((a: Reservation, b: Reservation) => {
          return +new Date(a.startOfReservation) - +new Date(b.startOfReservation);
        });
      });
  }

  deleteReservation(id: number): void {
    this.reservationService.delete(id)
      .subscribe(() => {
        this.reservations = this.reservations.filter(t => t.id !== id);
      });
  }
}
