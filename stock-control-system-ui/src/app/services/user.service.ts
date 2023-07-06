import { Subject, Observable } from 'rxjs';
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private create_user_endpoint: string = `http://localhost:8080/api/authentication/register`;
  private login_user_endpoint: string = `http://localhost:8080/api/authentication/login`;
  private user_endpoint: string = `http://localhost:8080/api/authentication/`;

  
  constructor(private http: HttpClient) {
  }

  createUser(data: any): Observable<any> {
    return this.http.post<any>(this.create_user_endpoint, data);
  }

  loginUser(data: any): Observable<any> {
    return this.http.post<any>(this.login_user_endpoint, data);
  }

  getUserByusername(username: any): Observable<any> {
    return this.http.get<any>(this.user_endpoint + username);
  }

  updateBankAccountDetails(username: any, bankCode:any, bankAccountNumber:any) {
    return this.http.put<any>(this.user_endpoint +"update-bank-details/" + username + "/" + bankCode +"/"+bankAccountNumber, null);
  }

}
