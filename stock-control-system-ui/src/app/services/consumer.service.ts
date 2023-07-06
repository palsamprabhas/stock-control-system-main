import { Subject, Observable } from 'rxjs';
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class ConsumerService {

    private create_consumer_endpoint: string = `http://localhost:8080/api/consumer`;
    private update_consumer_endpoint: string = `http://localhost:8080/api/consumer`;
    private get_all_consumers_endpoint: string = `http://localhost:8080/api/consumer`;
    private delete_consumer_endpoint: string = `http://localhost:8080/api/consumer`;
    
    constructor(private http: HttpClient) {
    }

    createConsumer(data: any): Observable<any> {
      return this.http.post<any>(this.create_consumer_endpoint, data);
    }

    getAllConsumers(): Observable<any> {
      return this.http.get<any>(this.get_all_consumers_endpoint);
    }

    updateConsumer(data:any): Observable<any> {
      return this.http.put<any>(this.update_consumer_endpoint,data);
    }

    updateConsumerStatus(consumerId: any, status: any): Observable<any> {
      return this.http.put<any>(this.update_consumer_endpoint + '/' + consumerId + '/' + status, null);
    }

    deleteConsumer(consumerId: any): Observable<any> {
      return this.http.delete<any>(this.delete_consumer_endpoint + '/' + consumerId);
    }
}
