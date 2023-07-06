import { Component } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { ConsumeritemService } from 'src/app/services/consumeritem.service';
import { InvoiceService } from 'src/app/services/invoice.service';
import { ItemService } from 'src/app/services/item.service';

@Component({
  selector: 'app-consumer-home',
  templateUrl: './consumer-home.component.html',
  styleUrls: ['./consumer-home.component.css']
})
export class ConsumerHomeComponent {
  colorCode = "#FFED00"
  isShowRequestItemsContainer = 'none';
  consumerItemForm: FormGroup;
  consumerItems: any[] = []
  pendingConsumerItems: any[] = []
  approvedConsumerItems: any[] = []
  receivedConsumerItems: any[] = []
  returnedConsumerItems: any[] = []
  items: any[] = []
  displayEditDialog = 'none'
  isShowItemReceivedConfirmationContainer = 'none'

  constructor(
    private consumerItemService: ConsumeritemService,
    private itemService: ItemService,
    private invoiceService: InvoiceService) {
    this.getAllConumserItems();
    this.getAllItems()
    this.consumerItemForm = new FormGroup({
      itemId: new FormControl('', [Validators.required]),
      count: new FormControl('', [Validators.required]),
      status: new FormControl('IN_PROGRESS', [Validators.required]),
      consumerId: new FormControl('', [Validators.required]),
      totalPrice: new FormControl('', [Validators.required]),
    });
  }

  enableRequestItemsContainer() {
    this.isShowRequestItemsContainer = 'block';
  }
  disableRequestItemsContainer() {
    this.isShowRequestItemsContainer = 'none';
  }


  getAllConumserItems() {
    this.consumerItemService.getAllConsumerItems().subscribe(
      (data) => {
        var consumerId = localStorage.getItem('username')
        this.consumerItems = data;
        this.pendingConsumerItems = this.consumerItems.filter(consumerItem => consumerItem.status == 'IN_PROGRESS' && consumerItem.consumerId == consumerId)
        this.approvedConsumerItems = this.consumerItems.filter(consumerItem => consumerItem.status == 'APPROVED' && consumerItem.consumerId == consumerId)
        this.receivedConsumerItems = this.consumerItems.filter(consumerItem => consumerItem.status == 'RECEIVED' && consumerItem.consumerId == consumerId)
        this.returnedConsumerItems = this.consumerItems.filter(consumerItem => consumerItem.status == 'RETURN' && consumerItem.consumerId == consumerId)
      },
      (error) => {
        console.log(error);
      }
    );
  }

  addConsumerItem() {
    this.consumerItemForm.controls['consumerId'].setValue(localStorage.getItem('username'));
    this.consumerItemService.createConsumerItem(this.consumerItemForm.value).subscribe(
      (data) => {
        this.isShowRequestItemsContainer = 'none';
        this.getAllConumserItems();
      },
      (error) => {
        console.log(error);
      }
    );
  }


  deleteConsumerItem(itemId: any) {
    this.consumerItemService.deleteConsumerItem(itemId).subscribe(
      (data) => {
        this.getAllConumserItems();
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

  selectedConsumerItemId: any;
  selectedStockId: any
  consumerItemReceivedConfirmation(conseumerItemId: any, stockId: any) {
    this.isShowItemReceivedConfirmationContainer = 'block'
    this.selectedConsumerItemId = conseumerItemId;
    this.selectedStockId = stockId;
  }

  consumerItemsReceived() {
    this.consumerItemService.updateStockIdAndStatus(this.selectedConsumerItemId, this.selectedStockId, "RECEIVED").subscribe(
      (data) => {
        this.getAllConumserItems();
        this.isShowItemReceivedConfirmationContainer = 'none'
      },
      (error) => {
        console.log(error);
      }
    );
  }

  consumerItemsReceivedDamaged() {
    this.consumerItemService.updateStockIdAndStatus(this.selectedConsumerItemId, this.selectedStockId, "RETURN").subscribe(
      (data) => {
        this.getAllConumserItems();
        this.isShowItemReceivedConfirmationContainer = 'none'
      },
      (error) => {
        console.log(error);
      }
    );
  }

  generateInvoice(id: any) {
    this.invoiceService.generateConsumerInvoice(id);
  }

  updateTotalPrice() {
    var consumerItem = this.consumerItemForm.value;
    var item = this.items.find(item => item.id == consumerItem.itemId)
    this.consumerItemForm.controls['totalPrice'].setValue(item.price * consumerItem.count);
  }
}
