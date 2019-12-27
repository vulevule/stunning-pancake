import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ShopingCartComponent } from './shoping-cart/shoping-cart.component';
import { SuccessPaymentComponent } from './success-payment/success-payment.component';
import { FailPaymentComponent } from './fail-payment/fail-payment.component';

const routes: Routes = [
  {path: 'success', component: SuccessPaymentComponent},
  {path: 'fail', component: FailPaymentComponent},
  {path: '**', component: ShopingCartComponent},

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
