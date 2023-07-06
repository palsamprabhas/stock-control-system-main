import { Component } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { ConsumeritemService } from 'src/app/services/consumeritem.service';
import { InventoryService } from 'src/app/services/inventory.service';
import { ItemService } from 'src/app/services/item.service';

@Component({
  selector: 'app-item-requests',
  templateUrl: './item-requests.component.html',
  styleUrls: ['./item-requests.component.css']
})
export class ItemRequestsComponent {
  colorCode = "#FFED00"
  isShowStockAvailableDialog = 'none'
  consumerItems: any[] = []
  pendingConsumerItems: any[] = []
  returnedConsumerItems: any[] = []
  availableInventory: any
  selectedConsumerItemId: any

  constructor(private consumerItemService: ConsumeritemService,
    private itemService: ItemService,
    private inventoryService: InventoryService) {
    this.getAllConumserItems();
  }

  diableShowStockAvailableDialog() {
    this.isShowStockAvailableDialog = 'none'
  }

  getAllConumserItems() {
    this.consumerItemService.getAllConsumerItems().subscribe(
      (data) => {
        this.consumerItems = data;
        this.pendingConsumerItems = this.consumerItems.filter(consumerItem => consumerItem.status == 'IN_PROGRESS')
        this.returnedConsumerItems = this.consumerItems.filter(consumerItem => consumerItem.status == 'RETURN')
      },
      (error) => {
        console.log(error);
      }
    );
  }

  getAvailableItemInventory(consumerItemId: any, itemId: any, count:number, status: any) {
    this.inventoryService.getAllAvailableInventoriesByItemId(itemId, status).subscribe(
      (data) => {
        this.availableInventory = data[0] ? data[0] : null;
        if(this.availableInventory == null || this.availableInventory.count < count) {
          this.availableInventory = null;
        }
        this.isShowStockAvailableDialog = 'block'
        this.selectedConsumerItemId = consumerItemId
      },
      (error) => {
        console.log(error);
      }
    );
  }

  approveConsumerItemsRequest(stockId: any, status: any) {
    this.consumerItemService.updateStockIdAndStatus(this.selectedConsumerItemId, stockId, status).subscribe(
      (data) => {
        this.getAllConumserItems();
        this.isShowStockAvailableDialog = 'none'
      },
      (error) => {
        console.log(error);
      }
    );
  }

}
