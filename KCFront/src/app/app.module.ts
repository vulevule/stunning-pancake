
import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import {FormsModule} from '@angular/forms';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ShopingCartComponent } from './components/shoping-cart/shoping-cart.component';
import {MatFormFieldModule} from '@angular/material/form-field';
import {ReactiveFormsModule} from '@angular/forms';
import {MatInputModule} from '@angular/material';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { HttpClientModule } from '@angular/common/http';
import {
  MatButtonModule, 
  MatToolbarModule, 
  MatSidenavModule, 
  MatIconModule, 
  MatListModule, 
  MatGridListModule,
  MatCardModule,
  MatTabsModule,

  } from '@angular/material';
import { SuccessPaymentComponent } from './components/success-payment/success-payment.component';
import { FailPaymentComponent } from './components/fail-payment/fail-payment.component';
import { RegistrationComponent } from './components/registration/registration.component';
import {MatCheckboxModule} from '@angular/material/checkbox';

import { TasksComponent } from './components/tasks/tasks.component';
import { LoginComponent } from './components/login/login.component';
@NgModule({
  declarations: [
    AppComponent,
    ShopingCartComponent,
    SuccessPaymentComponent,
    FailPaymentComponent,
    RegistrationComponent,
    TasksComponent,
    LoginComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    MatFormFieldModule,
    MatButtonModule, 
  MatToolbarModule, 
  MatSidenavModule, 
  MatIconModule, 
  MatListModule, 
  MatGridListModule,
  MatCardModule,
  MatTabsModule,
  ReactiveFormsModule,
  MatInputModule,
  BrowserAnimationsModule,
  HttpClientModule,
  MatInputModule,
  MatCheckboxModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
