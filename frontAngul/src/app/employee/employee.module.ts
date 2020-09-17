import { NgModule } from '@angular/core';
import {SharedModule} from '../shared/shared.module';
import { EmployeeLayoutComponent } from './shared/employee-layout/employee-layout.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import {RouterModule} from '@angular/router';



@NgModule({
  declarations: [EmployeeLayoutComponent, DashboardComponent],
  imports: [
    SharedModule,
    RouterModule.forChild([
      {
        path: '', component: EmployeeLayoutComponent, children: [
          {path: '', redirectTo: '/login', pathMatch: 'full'},
          {path: 'dashboard', component: DashboardComponent}
        ]
      }
    ])
  ],
  exports: [RouterModule]
})
export class EmployeeModule { }
