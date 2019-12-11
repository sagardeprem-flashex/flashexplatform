import { Component, OnInit, Inject } from '@angular/core';
import { trigger, state, style, transition, animate } from '@angular/animations';
import { ShipmentManagementService } from '../../services/shipment-management.service';
import { IShipmentConfig } from '../../interfaces/ShipmentConfig';
import { MatSlideToggleChange } from '@angular/material/slide-toggle';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';

@Component({
  selector: 'app-settings',
  templateUrl: './settings.component.html',
  styleUrls: ['./settings.component.css'],
  animations: [
    trigger('detailExpand', [
      state('collapsed', style({ height: '0px', minHeight: '0' })),
      state('expanded', style({ height: '*' })),
      transition('expanded <=> collapsed', animate('225ms cubic-bezier(0.4, 0.0, 0.2, 1)')),
    ]),
  ],
})
export class SettingsComponent implements OnInit {


  shipmentConfig: IShipmentConfig;
  updatedConfig: IShipmentConfig;
  checked = false;
  disabled = false;
  max = 20;
  min = 5;
  steps = 1;
  changeOfConfig = false;
  tickInterval = 1;
  maxShipmentSize = 15;
  color = 'accent';
  pincodeChecked = true;
  orderTypeChecked = true;
  priorityChecked = false;
  // });

  constructor(
    private shipmentManagementService: ShipmentManagementService,
    public dialogRef: MatDialogRef<SettingsComponent>,
    @Inject(MAT_DIALOG_DATA) public data: IShipmentConfig
  ) {

  }

  ngOnInit() {
    this.shipmentManagementService.configSubject.subscribe(
      config => {
        this.shipmentConfig = config[0];
        this.updatedConfig = this.shipmentConfig;
        for (const strategy of config[0].groupStrategy) {
          if (strategy === 'PINCODE') {
            this.pincodeChecked = true;
          } else if (strategy === 'PACKET_TYPE') {
            this.orderTypeChecked = true;
          }
        }
      },
      error => {
        console.log(error);
      }
    );

    // set defaults

  }

  public togglePincode(event: MatSlideToggleChange) {
    console.log('toggle', event.checked);
    this.pincodeChecked = event.checked;
    if (this.pincodeChecked === false && this.orderTypeChecked === false && this.priorityChecked === false) {
      this.orderTypeChecked = true;
    }
  }

  public toggleType(event: MatSlideToggleChange) {
    console.log('toggle', event.checked);
    this.orderTypeChecked = event.checked;
    if (this.pincodeChecked === false && this.orderTypeChecked === false && this.priorityChecked === false) {
      this.pincodeChecked = true;
    }
  }

  public togglePriority(event: MatSlideToggleChange) {
    console.log('toggle', event.checked);
    this.priorityChecked = event.checked;
    if (this.pincodeChecked === false && this.orderTypeChecked === false && this.priorityChecked === false) {
      this.pincodeChecked = true;
    }
  }


  onNoClick(): void {
    this.dialogRef.close();
  }

  updateConfig() {

    this.updatedConfig.configDate = new Date();
    this.updatedConfig.maxShipmentSize = this.maxShipmentSize;
    this.updatedConfig.groupStrategy = [];
    if (this.pincodeChecked) {
      this.updatedConfig.groupStrategy.push('PINCODE');
    }
    if (this.orderTypeChecked) {
      this.updatedConfig.groupStrategy.push('PACKET_TYPE');
    }
    if (this.priorityChecked) {
      this.updatedConfig.groupStrategy.push('PRIORITY');
    }
    this.shipmentManagementService.updateConfig(this.updatedConfig);
  }
}

