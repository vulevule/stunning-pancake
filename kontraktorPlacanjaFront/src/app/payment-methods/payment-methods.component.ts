import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { PaymentMethodsService } from '../services/payment-methods.service';
@Component({
  selector: 'app-payment-methods',
  templateUrl: './payment-methods.component.html',
  styleUrls: ['./payment-methods.component.css']
})
export class PaymentMethodsComponent implements OnInit {
  date : String = "";
  token : String = "";
  paymentMethods = []
  constructor(private activatedRoute: ActivatedRoute, private pmService: PaymentMethodsService) {
    this.activatedRoute.queryParams.subscribe(params => {
          this.token = params['token'];
          console.log( this.token); // Print the parameter to the console. 
      });
  }

  ngOnInit() {
    if (this.token !== null){
      this.pmService.getPaymentMethods(this.token).subscribe(paymentMethods => {
        this.paymentMethods = paymentMethods;
        console.log(this.paymentMethods);
      })
    }
  }
  pay(method){
    this.pmService.makePayment(this.token, method).subscribe(redirectUrls => {
      console.log(redirectUrls)
      window.location.href = redirectUrls.return_url;
    })
  }

}
