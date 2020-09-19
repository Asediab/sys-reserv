import { Component, OnInit } from '@angular/core';
import {User} from '../../shared/interfaces';
import {AuthService} from '../../services/auth.service';
import {EstablishmentService} from '../../services/establishment.service';
import {DataEstablishmentService} from '../../shared/services/data-establishment.service';
import {UserService} from '../../services/user.service';
import {ActivatedRoute, Params, Router} from '@angular/router';

@Component({
  selector: 'app-list-employees',
  templateUrl: './list-employees.component.html',
  styleUrls: ['./list-employees.component.css']
})
export class ListEmployeesComponent implements OnInit {

  employees: User[];

  page = 1;

  constructor(public auth: AuthService,
              private userService: UserService,
              private dataEstablishService: DataEstablishmentService,
              private route: ActivatedRoute,
              private router: Router) { }

  ngOnInit(): void {
      this.route.params.subscribe((params: Params) => {
        this.userService.getUsersByEstablishmentId(params.id)
          .subscribe(users => {
            this.employees = users;
          });
    });
  }

  modify(employee: User): void {
    return null;
  }

  delete(employee: User): void {
    return null;
  }

  newEmployee(): void {
    this.route.params.subscribe((params: Params) => {
      this.router.navigate(['service', 'addEmployee', params.id]);
    });
  }
}
