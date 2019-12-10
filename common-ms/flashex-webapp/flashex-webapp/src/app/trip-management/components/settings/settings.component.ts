import { Component, OnInit, Directive, TemplateRef } from '@angular/core';
import { trigger, state, style, transition, animate } from '@angular/animations';
import { ShipmentManagementService } from '../../services/shipment-management.service';
import { IShipmentConfig } from '../../interfaces/ShipmentConfig';
import { FormGroup, FormControl, Validators } from '@angular/forms';

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


  public shipmentConfig: IShipmentConfig[];

  configGroup = new FormGroup({
    configId: new FormControl(this.shipmentConfig[0].configId, Validators.required)
  });

  constructor(private shipmentManagementService: ShipmentManagementService) {
    console.log('Getting config-->');

  }

  ngOnInit() {
    this.shipmentManagementService.configSubject.subscribe(
      config => {
        console.log(config);
        this.shipmentConfig = config;
        console.log(config);
      },
      error => {
        console.log(error);
      }
    );
  }

}


@Directive({
  // tslint:disable-next-line: directive-selector
  selector: '[viewMode]'
})
export class ViewModeDirective {

  constructor(public tpl: TemplateRef<any>) { }
}


@Directive({
  // tslint:disable-next-line: directive-selector
  selector: '[editMode]'
})
export class EditModeDirective {
  constructor(public tpl: TemplateRef<any>) { }
}
