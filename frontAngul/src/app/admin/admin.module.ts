import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { AdminLayoutComponent } from './shared/components/admin-layout/admin-layout.component';
import {LoginPageComponent} from '../shared/components/login-page/login-page.component';
import {SharedModule} from '../shared/shared.module';
import { DashboardComponent } from './dashboard/dashboard.component';
import { NewEstablishmentComponent } from './new-establishment/new-establishment.component';
import { ListEmployeesComponent } from './list-employees/list-employees.component';
import { NewEmployeeComponent } from './new-employee/new-employee.component';



@NgModule({
  declarations: [AdminLayoutComponent, DashboardComponent, NewEstablishmentComponent, ListEmployeesComponent, NewEmployeeComponent],
  imports: [
    SharedModule,
    RouterModule.forChild([
      {
        path: '', component: AdminLayoutComponent, children: [
          {path: '', redirectTo: '/login', pathMatch: 'full'},
          {path: ':id', component: NewEstablishmentComponent},
          {path: 'employees/:id', component: ListEmployeesComponent},
          {path: 'dashboard', component: DashboardComponent},
          {path: 'addEmployee', component: NewEmployeeComponent}
        ]
      }
    ])
  ],
  exports: [RouterModule]
})
export class AdminModule {
}
