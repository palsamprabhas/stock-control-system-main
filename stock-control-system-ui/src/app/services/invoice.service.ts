import { Subject, Observable, map } from 'rxjs';
import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class InvoiceService {

  private generate_supplier_invoice_endpoint: string = `http://localhost:8080/api/invoice/generate/supplier/`;
  private generate_consumer_invoice_endpoint: string = `http://localhost:8080/api/invoice/generate/consumer/`;

  constructor(private http: HttpClient) {
  }


  generateSupplierInvoice(id: any) {
    let requestHeaderOptions = new HttpHeaders({
      'Content-Type': 'application/json',
      'Accept': 'application/pdf'
    });
    let requestOptions = { headers: requestHeaderOptions, responseType: 'blob' as 'blob' };
    this.http.post(this.generate_supplier_invoice_endpoint + id, null, requestOptions).pipe(map((data: any) => {
      let blob = new Blob([data], {
        type: 'application/pdf'
      });
      var link = document.createElement('a');
      link.href = window.URL.createObjectURL(blob);
      link.target = '_blank';
      link.click();
      window.URL.revokeObjectURL(link.href);
    })).subscribe((result: any) => {
    });;
  }

  generateConsumerInvoice(id: any) {
    let requestHeaderOptions = new HttpHeaders({
      'Content-Type': 'application/json',
      'Accept': 'application/pdf'
    });
    let requestOptions = { headers: requestHeaderOptions, responseType: 'blob' as 'blob' };
    this.http.post(this.generate_consumer_invoice_endpoint + id, null, requestOptions).pipe(map((data: any) => {
      let blob = new Blob([data], {
        type: 'application/pdf'
      });
      var link = document.createElement('a');
      link.href = window.URL.createObjectURL(blob);
      link.target = '_blank';
      link.click();
      window.URL.revokeObjectURL(link.href);
    })).subscribe((result: any) => {
    });;
  }

}
