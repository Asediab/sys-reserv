import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { AdminLayoutComponent } from './shared/components/admin-layout/admin-layout.component';
import {LoginPageComponent} from '../shared/components/login-page/login-page.component';
import {SharedModule} from '../shared/shared.module';
import { DashboardComponent } from './dashboard/dashboard.component';
import { NewEstablishmentComponent } from './new-establishment/new-establishment.component';
import { ListEmployeesComponent } from './list-employees/list-employees.component';
import { NewEmployeeComponent } from './new-employee/new-employee.component';
import {MyReservationsComponent} from '../my-reservations/my-reservations.component';
import {AuthGuard} from '../services/auth.guard';
import {Role} from '../shared/role.enum';



@NgModule({
  declarations: [AdminLayoutComponent, DashboardComponent, NewEstablishmentComponent, ListEmployeesComponent, NewEmployeeComponent],
  imports: [
    SharedModule,
    RouterModule.forChild([
      {
        path: '', component: AdminLayoutComponent, children: [
          {path: '', redirectTo: '/login', pathMatch: 'full'},
          {path: 'establishment/:id', component: NewEstablishmentComponent, canActivate: [AuthGuard], data: {roles: [Role.Admin]}},
          {path: 'employees/:id', component: ListEmployeesComponent, canActivate: [AuthGuard], data: {roles: [Role.Admin]}},
          {path: 'dashboard', component: DashboardComponent, canActivate: [AuthGuard], data: {roles: [Role.Admin]}},
          {path: 'addEmployee', component: NewEmployeeComponent, canActivate: [AuthGuard], data: {roles: [Role.Admin]}}
        ]
      }
    ])
  ],
  exports: [RouterModule]
})
export class AdminModule {
}
