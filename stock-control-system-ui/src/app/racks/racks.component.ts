import { Component } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { RacksService } from '../services/racks.service';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-racks',
  templateUrl: './racks.component.html',
  styleUrls: ['./racks.component.css']
})
export class RacksComponent {

  rackForm: FormGroup;
  racks: any[] = []
  damagedRackNotExist = true;

  constructor(private rackService: RacksService, private matSnackBar: MatSnackBar) {
    this.getAllRacks();
    this.rackForm = new FormGroup({
      name: new FormControl('', [Validators.required]),
      damagedRack: new FormControl(false),
    });
  }

  getAllRacks() {
    this.rackService.getAllRacks().subscribe(
      (data) => {
        this.racks = data;
        this.damagedRackNotExist = this.racks.filter(rack => rack.damagedRack).length == 0;
      },
      (error) => {
        console.log(error);
      }
    );
  }

  addRack() {
    this.rackService.createRack(this.rackForm.value).subscribe(
      (data) => {
        this.getAllRacks();
        this.rackForm.reset();
        let snackBarRef = this.matSnackBar.open(
          'Rack has been added successfully', 
          'Dismiss',
          {
            duration: 8000, 
            horizontalPosition: 'center',
            verticalPosition: 'bottom',
          }
        );
      },
      (error) => {
        console.log(error);
      }
    );
  }

  deleteRack(rackId: any) {
    this.rackService.deleteRack(rackId).subscribe(
      (data) => {        
        this.getAllRacks();     
        let snackBarRef = this.matSnackBar.open(
          'Rack has been deleted successfully', 
          'Dismiss',
          {
            duration: 8000, 
            horizontalPosition: 'center',
            verticalPosition: 'bottom',
          }
        );              
      },
      (error) => {
        console.log(error);
      }
    ); 
  }

}
