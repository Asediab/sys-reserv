import { Component, OnInit } from '@angular/core';
import {AuthService} from '../../../services/auth.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-main-layout',
  templateUrl: './main-layout.component.html',
  styleUrls: ['./main-layout.component.css']
})
export class MainLayoutComponent implements OnInit {

  constructor(public auth: AuthService,
              private router: Router) { }

  logout(): void {
    this.auth.logout();
    this.router.navigate(['/']);
  }

  ngOnInit(): void {
  }

}
