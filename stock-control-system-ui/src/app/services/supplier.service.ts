import { Subject, Observable } from 'rxjs';
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class SupplierService {

  private create_supplier_endpoint: string = `http://localhost:8080/api/supplier`;
  private update_supplier_endpoint: string = `http://localhost:8080/api/supplier`;
  private get_all_suppliers_endpoint: string = `http://localhost:8080/api/supplier`;
  private delete_supplier_endpoint: string = `http://localhost:8080/api/consumer`;

  constructor(private http: HttpClient) {
  }

  createsupplier(data: any): Observable<any> {
    return this.http.post<any>(this.create_supplier_endpoint, data);
  }

  getAllsuppliers(): Observable<any> {
    return this.http.get<any>(this.get_all_suppliers_endpoint);
  }

  updatesupplier(data: any): Observable<any> {
    return this.http.put<any>(this.update_supplier_endpoint, data);
  }

  updateSupplierStatus(supplierId: any, status: any): Observable<any> {
    return this.http.put<any>(this.delete_supplier_endpoint + '/' + supplierId + '/' + status, null);
  }

  deleteSupplier(supplierId: any): Observable<any> {
    return this.http.delete<any>(this.delete_supplier_endpoint + '/' + supplierId);
  }
}
