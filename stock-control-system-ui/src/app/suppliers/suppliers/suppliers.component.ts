import { Component } from '@angular/core';
import { SupplierService } from 'src/app/services/supplier.service';

@Component({
  selector: 'app-suppliers',
  templateUrl: './suppliers.component.html',
  styleUrls: ['./suppliers.component.css']
})
export class SuppliersComponent {

  colorCode = "#A084DC"

  constructor(private supplierService: SupplierService) { 
    this.getAllSuppliers()
  }

  suppliers: any

  getAllSuppliers() {
    this.supplierService.getAllsuppliers().subscribe(
      (data) => {
        this.suppliers = data;
      },
      (error) => {
        console.log(error);
      }
    );
  }

  updateSupplierStatus(supplierId: any, status: any) {
    this.supplierService.updateSupplierStatus(supplierId, status).subscribe(
      (data) => {
        this.getAllSuppliers()
      },
      (error) => {
        console.log(error);
      }
    );
  }

  deleteSupplier(supplierId: any) {
    this.supplierService.deleteSupplier(supplierId).subscribe(
      (data) => {
        this.getAllSuppliers()
      },
      (error) => {
        console.log(error);
      }
    );
  }
  
}
