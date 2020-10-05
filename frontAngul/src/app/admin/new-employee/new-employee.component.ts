import {Component, OnInit} from '@angular/core';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {ActivatedRoute, Params} from '@angular/router';
import {User} from '../../shared/interfaces';
import {UserService} from '../../services/user.service';
import {AlertService} from '../../shared/services/alert.service';
import {Location} from '@angular/common';

@Component({
  selector: 'app-new-employee',
  templateUrl: './new-employee.component.html',
  styleUrls: ['./new-employee.component.css']
})
export class NewEmployeeComponent implements OnInit {

  form: FormGroup;

  establishmentId: number;

  constructor(private route: ActivatedRoute,
              private userService: UserService,
              private alertService: AlertService,
              public location: Location) {
  }

  ngOnInit(): void {
    this.route.params.subscribe((params: Params) => {
      this.establishmentId = params.id;
    });
    this.form = new FormGroup({
      firstName: new FormControl('', Validators.required),
      lastName: new FormControl('', Validators.required),
      email: new FormControl('', [Validators.email, Validators.required]),
      password: new FormControl(null, [Validators.required, Validators.minLength(6)])
    });
  }

  submit(): void {
    if (this.form.valid) {
      const user: User = this.form.value;
      user.establishmentId = this.establishmentId;
      console.log(user);
      this.userService.created(user)
        .subscribe(v => {
          this.form.reset();
          this.alertService.success('Employee est enregistré');
        });
    }
  }
}
