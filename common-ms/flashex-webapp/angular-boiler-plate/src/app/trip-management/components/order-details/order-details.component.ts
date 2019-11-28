import { Component, OnInit } from '@angular/core';
import { ShipmentManagementService } from '../../services/shipment-management.service';
import { transition, animate, trigger, state, style } from '@angular/animations';

@Component({
  selector: 'app-order-details',
  templateUrl: './order-details.component.html',
  styleUrls: ['./order-details.component.css'],
  animations: [
    trigger('detailExpand', [
      state('collapsed', style({ height: '0px', minHeight: '0' })),
      state('expanded', style({ height: '*' })),
      transition('expanded <=> collapsed', animate('225ms cubic-bezier(0.4, 0.0, 0.2, 1)')),
    ]),
  ],
})


export class OrderDetailsComponent implements OnInit {


  columnsToDisplay = ['packetId', 'packetDescription', 'packetType', 'receivedDate'];
  public dataSource;
  constructor(private packetService: ShipmentManagementService) {

  }

  ngOnInit() {
    this.packetService.behaviourSubject.subscribe(data => {
      this.dataSource = data;
      console.log(this.dataSource);

    });
  }



}
