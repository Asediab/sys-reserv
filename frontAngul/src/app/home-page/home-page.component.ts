import {Component, OnInit} from '@angular/core';
import {Establishment, User} from '../shared/interfaces';
import {EstablishmentService} from '../services/establishment.service';
import {DataEstablishmentService} from '../shared/services/data-establishment.service';
import {Router} from '@angular/router';
import {FormControl, FormGroup, Validators} from '@angular/forms';

@Component({
  selector: 'app-home-page',
  templateUrl: './home-page.component.html',
  styleUrls: ['./home-page.component.css']
})
export class HomePageComponent implements OnInit {

  establishments: Establishment[] = [];

  form: FormGroup;

  loading = false;

  page = 1;

  constructor(private establishmentService: EstablishmentService,
              private dataEstablishmentService: DataEstablishmentService,
              private router: Router) {
  }

  ngOnInit(): void {
    this.fetchEstablishments();
    this.form = new FormGroup({
      nom: new FormControl(''),
      type: new FormControl('')
    });
  }

  fetchEstablishments(): void {
    this.loading = true;
    this.establishmentService.getAll()
      .subscribe(establishment => {
        this.establishments = establishment;
        this.loading = false;
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
        this.loading = false;
        console.log(this.form.value.nom, this.form.value.type);
      });
  }
}
