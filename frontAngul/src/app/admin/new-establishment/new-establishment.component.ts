import {Component, OnDestroy, OnInit} from '@angular/core';
import {EstablishmentService} from '../../services/establishment.service';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {Establishment, TimeTable} from '../../shared/interfaces';
import {Type} from '../../shared/role.enum';
import {DataEstablishmentService} from '../../shared/services/data-establishment.service';
import {Router} from '@angular/router';
import {NgbTimeStruct} from '@ng-bootstrap/ng-bootstrap';
import {AlertService} from '../../shared/services/alert.service';

@Component({
  selector: 'app-new-establishment',
  templateUrl: './new-establishment.component.html',
  styleUrls: ['./new-establishment.component.css']
})
export class NewEstablishmentComponent implements OnInit, OnDestroy {

  file: File;

  hourStep = 1;
  minuteStep = 15;
  secondStep = 30;

  lindi1: NgbTimeStruct = {hour: 0, minute: 0, second: 0};
  lindi2: NgbTimeStruct = {hour: 0, minute: 0, second: 0};
  lindi3: NgbTimeStruct = {hour: 0, minute: 0, second: 0};
  lindi4: NgbTimeStruct = {hour: 0, minute: 0, second: 0};

  mardi1: NgbTimeStruct = {hour: 0, minute: 0, second: 0};
  mardi2: NgbTimeStruct = {hour: 0, minute: 0, second: 0};
  mardi3: NgbTimeStruct = {hour: 0, minute: 0, second: 0};
  mardi4: NgbTimeStruct = {hour: 0, minute: 0, second: 0};

  mercredi1: NgbTimeStruct = {hour: 0, minute: 0, second: 0};
  mercredi2: NgbTimeStruct = {hour: 0, minute: 0, second: 0};
  mercredi3: NgbTimeStruct = {hour: 0, minute: 0, second: 0};
  mercredi4: NgbTimeStruct = {hour: 0, minute: 0, second: 0};

  jeudi1: NgbTimeStruct = {hour: 0, minute: 0, second: 0};
  jeudi2: NgbTimeStruct = {hour: 0, minute: 0, second: 0};
  jeudi3: NgbTimeStruct = {hour: 0, minute: 0, second: 0};
  jeudi4: NgbTimeStruct = {hour: 0, minute: 0, second: 0};

  vendredi1: NgbTimeStruct = {hour: 0, minute: 0, second: 0};
  vendredi2: NgbTimeStruct = {hour: 0, minute: 0, second: 0};
  vendredi3: NgbTimeStruct = {hour: 0, minute: 0, second: 0};
  vendredi4: NgbTimeStruct = {hour: 0, minute: 0, second: 0};

  samedi1: NgbTimeStruct = {hour: 0, minute: 0, second: 0};
  samedi2: NgbTimeStruct = {hour: 0, minute: 0, second: 0};
  samedi3: NgbTimeStruct = {hour: 0, minute: 0, second: 0};
  samedi4: NgbTimeStruct = {hour: 0, minute: 0, second: 0};

  dimanche1: NgbTimeStruct = {hour: 0, minute: 0, second: 0};
  dimanche2: NgbTimeStruct = {hour: 0, minute: 0, second: 0};
  dimanche3: NgbTimeStruct = {hour: 0, minute: 0, second: 0};
  dimanche4: NgbTimeStruct = {hour: 0, minute: 0, second: 0};


  establishmentModify: Establishment;

  form: FormGroup;

  type: Type;

  constructor(private establishmentService: EstablishmentService,
              private dataEstablishmentService: DataEstablishmentService,
              private router: Router,
              private alertService: AlertService) {
    this.establishmentModify = dataEstablishmentService.establishmentForModify;
  }

  ngOnInit(): void {
    if (this.establishmentModify) {
      this.form = new FormGroup({
        name: new FormControl(this.establishmentModify.name, Validators.required),
        description: new FormControl(this.establishmentModify.description, Validators.required),
        address: new FormControl(this.establishmentModify.address, Validators.required),
        clients_limit: new FormControl(this.establishmentModify.clients_limit, Validators.required),
        typeOfEstablishment: new FormControl(this.establishmentModify.typeOfEstablishment, Validators.required),
        file: new FormControl()
      });

    } else {
      this.form = new FormGroup({
        name: new FormControl('', Validators.required),
        description: new FormControl('', Validators.required),
        address: new FormControl('', Validators.required),
        clients_limit: new FormControl('', Validators.required),
        typeOfEstablishment: new FormControl('POOL', Validators.required),
        file: new FormControl()
      });
    }
  }

  // tslint:disable-next-line:typedef
  selectFile(event) {
    const file = event.target.files.item(0);

    if (file.type.match('image.*')) {
      this.file = event.target.files.item(0);
    } else {
      alert('invalid format!');
    }
  }

  getTimeTable(): TimeTable {
    return {
      fridayAMEnd: new Date(0, 0, 0, this.vendredi2.hour, this.vendredi2.minute),
      fridayAMStart: new Date(0, 0, 0, this.vendredi1.hour, this.vendredi1.minute),
      fridayPMEnd: new Date(0, 0, 0, this.vendredi4.hour, this.vendredi4.minute),
      fridayPMStart: new Date(0, 0, 0, this.vendredi3.hour, this.vendredi3.minute),
      mondayAMEnd: new Date(0, 0, 0, this.lindi2.hour, this.lindi2.minute),
      mondayAMStart: new Date(0, 0, 0, this.lindi1.hour, this.lindi1.minute),
      mondayPMEnd: new Date(0, 0, 0, this.lindi4.hour, this.lindi4.minute),
      mondayPMStart: new Date(0, 0, 0, this.lindi3.hour, this.lindi3.minute),
      saturdayAMEnd: new Date(0, 0, 0, this.samedi2.hour, this.samedi2.minute),
      saturdayAMStart: new Date(0, 0, 0, this.samedi1.hour, this.samedi1.minute),
      saturdayPMEnd: new Date(0, 0, 0, this.samedi4.hour, this.samedi4.minute),
      saturdayPMStart: new Date(0, 0, 0, this.samedi3.hour, this.samedi3.minute),
      sundayAMEnd: new Date(0, 0, 0, this.dimanche2.hour, this.dimanche2.minute),
      sundayAMStart: new Date(0, 0, 0, this.dimanche1.hour, this.dimanche1.minute),
      sundayPMEnd: new Date(0, 0, 0, this.dimanche4.hour, this.dimanche4.minute),
      sundayPMStart: new Date(0, 0, 0, this.dimanche3.hour, this.dimanche3.minute),
      thursdayAMEnd: new Date(0, 0, 0, this.jeudi2.hour, this.jeudi2.minute),
      thursdayAMStart: new Date(0, 0, 0, this.jeudi1.hour, this.jeudi1.minute),
      thursdayPMEnd: new Date(0, 0, 0, this.jeudi4.hour, this.jeudi4.minute),
      thursdayPMStart: new Date(0, 0, 0, this.jeudi3.hour, this.jeudi3.minute),
      tuesdayAMEnd: new Date(0, 0, 0, this.mardi2.hour, this.mardi2.minute),
      tuesdayAMStart: new Date(0, 0, 0, this.mardi1.hour, this.mardi1.minute),
      tuesdayPMEnd: new Date(0, 0, 0, this.mardi4.hour, this.mardi4.minute),
      tuesdayPMStart: new Date(0, 0, 0, this.mardi3.hour, this.mardi3.minute),
      wednesdayAMEnd: new Date(0, 0, 0, this.mercredi2.hour, this.mercredi2.minute),
      wednesdayAMStart: new Date(0, 0, 0, this.mercredi1.hour, this.mercredi1.minute),
      wednesdayPMEnd: new Date(0, 0, 0, this.mercredi4.hour, this.mercredi4.minute),
      wednesdayPMStart: new Date(0, 0, 0, this.mercredi3.hour, this.mercredi3.minute),
    };
  }

  submit(): void {
    if (this.form.valid) {
      this.establishmentModify = {
        id: this.establishmentModify ? this.establishmentModify.id : null,
        address: this.form.value.address, clients_limit: this.form.value.clients_limit, description: this.form.value.description,
        name: this.form.value.name, timeTable: this.getTimeTable(), typeOfEstablishment: this.form.value.typeOfEstablishment
      };
      console.log(this.establishmentModify);
      this.establishmentService.save(this.establishmentModify, this.file).subscribe(value => {
        this.form.reset();
        this.router.navigate(['service', 'dashboard']);
        this.alertService.success('Establishment créée');
      });
    }
  }

  ngOnDestroy(): void {
    this.dataEstablishmentService.establishmentForModify = null;
  }
}
