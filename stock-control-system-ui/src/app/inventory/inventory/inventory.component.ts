import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Observable, map, startWith } from 'rxjs';
import { InventoryService } from 'src/app/services/inventory.service';
import { ItemService } from 'src/app/services/item.service';
import { RacksService } from 'src/app/services/racks.service';

@Component({
  selector: 'app-inventory',
  templateUrl: './inventory.component.html',
  styleUrls: ['./inventory.component.css']
})
export class InventoryComponent {
  colorCode = "#FF6D60"
  isShowAddInventoryContainer = 'none';
  isShowEditInventoryContainer = 'none';
  isShowInventoryReceivedConfirmationContainer = 'none'
  inventoryForm: FormGroup;
  updateInventoryForm: FormGroup;
  items: any[] = []
  racks: any[] = []
  inventories: any[] = []
  inProgressInventories: any[] = []
  acceptedInventories: any[] = []
  inStockInventories: any[] = []
  displayEditDialog = 'none'

  constructor(private inventoryService: InventoryService,
    private itemService: ItemService,
    private rackService: RacksService) {
    this.getAllInventories();
    this.getAllItems();
    this.getAllRacks();
    this.inventoryForm = new FormGroup({
      itemId: new FormControl('', [Validators.required]),
      rackId: new FormControl('', [Validators.required]),
      count: new FormControl('', [Validators.required]),
      totalPrice: new FormControl('', [Validators.required]),
      status: new FormControl('IN_PROGRESS', [Validators.required])
    });
    this.updateInventoryForm = new FormGroup({
      id: new FormControl('', [Validators.required]),
      itemId: new FormControl('', [Validators.required]),
      rackId: new FormControl('', [Validators.required]),
      count: new FormControl('', [Validators.required]),
      totalPrice: new FormControl('', [Validators.required]),
      status: new FormControl('', [Validators.required])
    });
  }

  enableAddInventoryContainer() {
    this.isShowAddInventoryContainer = 'block';
  }
  disableAddInventoryContainer() {
    this.isShowAddInventoryContainer = 'none';
  }

  enableEditInventoryContainer(inventoryId: any) {
    var selectedInventory = this.inventories.find(inventory => inventory.id == inventoryId)
    
    this.updateInventoryForm.setValue({
      id: inventoryId,
      itemId: selectedInventory.itemId,
      rackId: selectedInventory.rackId,
      count: selectedInventory.count,
      totalPrice: selectedInventory.totalPrice,
      status: selectedInventory.status
    });
    this.isShowEditInventoryContainer = 'block';
  }

  disableEditInventoryContainer() {
    this.isShowEditInventoryContainer = 'none';
  }

  getAllInventories() {
    this.inventoryService.getAllInventorys().subscribe(
      (data) => {
        this.inventories = data;
        this.inProgressInventories = this.inventories.filter(invntory => invntory.status == 'IN_PROGRESS')
        this.acceptedInventories = this.inventories.filter(invntory => invntory.status == 'SUPPLIER_ACCEPTED')
        this.inStockInventories = this.inventories.filter(invntory => invntory.status == 'IN_STOCK')
      },
      (error) => {
        console.log(error);
      }
    );
  }

  addInventory() {
    this.inventoryService.createInventory(this.inventoryForm.value).subscribe(
      (data) => {
        this.isShowAddInventoryContainer = 'none';
        this.getAllInventories();
      },
      (error) => {
        console.log(error);
      }
    );
  }

  updateInventory() {
    this.inventoryService.updateInventory(this.updateInventoryForm.value).subscribe(
      (data) => {
        this.isShowEditInventoryContainer = 'none';
        this.getAllInventories();
      },
      (error) => {
        console.log(error);
      }
    );
  }


  deleteInventory(itemId: any) {
    this.inventoryService.deleteInventory(itemId).subscribe(
      (data) => {
        this.getAllInventories();
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

  getAllRacks() {
    this.rackService.getAllRacks().subscribe(
      (data) => {
        this.racks = data;
      },
      (error) => {
        console.log(error);
      }
    );
  }

  selectedInventoryId: any
  selectedSupplierId: any
  inventoryReceivedConfirmation(inventoryId: any, status: any, supplierId: any) {
    this.isShowInventoryReceivedConfirmationContainer = 'block';
    this.selectedInventoryId = inventoryId;
    this.selectedSupplierId = supplierId;
  }

  inventoryReceived() {
    this.inventoryService.updateInventoryByStatusAndSupplierId(this.selectedInventoryId, 'IN_STOCK', this.selectedSupplierId).subscribe(
      (data) => {
        this.getAllInventories()
        this.isShowInventoryReceivedConfirmationContainer = 'none';
      },
      (error) => {
        console.log(error);
      }
    );
  }

  inventoryReceivedDamaged() {
    this.inventoryService.updateInventoryByStatusAndSupplierId(this.selectedInventoryId, 'RETURN', this.selectedSupplierId).subscribe(
      (data) => {
        this.getAllInventories()
        this.isShowInventoryReceivedConfirmationContainer = 'none';
      },
      (error) => {
        console.log(error);
      }
    );
  }

  updateTotalPrice() {
    var inventory = this.inventoryForm.value;
    var item = this.items.find(item => item.id == inventory.itemId)
    this.inventoryForm.setValue({
      itemId: inventory.itemId,
      rackId: inventory.rackId,
      count: inventory.count,
      totalPrice: item.price * inventory.count,
      status: 'IN_PROGRESS',
    });
  }

  getItemName(itemId: string) {
    return this.items.find(item => item.id === itemId).name;
  }
}
