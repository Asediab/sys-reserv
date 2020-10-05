import { NgModule } from '@angular/core';
import {Routes, RouterModule} from '@angular/router';
import {MainLayoutComponent} from './shared/components/main-layout/main-layout.component';
import {HomePageComponent} from './home-page/home-page.component';
import {LoginPageComponent} from './shared/components/login-page/login-page.component';
import {MyReservationsComponent} from './my-reservations/my-reservations.component';
import {InfoEstablishmentComponent} from './info-establishment/info-establishment.component';
import {ErrorComponent} from './error/error.component';
import {AuthGuard} from './services/auth.guard';
import {Role} from './shared/role.enum';
import {CommentsComponent} from './comments/comments.component';
import {IntroComponent} from './intro/intro.component';
import {NewUserComponent} from './shared/components/new-user/new-user.component';


const routes: Routes = [
  {path: '', component: MainLayoutComponent,  children: [
      {path: '', redirectTo: '/', pathMatch: 'full'},
      {path: '', component: IntroComponent},
      {path: 'home', component: HomePageComponent},
      {path: 'registration', component: NewUserComponent},
      {path: 'reservations', component: MyReservationsComponent, canActivate: [AuthGuard], data: {roles: [Role.User]}},
      {path: 'info/:id', component: InfoEstablishmentComponent, canActivate: [AuthGuard], data: {roles: [Role.User]}, children: [
          {path: 'comments', component: CommentsComponent, canActivate: [AuthGuard], data: {roles: [Role.User]}}
        ]},
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
