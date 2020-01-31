import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ShopingCartComponent } from './components/shoping-cart/shoping-cart.component';
import { SuccessPaymentComponent } from './components/success-payment/success-payment.component';
import { FailPaymentComponent } from './components/fail-payment/fail-payment.component';
import { RegistrationComponent } from './components/registration/registration.component';
import {TasksComponent} from './components/tasks/tasks.component';
import { LoginComponent } from './components/login/login.component';
const routes: Routes = [
  {path: 'success/:id', component: SuccessPaymentComponent},
  {path: 'fail', component: FailPaymentComponent},
  {path: 'task/:task', component: RegistrationComponent},
  {path: 'tasks', component : TasksComponent},
  {path: 'login', component : LoginComponent},
  {path: '**', component: ShopingCartComponent},
  

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
