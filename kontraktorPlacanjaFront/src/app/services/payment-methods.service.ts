import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
@Injectable({
  providedIn: 'root'
})
export class PaymentMethodsService {

  constructor(private http : HttpClient) { }
  private paymentMethodsUrl = `https://localhost:8000/api/seller-service/api/`;
  getPaymentMethods(token){
    return this.http.get<any>(this.paymentMethodsUrl + "paymentMethods/" + token);
  }
  makePayment(token, method){
    return this.http.get<any>(this.paymentMethodsUrl + "pay/" + token + "/" + method.name);
  }
}
