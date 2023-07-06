import { Component } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-signin',
  templateUrl: './signin.component.html',
  styleUrls: ['./signin.component.css']
})
export class SigninComponent {

  userForm: FormGroup;
  consumers: any
  isLoginFailed = false  

  constructor(private userService: UserService, private router: Router) { 
    this.userForm = new FormGroup({
      username: new FormControl('', [Validators.required]),
      password: new FormControl('', [Validators.required])      
    });
  }

  loginUser() {
    this.userService.loginUser(this.userForm.value).subscribe(
      (data) => {
        localStorage.setItem("username", data.username);           
        localStorage.setItem("role", data.role);
        if(data.role == "SUPPLIER") {
          this.router.navigateByUrl('supplier-home')
        } else if(data.role == "CONSUMER") {
          this.router.navigateByUrl('consumer-home')
        } else {
          this.router.navigateByUrl('home')
        }        
      },
      (error) => {
        this.isLoginFailed = true        
      }
    ); 
  }
}
