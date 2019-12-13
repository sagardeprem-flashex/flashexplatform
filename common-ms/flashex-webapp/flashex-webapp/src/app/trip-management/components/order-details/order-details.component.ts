import { Component, OnInit, ViewChild, Inject } from '@angular/core';
import { ShipmentManagementService } from '../../services/shipment-management.service';
import { transition, animate, trigger, state, style } from '@angular/animations';
import { MatTableDataSource, MatSort, MatPaginator, MatDialog, MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';
import * as moment from 'moment';
import { IPacket } from '../../interfaces/Packet';
import {STEPPER_GLOBAL_OPTIONS} from '@angular/cdk/stepper';
import { StatusDialogComponent } from '../status-dialog/status-dialog.component';
import { SettingsComponent } from '../settings/settings.component';
import { distinct } from 'rxjs/operators';

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
  public statuslist: Status[];
  @ViewChild(MatSort, { static: true }) sort: MatSort;

  @ViewChild(MatPaginator, { static: true }) paginator: MatPaginator;

  displayedColumns: string[] = [ 'packetId', 'receivedDate', 'packetType', 'priority', 'currentStatus'];
  public packetList = [];
  public mydata = [];
  public transformedData = [];
  public expandedElement: any;
  public expandedDetail: any;
  public dateList = [];



  constructor(private packetService: ShipmentManagementService, public dialog: MatDialog, public configDialog: MatDialog) {

  }
  // tslint:disable-next-line: use-lifecycle-interface
  ngOnInit() {
    this.packetService.behaviourSubject.subscribe(data => {
      let temp: IPacket;
      let dateValue;
      data.forEach(d => {
        temp = d;
        // temp.receivedDate = moment(d.receivedDate, 'YYYYMMDD').fromNow();
        temp.receivedDate = moment(d.receivedDate).format('D/M/YYYY hh:mm:ss a');
        temp.currentStatus = temp.statusList[temp.statusList.length - 1].statusValue;

        dateValue = moment(d.receivedDate).format('D/M/YYYY');
        this.dateList.push(dateValue);
        // console.log(temp.currentStatus);

        this.mydata.push(temp);
      });

      this.mydata.forEach(dt => {
        const updatedList = [];
        dt.statusList.forEach(d => {
          const obj = {
            statusValue : d.statusValue,
            timeStamp : moment(d.timeStamp).format('D/M/YYYY hh:mm:ss a')
          };
          updatedList.push(obj);
        });
        dt.statusList = updatedList;
      });



      this.dataSource = new MatTableDataSource(this.mydata);
      this.dataSource.sort = this.sort;
      this.dataSource.paginator = this.paginator;
      this.packetList = data;
      // console.log(this.packetList);
      console.log('date chya values ' + this.dateList);
      this.dateList = [... new Set(this.dateList)];
      console.log('date chya values ' + this.dateList);


    });
  }
  applyFilter(filterValue: string) {
    this.dataSource.filter = filterValue.trim();
  }

  funColor(priority) {


    if (priority === 'PREMIUM') {
      return {

        color: 'red'
      };
    } else if (priority === 'ORDINARY') {
      return {
        color: 'green'
      };
    } else {
      return {
        color: 'blue'
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
      case 'PROCESSING' : {
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
    const dialogRef = this.configDialog.open(SettingsComponent, {
      width: '415px',
      height: '70vh',
      data: {}
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed');
    });
  }


  openDialog(statusList, packetId): void {
    const dialogRef = this.dialog.open(StatusDialogComponent, { width: '30em',
       data: {status: statusList, packet: packetId}
    });
   // console.log(statusList);
  }
}

export interface Status {
  statusValue: string;
  timeStamp: Date;
}
