import { NgModule } from '@angular/core';
import {HttpClientModule} from '@angular/common/http';
import {LoginPageComponent} from './components/login-page/login-page.component';
import {CommonModule} from '@angular/common';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {NgbModule} from '@ng-bootstrap/ng-bootstrap';
import { NewUserComponent } from './components/new-user/new-user.component';
import { ModalModifCommentComponent } from './components/modal-modif-comment/modal-modif-comment.component';
import { AlertComponent } from './components/alert/alert.component';



@NgModule({
  declarations: [
    LoginPageComponent,
    NewUserComponent,
    ModalModifCommentComponent,
    AlertComponent
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
        NgbModule,
        ModalModifCommentComponent,
        AlertComponent
    ]
})
export class SharedModule { }
