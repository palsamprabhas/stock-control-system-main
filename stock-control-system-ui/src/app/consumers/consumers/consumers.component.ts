import { Component } from '@angular/core';
import { ConsumerService } from 'src/app/services/consumer.service';

@Component({
  selector: 'app-consumers',
  templateUrl: './consumers.component.html',
  styleUrls: ['./consumers.component.css']
})
export class ConsumersComponent {
  
  constructor(private consumerService: ConsumerService) { 
    this.getAllConsumers()
  }

  consumers: any

  getAllConsumers() {
    this.consumerService.getAllConsumers().subscribe(
      (data) => {
        this.consumers = data;
      },
      (error) => {
        console.log(error);
      }
    );
  }

  updateConsumerStatus(consumerId: any, status: any) {
    this.consumerService.updateConsumerStatus(consumerId, status).subscribe(
      (data) => {
        this.getAllConsumers()
      },
      (error) => {
        console.log(error);
      }
    );
  }

  deleteConsumer(consumerId: any) {
    this.consumerService.deleteConsumer(consumerId).subscribe(
      (data) => {
        this.getAllConsumers()
      },
      (error) => {
        console.log(error);
      }
    );
  }
}
