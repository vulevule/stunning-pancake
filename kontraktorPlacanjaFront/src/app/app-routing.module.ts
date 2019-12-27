import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { PaymentMethodsComponent } from './payment-methods/payment-methods.component';

const routes: Routes = [
  {path: 'paymentMethods', component: PaymentMethodsComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
