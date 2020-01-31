import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';





@Injectable({
  providedIn: 'root'
})
export class ShoppingCartService {

 
  constructor(private http : HttpClient) { }
  //private lineUrl = `http://localhost:8000/api/seller-service/api/order`;
  private lineUrl = `http://localhost:8007/api/payment/order`;
  makePayment(order){
    return this.http.post<any>(this.lineUrl , order);
  }
  getEditions(){
    return this.http.get<any>('http://localhost:8007/api/payment/editions');
  }
  payEdition(id){
    return this.http.post<any>('http://localhost:8007/api/payment/pay/edition/' + id, {});
  }
  getEdition(id){
    return this.http.get<any>('http://localhost:8007/api/payment/success/' + id);
  }
}