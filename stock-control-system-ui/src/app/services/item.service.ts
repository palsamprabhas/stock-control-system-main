import { Subject, Observable } from 'rxjs';
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class ItemService {

  private item_endpoint: string = `http://localhost:8080/api/item`; 

  constructor(private http: HttpClient) {
  }

  createItem(data: any): Observable<any> {
    return this.http.post<any>(this.item_endpoint, data);
  }

  getAllItems(): Observable<any> {
    return this.http.get<any>(this.item_endpoint);
  }

  updateItem(data: any): Observable<any> {
    return this.http.put<any>(this.item_endpoint, data);
  }

  deleteItem(itemId: any): Observable<any> {
    return this.http.delete<any>(this.item_endpoint + '/' + itemId);
  }
}
