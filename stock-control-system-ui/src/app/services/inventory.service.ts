import { Subject, Observable } from 'rxjs';
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class InventoryService {

  private inventory_endpoint: string = `http://localhost:8080/api/inventory`; 

  constructor(private http: HttpClient) {
  }

  createInventory(data: any): Observable<any> {
    return this.http.post<any>(this.inventory_endpoint, data);
  }

  getAllInventorys(): Observable<any> {
    return this.http.get<any>(this.inventory_endpoint);
  }

  getAllAvailableInventoriesByItemId(itemId: any, status:any): Observable<any> {
    return this.http.get<any>(this.inventory_endpoint + '/'+itemId+'/status/' + status);
  }

  updateInventory(data: any): Observable<any> {
    return this.http.put<any>(this.inventory_endpoint, data);
  }

  updateInventoryByStatusAndSupplierId(inventoryId: any, status: any, supplierId: any): Observable<any> {
    return this.http.put<any>(this.inventory_endpoint + '/' + inventoryId + '/status/' + status + '/supplier/' + supplierId, null);
  }

  updateInventoryByStatus(inventoryId: any, status: any): Observable<any> {
    return this.http.put<any>(this.inventory_endpoint + '/' + inventoryId + '/status/' + status, null);
  }

  deleteInventory(inventoryId: any): Observable<any> {
    return this.http.delete<any>(this.inventory_endpoint + '/' + inventoryId);
  }
}
