import { Component, OnInit, ViewChild } from '@angular/core';
import { ShipmentManagementService } from '../../services/shipment-management.service';
import { transition, animate, trigger, state, style } from '@angular/animations';
import { MatTableDataSource, MatSort, MatPaginator, MatDialog } from '@angular/material';
import * as moment from 'moment';
import { IPacket } from '../../interfaces/Packet';
import {STEPPER_GLOBAL_OPTIONS} from '@angular/cdk/stepper';
import { SettingsComponent } from '../settings/settings.component';
// import { timingSafeEqual } from 'crypto';

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

  displayedColumns: string[] = ['receivedDate', 'packetType', 'priority', 'currentStatus'];
  public packetList = [];
  public mydata = [];
  public transformedData = [];
  public expandedElement: any;
  public expandedDetail: any;

  constructor(
    private packetService: ShipmentManagementService,
    public dialog: MatDialog
    ) {

  }
  // tslint:disable-next-line: use-lifecycle-interface
  ngOnInit() {
    this.packetService.behaviourSubject.subscribe(data => {
      let temp: IPacket;
      data.forEach(d => {
        temp = d;
        // temp.receivedDate = moment(d.receivedDate, 'YYYYMMDD').fromNow();
        temp.receivedDate = moment().format('M/D/YYYY hh:mm:ss a');
        temp.currentStatus = temp.statusList[temp.statusList.length - 1].statusValue;
        // console.log(temp.currentStatus);

        this.mydata.push(temp);
      });

      this.mydata.forEach(dt => {
        const updatedList = [];
        dt.statusList.forEach(d => {
          const obj = {
            statusValue : d.statusValue,
            timeStamp : moment(d.timeStamp, 'YYYYMMDD').fromNow()
          };
          updatedList.push(obj);
        });
        dt.statusList = updatedList;
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
  openConfig(): void {
    const dialogRef = this.dialog.open(SettingsComponent, {
      width: '60vw',
      height: '70vh',
      data: {}
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed');
    });
  }


}
