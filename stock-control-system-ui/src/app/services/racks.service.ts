import { Subject, Observable } from 'rxjs';
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class RacksService {

  private rack_endpoint: string = `http://localhost:8080/api/rack`; 

  constructor(private http: HttpClient) {
  }

  createRack(data: any): Observable<any> {
    return this.http.post<any>(this.rack_endpoint, data);
  }

  getAllRacks(): Observable<any> {
    return this.http.get<any>(this.rack_endpoint);
  }

  updateRack(data: any): Observable<any> {
    return this.http.put<any>(this.rack_endpoint, data);
  }

  deleteRack(rackId: any): Observable<any> {
    return this.http.delete<any>(this.rack_endpoint + '/' + rackId);
  }
}
