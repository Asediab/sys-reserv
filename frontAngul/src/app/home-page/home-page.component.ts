import { Component, OnInit } from '@angular/core';
import {Establishment} from '../shared/interfaces';
import {EstablishmentService} from '../services/establishment.service';
import {DataEstablishmentService} from '../shared/services/data-establishment.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-home-page',
  templateUrl: './home-page.component.html',
  styleUrls: ['./home-page.component.css']
})
export class HomePageComponent implements OnInit {

  establishments: Establishment[] = [];

  loading = false;

  page = 1;

  constructor(private establishmentService: EstablishmentService,
              private dataEstablishmentService: DataEstablishmentService,
              private router: Router) { }

  ngOnInit(): void {
    this.fetchEstablishments();
  }

  fetchEstablishments(): void {
    this.loading = true;
    this.establishmentService.getAll()
      .subscribe(establishment => {
        this.establishments = establishment;
        this.loading = false;
      });
  }

  info(establishment: Establishment): void {
    this.dataEstablishmentService.establishment = establishment;
    this.router.navigate(['/info/' + establishment.id]);
  }
}
