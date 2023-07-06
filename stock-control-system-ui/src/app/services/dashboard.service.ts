import { Subject, Observable } from 'rxjs';
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class DashboardService {

  private dahsboard_endpoint: string = `http://localhost:8080/api/dashboard/`; 

  constructor(private http: HttpClient) {
  }

  
  getOverallConsumersReport(): Observable<any> {
    return this.http.get<any>(this.dahsboard_endpoint + 'overall-consumers-report');
  }

  getOverallSuppliersReport(): Observable<any> {
    return this.http.get<any>(this.dahsboard_endpoint + 'overall-suppliers-report');
  }
}
