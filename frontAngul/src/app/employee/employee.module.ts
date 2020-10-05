import { NgModule } from '@angular/core';
import {SharedModule} from '../shared/shared.module';
import { EmployeeLayoutComponent } from './shared/employee-layout/employee-layout.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import {RouterModule} from '@angular/router';
import {AuthGuard} from '../services/auth.guard';
import {Role} from '../shared/role.enum';
import { HistoryComponent } from './history/history.component';
import {ErrorComponent} from '../error/error.component';



@NgModule({
  declarations: [EmployeeLayoutComponent, DashboardComponent, HistoryComponent],
  imports: [
    SharedModule,
    RouterModule.forChild([
      {
        path: '', component: EmployeeLayoutComponent, children: [
          {path: '', redirectTo: '/employee/dashboard', pathMatch: 'full'},
          {path: 'dashboard', component: DashboardComponent, canActivate: [AuthGuard], data: {roles: [Role.Employee]}},
          {path: 'history', component: HistoryComponent, canActivate: [AuthGuard], data: {roles: [Role.Employee]}},
          {path: '***', component: ErrorComponent}
        ]
      }
    ])
  ],
  exports: [RouterModule]
})
export class EmployeeModule { }
