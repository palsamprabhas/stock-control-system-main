import { Component } from '@angular/core';
import { InventoryService } from 'src/app/services/inventory.service';
import { InvoiceService } from 'src/app/services/invoice.service';
import { ItemService } from 'src/app/services/item.service';

@Component({
  selector: 'app-supplier-home',
  templateUrl: './supplier-home.component.html',
  styleUrls: ['./supplier-home.component.css']
})
export class SupplierHomeComponent {

  items: any[] =[]
  inventories: any[] = []
  accptedInventoryRequests: any[] = []
  completedInventoryRequests: any[] = []
  returnedInventoryRequests: any[] = []
  newInventoryRequests: any[] = []
  colorCode = "#FFED00"

  constructor(private inventoryService: InventoryService,
     private itemService: ItemService,
     private invoiceService: InvoiceService) { 
    this.getAllInventories();
    this.getAllItems();
  }

  getAllInventories() {
    this.inventoryService.getAllInventorys().subscribe(
      (data) => {
        var supplierId = localStorage.getItem('username')
        this.inventories = data;
        this.newInventoryRequests = this.inventories.filter(invntory => invntory.status == 'IN_PROGRESS')
        this.accptedInventoryRequests = this.inventories.filter(invntory => invntory.supplierId == supplierId && invntory.status == 'SUPPLIER_ACCEPTED')
        this.completedInventoryRequests = this.inventories.filter(invntory => invntory.supplierId == supplierId && invntory.status == 'COMPLETED')
        this.returnedInventoryRequests = this.inventories.filter(invntory => invntory.supplierId == supplierId && invntory.status == 'RETURN')
      },
      (error) => {
        console.log(error);
      }
    );
  }

  getAllItems() {
    this.itemService.getAllItems().subscribe(
      (data) => {
        this.items = data;
      },
      (error) => {
        console.log(error);
      }
    );
  }

  inventoryAccepted(inventoryId:any, status: any){
    var supplierId = localStorage.getItem('username');
    this.inventoryService.updateInventoryByStatusAndSupplierId(inventoryId, status, supplierId).subscribe(
      (data) => {
        this.getAllInventories()
      },
      (error) => {
        console.log(error);
      }
    );
  }

  generateInvoice(id: any) {
    this.invoiceService.generateSupplierInvoice(id);
  }
}
