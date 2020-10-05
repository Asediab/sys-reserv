import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { AdminLayoutComponent } from './shared/components/admin-layout/admin-layout.component';
import {SharedModule} from '../shared/shared.module';
import { DashboardComponent } from './dashboard/dashboard.component';
import { NewEstablishmentComponent } from './new-establishment/new-establishment.component';
import { ListEmployeesComponent } from './list-employees/list-employees.component';
import { NewEmployeeComponent } from './new-employee/new-employee.component';
import {AuthGuard} from '../services/auth.guard';
import {Role} from '../shared/role.enum';
import { InfoComponent } from './info/info.component';



@NgModule({
  declarations: [
    AdminLayoutComponent,
    DashboardComponent,
    NewEstablishmentComponent,
    ListEmployeesComponent,
    NewEmployeeComponent,
    InfoComponent
  ],
  imports: [
    SharedModule,
    RouterModule.forChild([
      {
        path: '', component: AdminLayoutComponent, children: [
          {path: '', redirectTo: '/service/dashboard', pathMatch: 'full'},
          {path: 'addEstablishment', component: NewEstablishmentComponent, canActivate: [AuthGuard], data: {roles: [Role.Admin]}},
          {path: 'employees/:id', component: ListEmployeesComponent, canActivate: [AuthGuard], data: {roles: [Role.Admin]}},
          {path: 'dashboard', component: DashboardComponent, canActivate: [AuthGuard], data: {roles: [Role.Admin]}},
          {path: 'addEmployee/:id', component: NewEmployeeComponent, canActivate: [AuthGuard], data: {roles: [Role.Admin]}},
          {path: 'info/:id', component: InfoComponent, canActivate: [AuthGuard], data: {roles: [Role.Admin]}}
        ]
      }
    ])
  ],
  exports: [RouterModule]
})
export class AdminModule {
}
