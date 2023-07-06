import { Component } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { MatDatepickerInputEvent } from '@angular/material/datepicker';
import { ItemService } from 'src/app/services/item.service';

@Component({
  selector: 'app-items',
  templateUrl: './items.component.html',
  styleUrls: ['./items.component.css']
})
export class ItemsComponent {

  colorCode = "#F2921D"
  isShowAddItemContainer = 'none';
  isShowEditItemContainer = 'none';
  itemForm: FormGroup;
  updateItemForm: FormGroup;
  items: any[] =[]
  displayEditDialog = 'none'

  constructor(private itemService: ItemService) { 
    this.getAllItems();
    this.itemForm = new FormGroup({
      name: new FormControl('', [Validators.required]),
      description: new FormControl('', [Validators.required]),
      imageUrl: new FormControl('', [Validators.required]),
      price: new FormControl('', [Validators.required]), 
      expiryDate: new FormControl('', [Validators.required]), 
    });
    this.updateItemForm = new FormGroup({
      id: new FormControl('', [Validators.required]),
      name: new FormControl('', [Validators.required]),
      description: new FormControl('', [Validators.required]),
      imageUrl: new FormControl('', [Validators.required]),
      price: new FormControl('', [Validators.required]), 
      expiryDate: new FormControl('', [Validators.required]), 
    });
  }  
     
  enableAddItemContainer() {  
    this.isShowAddItemContainer = 'block';  
  }  
  disableAddItemContainer() {  
    this.isShowAddItemContainer = 'none';  
  }  

  enableEditItemContainer(itemId: any) {  
    var selectedItem =  this.items.find(item => item.id == itemId)
    this.updateItemForm.setValue({ 
      id: selectedItem.id,    
      name: selectedItem.name,
      imageUrl: selectedItem.imageUrl,
      description: selectedItem.description,
      price: selectedItem.price,
      expiryDate: selectedItem.expiryDate
    }); 
    console.log(this.updateItemForm.value);
    
    this.isShowEditItemContainer = 'block';  
  }  
  disableEditItemContainer() {  
    this.isShowEditItemContainer = 'none';  
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

  addItem() {      
    this.itemService.createItem(this.itemForm.value).subscribe(
      (data) => {       
        this.isShowAddItemContainer = 'none'; 
        this.getAllItems(); 
      },
      (error) => {
        console.log(error);
      }
    ); 
  }

  updateItem() {   
    this.itemService.updateItem(this.updateItemForm.value).subscribe(
      (data) => {       
        this.isShowEditItemContainer = 'none'; 
        this.getAllItems(); 
      },
      (error) => {
        console.log(error);
      }
    ); 
  }


  deleteItem(itemId: any) {
    this.itemService.deleteItem(itemId).subscribe(
      (data) => {        
        this.getAllItems();                   
      },
      (error) => {
        console.log(error);
      }
    ); 
  }

}
