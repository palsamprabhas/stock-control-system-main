import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { ConsumerService } from 'src/app/services/consumer.service';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent {
  
  userForm: FormGroup;
  consumers: any

  constructor(private userService: UserService, private router: Router) { 
    this.userForm = new FormGroup({
      username: new FormControl('', [Validators.required, Validators.minLength(8)]),
      password: new FormControl('', [Validators.required]),
      confirmPassword: new FormControl('', [Validators.required]),
      role: new FormControl('', [Validators.required]),
      companyName: new FormControl('', [Validators.required]),
      email: new FormControl('', [Validators.required, Validators.pattern("^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$")]),      
      phoneNumber: new FormControl('', [Validators.required, Validators.pattern('[- +()0-9]+')]),
      address: new FormControl('', [Validators.required]),     
    });
  }

  createUser() {
    this.userService.createUser(this.userForm.value).subscribe(
      (data) => {
        this.router.navigateByUrl('signup-success')
      },
      (error) => {
        console.log(error);
      }
    ); 
  }

}
