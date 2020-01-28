import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
@Injectable({
  providedIn: 'root'
})
export class RegistrationService {

  constructor(private http : HttpClient) { }
  registrationUrl = "http://localhost:8007/register"
  startRegistration(){
    localStorage.setItem("user", "guest");
    return this.http.get(this.registrationUrl + "/start") as Observable<any>;
  }
  registerUser(regData, taskId){
    return this.http.post(this.registrationUrl + "/post/" + taskId, regData);
  }
  getTasks(){
    return this.http.get(this.registrationUrl + "/tasks/" + localStorage.getItem("user")) as Observable<any>;
  }
  login(username, password){
    let loginDto = {username : username, password : password};
    return this.http.post("http://localhost:8007/login/attemp", loginDto) as Observable<any>;
  }
  startProcess(name){
    return this.http.get("http://localhost:8007/register/startProcess/" + name) as Observable<any>;
  }
  getForm(task){
    return this.http.get("http://localhost:8007/register/getFrom/" + task);
  }
}
