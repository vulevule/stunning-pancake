import { Component, OnInit } from '@angular/core';
import { RegistrationService } from 'src/app/services/registration.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  username = "";
  password = "";
  constructor(private rs : RegistrationService) { }

  ngOnInit() {
  }
  login(){
    this.rs.login(this.username, this.password).subscribe( success =>{
        localStorage.setItem("user", success.username);
        window.location.href = "/tasks";
    },
    err =>{
        alert("Wront username & password");
    })
  }

}
