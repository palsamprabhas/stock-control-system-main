import { Component, ViewChild } from '@angular/core';
import { ChartConfiguration, ChartData, ChartEvent, ChartType } from 'chart.js';
import { BaseChartDirective } from 'ng2-charts';
import { DashboardService } from 'src/app/services/dashboard.service';
import { InventoryService } from 'src/app/services/inventory.service';
import { ItemService } from 'src/app/services/item.service';

@Component({
  selector: 'app-homepage',
  templateUrl: './homepage.component.html',
  styleUrls: ['./homepage.component.css']
})
export class HomepageComponent {
  @ViewChild(BaseChartDirective) chart: BaseChartDirective | undefined;

  public pieChartType: ChartType = 'pie';
  public pieChartOptions: ChartConfiguration['options'] = {
    responsive: true
  };
  public overallConsumersPieChartData: any;
  public overallSuppliersPieChartData: any;
  public inventoryReportData: any;

  public barChartOptions: ChartConfiguration['options'] = {
    responsive: true,
  };
  public barChartType: ChartType = 'bar';


  items: any[] = []
  dangorItems: any[] = []
  warnItems: any[] = []
  infoItems: any[] = []

  constructor(private dashboardService: DashboardService,
    private itemService: ItemService,
    private inventoryService: InventoryService) {
    dashboardService.getOverallConsumersReport().subscribe(
      (data) => {
        this.overallConsumersPieChartData = {
          labels: ["APPROVED", "REJECTED", 'PENDING'],
          datasets: [{
            data: data
          }]
        };
      },
      (error) => {
        console.log(error)
      }
    )


    dashboardService.getOverallSuppliersReport().subscribe(
      (data) => {
        this.overallSuppliersPieChartData = {
          labels: ["APPROVED", "REJECTED", 'PENDING'],
          datasets: [{
            data: data
          }]
        };
      },
      (error) => {
        console.log(error)
      }
    )

    inventoryService.getAllInventorys().subscribe(
      (data) => {
        let itemNames = Array.from(new Set(data.map((inventory: any) => inventory.item.name)))
        let inprogressInventory: any[] = [];
        let acceptedInventory: any[] = [];
        let instockInventory: any[] = [];
        for (let itemName of itemNames) {
          let inprogressCount = 0;
          let acceptedCount = 0;
          let inStockCount = 0;
          for (var inventory of data) {
            if (inventory.item.name == itemName && inventory.status == 'IN_PROGRESS') {
              inprogressCount += inventory.count;
            }
            if (inventory.item.name == itemName && inventory.status == 'SUPPLIER_ACCEPTED') {
              acceptedCount += inventory.count;
            }
            if (inventory.item.name == itemName && inventory.status == 'IN_STOCK') {
              inStockCount += inventory.count;
            }
          }
          inprogressInventory.push(inprogressCount)
          acceptedInventory.push(acceptedCount)
          instockInventory.push(inStockCount)
        }

        console.log(inprogressInventory);
        console.log(acceptedInventory);
        console.log(instockInventory);


        this.inventoryReportData = {
          labels: itemNames,
          datasets: [
            { data: inprogressInventory, label: 'IN_PROGRESS' },
            { data: acceptedInventory, label: 'SUPPLIER_ACCEPTED' },
            { data: instockInventory, label: 'IN_STOCK' },
          ]
        };
      },
      (error) => {
        console.log(error)
      }
    )

    itemService.getAllItems().subscribe(
      (data) => {
        this.items = data;
        this.dangorItems = this.items.filter(item => {
          let expiryDate: any = new Date(item.expiryDate);
          let currentDate: any = new Date();
          var diffDays: any = Math.floor((expiryDate - currentDate) / (1000 * 60 * 60 * 24));
          return diffDays < 0;
        })

        this.warnItems = this.items.filter(item => {
          let expiryDate: any = new Date(item.expiryDate);
          let currentDate: any = new Date();
          var diffDays: any = Math.floor((expiryDate - currentDate) / (1000 * 60 * 60 * 24));
          return diffDays >= 0 && diffDays < 30;
        })

        this.infoItems = this.items.filter(item => {
          let expiryDate: any = new Date(item.expiryDate);
          let currentDate: any = new Date();
          var diffDays: any = Math.floor((expiryDate - currentDate) / (1000 * 60 * 60 * 24));
          return diffDays >= 31 && diffDays < 90;
        })
      },
      (error) => {
        console.log(error);
      }
    );
  }

}
