import { Component, OnInit } from '@angular/core';
import {FormGroup} from '@angular/forms';

@Component({
  selector: 'app-new-user',
  templateUrl: './new-user.component.html',
  styleUrls: ['./new-user.component.css']
})
export class NewUserComponent implements OnInit {

  form: FormGroup;

  constructor() { }

  ngOnInit(): void {
    this.form = new FormGroup({});
  }

}
