import { Subject, Observable } from 'rxjs';
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class ConsumeritemService {

  private consumer_item_endpoint: string = `http://localhost:8080/api/consumeritem`; 

  constructor(private http: HttpClient) {
  }

  createConsumerItem(data: any): Observable<any> {
    return this.http.post<any>(this.consumer_item_endpoint, data);
  }

  getAllConsumerItems(): Observable<any> {
    return this.http.get<any>(this.consumer_item_endpoint);
  }

  updateStockIdAndStatus(consumerItemId: any, stockId: any, status: any): Observable<any> {
    return this.http.put<any>(this.consumer_item_endpoint + '/' + consumerItemId+'/stock/'+stockId +'/status/'+status, null);
  }

  deleteConsumerItem(itemId: any): Observable<any> {
    return this.http.delete<any>(this.consumer_item_endpoint + '/' + itemId);
  }
}
