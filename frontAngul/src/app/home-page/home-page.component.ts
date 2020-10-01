import {Component, OnInit} from '@angular/core';
import {Establishment, User} from '../shared/interfaces';
import {EstablishmentService} from '../services/establishment.service';
import {DataEstablishmentService} from '../shared/services/data-establishment.service';
import {Router} from '@angular/router';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {environment} from '../../environments/environment';

@Component({
  selector: 'app-home-page',
  templateUrl: './home-page.component.html',
  styleUrls: ['./home-page.component.css']
})
export class HomePageComponent implements OnInit {

  establishments: Establishment[];

  imageURI = environment.urlEstablishmentApi;

  form: FormGroup;

  page = 1;

  constructor(private establishmentService: EstablishmentService,
              private dataEstablishmentService: DataEstablishmentService,
              private router: Router) {
    this.fetchEstablishments();
  }

  ngOnInit(): void {
    this.fetchEstablishments();
    this.form = new FormGroup({
      nom: new FormControl(''),
      type: new FormControl('')
    });
  }

  fetchEstablishments(): void {
    this.establishmentService.getAll()
      .subscribe(e => {
        this.establishments = e;
        this.form.reset();
      });
  }

  info(establishment: Establishment): void {
    this.dataEstablishmentService.establishment = establishment;
    this.router.navigate(['/info/' + establishment.id]);
  }

  submit(): void {
    const nom: string = this.form.value.nom;
    const type: string = this.form.value.type;
    this.establishmentService.search(nom, type)
      .subscribe(v => {
        this.establishments = v;
        console.log(this.form.value.nom, this.form.value.type);
      });
  }
}
