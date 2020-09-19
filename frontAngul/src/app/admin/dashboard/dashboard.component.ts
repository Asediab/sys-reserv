import { Component, OnInit } from '@angular/core';
import {Establishment} from '../../shared/interfaces';
import {AuthService} from '../../services/auth.service';
import {EstablishmentService} from '../../services/establishment.service';
import {DataEstablishmentService} from '../../shared/services/data-establishment.service';
import {ActivatedRoute, Params, Router} from '@angular/router';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {

  establishments: Establishment[];

  page = 1;

  constructor(public auth: AuthService,
              private establishmentService: EstablishmentService,
              private dataEstablishmentService: DataEstablishmentService,
              private router: Router,
              private route: ActivatedRoute) { }

  ngOnInit(): void {
    this.establishmentService.getAll().subscribe(value => {
      this.establishments = value;
    });
  }

  modify(establishment: Establishment): void {
    this.dataEstablishmentService.establishmentForModify = establishment;
    this.route.params.subscribe(() => {
      this.router.navigate(['service', 'addEstablishment']);
    });
  }

  info(establishment: Establishment): void {
    this.dataEstablishmentService.establishment = establishment;
    this.router.navigate(['service', 'info', establishment.id]);
  }

  redirect(): void {
    this.route.params.subscribe(() => {
      this.router.navigate(['service', 'addEstablishment']);
    });
  }
}
