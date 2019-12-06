import { Component, OnInit, ViewChild } from '@angular/core';
import { ShipmentManagementService } from '../../services/shipment-management.service';
import { transition, animate, trigger, state, style } from '@angular/animations';
import { MatTableDataSource, MatSort, MatPaginator } from '@angular/material';
import * as moment from 'moment';
import { IPacket } from '../../interfaces/Packet';
import {STEPPER_GLOBAL_OPTIONS} from '@angular/cdk/stepper';

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
  providers: [{
    provide: STEPPER_GLOBAL_OPTIONS, useValue: {displayDefaultIndicatorType: false}
  }]
})


export class OrderDetailsComponent implements OnInit {
  public dataSource;
  @ViewChild(MatSort, { static: true }) sort: MatSort;

  @ViewChild(MatPaginator, { static: true }) paginator: MatPaginator;

  displayedColumns: string[] = ['receivedDate', 'packetType', 'priority', 'currentStatus',  'packetDescription'];
  public packetList = [];
  public mydata = [];
  public expandedElement: any;
  public expandedDetail: any;

  constructor(private packetService: ShipmentManagementService) {

  }
  // tslint:disable-next-line: use-lifecycle-interface
  ngOnInit() {
    this.packetService.behaviourSubject.subscribe(data => {
      let temp;
      data.forEach(d => {
        temp = d;
        temp.receivedDate = moment(d.receivedDate, 'YYYYMMDD').fromNow();
        this.mydata.push(temp);
      });

      this.dataSource = new MatTableDataSource(this.mydata);
      this.dataSource.sort = this.sort;
      this.dataSource.paginator = this.paginator;
      this.packetList = data;
      console.log(this.packetList);

    });
  }
  applyFilter(filterValue: string) {
    this.dataSource.filter = filterValue.trim();
  }

  funColor(priority) {


    if (priority === 'HIGH') {
      return {

        color: 'red'
      };
    } else if (priority === 'MEDIUM') {
      return {
        color: 'orange'
      };
    } else {
      return {
        color: 'green'
      };
    }

  }

  statusColor(currentStatus) {
    switch (currentStatus) {
      case 'RECEIVED': {
          return{
            color: 'blue'
          };
          break;
        }
      case 'PROCESSED' : {
        return {
          color: 'orange'
        };
        break;
      }
      case 'DISPATCHED': {
        return{
          color: 'yellow'
        };
        break;
      }
      case 'DELIVERED': {
        return {
          color: 'green'
        };
        break;
      }
      case 'UNDELIVERED': {
        return {
          color: 'red'
        };
        break;
      }
    }
  }


}
