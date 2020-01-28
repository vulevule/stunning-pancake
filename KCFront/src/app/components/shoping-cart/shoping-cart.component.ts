import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import {ShoppingCartService} from '../../services/shopping-cart.service';
import {Router} from '@angular/router';
import { RegistrationService } from 'src/app/services/registration.service';
@Component({
  selector: 'app-shoping-cart',
  templateUrl: './shoping-cart.component.html',
  styleUrls: ['./shoping-cart.component.css']
})
export class ShopingCartComponent  {

    constructor(
      private scService: ShoppingCartService,
      private router: Router,
      private rs : RegistrationService
      ) { }
      
    
    paymentForm: FormGroup = new FormGroup({
      amount: new FormControl('', Validators.required),
    });
    
    
  
    start(processName){
      if (processName == "Registration"){
        localStorage.setItem("user", "guest");
      }
      this.rs.startProcess(processName).subscribe(res => {
        this.router.navigate(['./tasks']);
      })
    }
    onAddLine() {
      var order = {
        amount: this.paymentForm.controls['amount'].value,
        seller : {
          id : 1
        },
        
          successUrl: "asd",
          failUrl: "asd"
        
      }
      console.log(order)
      
      this.scService.makePayment(order)
          .subscribe(response=>{
            console.log(response)
            window.location.href = response.return_url;
          })
      
      //this.lineService.addLine(line)
        //.subscribe(addedLine => {
        //  this.notificationService.success("Uspesno ste dodali liniju " + addedLine.name + ".");
        //  this.formLine.reset();
       // });
    }
  
  
  }
