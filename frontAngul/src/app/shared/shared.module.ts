import { NgModule } from '@angular/core';
import {HttpClientModule} from '@angular/common/http';
import {LoginPageComponent} from './components/login-page/login-page.component';
import {CommonModule} from '@angular/common';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {NgbModule} from '@ng-bootstrap/ng-bootstrap';



@NgModule({
  declarations: [
    LoginPageComponent
  ],
  imports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    NgbModule,
    HttpClientModule
  ],
  exports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    LoginPageComponent,
    HttpClientModule,
    NgbModule
  ]
})
export class SharedModule { }
