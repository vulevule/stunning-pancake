import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';





@Injectable({
  providedIn: 'root'
})
export class ShoppingCartService {

 
  constructor(private http : HttpClient) { }
  //private lineUrl = `http://localhost:8000/api/seller-service/api/order`;
  private lineUrl = `http://localhost:8080/api/payment/order`;
  makePayment(order){
    return this.http.post<any>(this.lineUrl , order);
  }
  
}