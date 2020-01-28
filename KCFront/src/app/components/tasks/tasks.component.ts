import { Component, OnInit } from '@angular/core';
import { RegistrationService } from 'src/app/services/registration.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-tasks',
  templateUrl: './tasks.component.html',
  styleUrls: ['./tasks.component.css']
})
export class TasksComponent implements OnInit {

  constructor(private rs : RegistrationService,
      private router : Router) { }
  tasks = [];
  ngOnInit() {
    this.rs.getTasks().subscribe(tasks=> {
      console.log(tasks);
      
        this.tasks =tasks;

      
    })
  }
  start(task){
    this.router.navigate(["task", task.taskId]);

  }

}
