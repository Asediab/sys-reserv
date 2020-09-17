import { NgModule } from '@angular/core';
import {Routes, RouterModule, PreloadAllModules} from '@angular/router';
import {MainLayoutComponent} from './shared/components/main-layout/main-layout.component';
import {HomePageComponent} from './home-page/home-page.component';
import {LoginPageComponent} from './shared/components/login-page/login-page.component';
import {MyReservationsComponent} from './my-reservations/my-reservations.component';
import {InfoEstablishmentComponent} from './info-establishment/info-establishment.component';
import {NewReservationComponent} from './new-reservation/new-reservation.component';
import {ErrorComponent} from './error/error.component';


const routes: Routes = [
  {path: '', component: MainLayoutComponent, children: [
      {path: '', redirectTo: '/', pathMatch: 'full'},
      {path: '', component: HomePageComponent},
      {path: 'reservations', component: MyReservationsComponent},
      {path: 'info/:id', component: InfoEstablishmentComponent},
      {path: 'new', component: NewReservationComponent},
      {path: 'error', component: ErrorComponent},
      {path: 'login', component: LoginPageComponent}
    ]},
  {path: 'service', loadChildren: () => import('./admin/admin.module').then(m => m.AdminModule) },
  {path: 'employee', loadChildren: () => import('./employee/employee.module').then(m => m.EmployeeModule) },
  {path: '**', redirectTo: '/error'}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
