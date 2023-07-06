import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.css']
})
export class MenuComponent implements OnInit {
  isUserLoggedIn = false;
  role: any
  userDetails: any
  constructor(private router: Router, private userService: UserService) {  
  }

  ngOnInit(): void {
    this.router.events.subscribe((value: any) => {
      var username = localStorage.getItem("username");  
      this.role = localStorage.getItem("role")   
      if( username!=null && username != '') {
        this.isUserLoggedIn = true;        
      } else {
        this.isUserLoggedIn = false;
      }              
    })    
  }    
}
