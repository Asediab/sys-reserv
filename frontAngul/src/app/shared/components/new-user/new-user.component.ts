import { Component, OnInit } from '@angular/core';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {ActivatedRoute, Params, Router} from '@angular/router';
import {User} from '../../interfaces';
import {UserService} from '../../../services/user.service';
import {AlertService} from '../../services/alert.service';

@Component({
  selector: 'app-new-user',
  templateUrl: './new-user.component.html',
  styleUrls: ['./new-user.component.css']
})
export class NewUserComponent implements OnInit {

  form: FormGroup;

  constructor(private router: Router,
              private userService: UserService,
              private alertService: AlertService) { }

  ngOnInit(): void {
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
      user.establishmentId = -1;
      this.userService.created(user)
        .subscribe(v => {
          this.form.reset();
          this.alertService.success('Êtes-vous inscrit');
          this.router.navigate(['/home']);
        });
    }
  }
}
