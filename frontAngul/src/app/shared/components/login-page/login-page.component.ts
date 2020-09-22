import { Component, OnInit } from '@angular/core';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {UserLogin} from '../../interfaces';
import {AuthService} from '../../../services/auth.service';
import {Router} from '@angular/router';
import {AlertService} from '../../services/alert.service';
import {Role} from '../../role.enum';

@Component({
  selector: 'app-login-page',
  templateUrl: './login-page.component.html',
  styleUrls: ['./login-page.component.css']
})
export class LoginPageComponent implements OnInit {

  form: FormGroup;

  constructor(private auth: AuthService,
              private alertService: AlertService) { }

  ngOnInit(): void {
    this.form = new FormGroup({
      email: new FormControl(null, [
        Validators.required,
        Validators.email
      ]),
      password: new FormControl(null, [
        Validators.required,
        Validators.minLength(4)
      ])
    });
  }

  submit(): void {
    if (this.form.invalid) {
      return;
    }
    const userLogin: UserLogin = {
      email: this.form.value.email,
      password: this.form.value.password
    };

    this.auth.login(userLogin).subscribe(value => {
      this.auth.setUser();
      this.form.reset();
      this.alertService.success('Bienvenue!');
    });
  }
}
