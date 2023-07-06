import { Component } from '@angular/core';
import { UserService } from '../services/user.service';
import { FormControl, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent {

  userProfile: any
  accountForm: FormGroup;

  constructor(private userService: UserService) {
    this.getUserDetails();
    this.accountForm = new FormGroup({
      bankCode: new FormControl('', [Validators.required]),
      bankAccountNumber: new FormControl('', [Validators.required]),
    });
  }

  getUserDetails() {
    this.userService.getUserByusername(localStorage.getItem('username')).subscribe(
      (data) => {
        this.userProfile = data;
        this.accountForm.setValue({
          bankCode: this.userProfile.bankCode,
          bankAccountNumber: this.userProfile.bankAccountNumber,
        });
      },
      (error) => {
        console.log(error);
      }
    );
  }

  updateAccountDetails() {
    var bankCode = this.accountForm.value['bankCode'];
    var bankAccountNumber = this.accountForm.value['bankAccountNumber'];
    var userame = localStorage.getItem('username');
    this.userService.updateBankAccountDetails(userame, bankCode, bankAccountNumber).subscribe(
      (data) => {
        this.getUserDetails()
      },
      (error) => {
        console.log(error);
      }
    );
  }
}
