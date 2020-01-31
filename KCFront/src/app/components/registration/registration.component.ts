import { Component, OnInit } from '@angular/core';
import {RegistrationService} from '../../services/registration.service';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
export class RegistrationComponent implements OnInit {
  title : String;
  formFields = [];
  processInstance = "";
  taskId = "";
  scientificArea = "";
  scientificAreas = [];
  constructor(private regService : RegistrationService, private route : ActivatedRoute ) { 
    this.route.params.subscribe((params) => {
      this.regService.getForm(params['task'])
      .subscribe(
        (res) => {
              this.title = "Registration started!";
              this.formFields = res.formFields;
              this.taskId = res.taskId;
              console.log(this.formFields);
              this.processInstance = res.processInstanceId;
            },
        err => alert("Registration process failed!")
    );
    });
    
    
  }
  addField(){
    this.scientificAreas.push(this.scientificArea + "");
    console.log(this.scientificAreas);
    this.scientificArea = "";
  }
  
  ngOnInit(){

  }
  onSubmit(form){
    
    let o = new Array();
    for (var property in form) {
      console.log(property);
      console.log(form[property]);
      o.push({fieldId : property, fieldValue : form[property]});
      
    }
    let sfs = "";
    if (this.formFields.filter(formField=> formField.properties.hasOwnProperty('array')).length != 0){
      this.scientificAreas.map(area=> sfs+= "," + area);
      sfs = sfs.substring(2, sfs.length);
      o.push({fieldId : "scientificAreas", fieldValue : sfs});
    }
    
    

    console.log(o);
    let x = this.regService.registerUser(o, this.taskId);

    x.subscribe(
      res => {
        console.log(res);
        
        window.location.href = "/tasks";
      },
      err => {
        console.log("Error occured");
      }
    );
    
  }

}
