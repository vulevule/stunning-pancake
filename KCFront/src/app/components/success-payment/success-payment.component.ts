import { Component, OnInit } from '@angular/core';
import {ShoppingCartService} from '../../services/shopping-cart.service';
import { ActivatedRoute } from '@angular/router';
@Component({
  selector: 'app-success-payment',
  templateUrl: './success-payment.component.html',
  styleUrls: ['./success-payment.component.css']
})
export class SuccessPaymentComponent implements OnInit {

  constructor(private route: ActivatedRoute, private scService : ShoppingCartService) { }
  edition = null;
  ngOnInit() {
    this.route.params.subscribe(params => {
      this.scService.getEdition(params.id)
      .subscribe(response=>{
        this.edition = response;
        console.log(this.edition)

        
      })
    });
  }

}
