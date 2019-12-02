import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { OrderDeliveryListComponent } from './components/order-delivery-list/order-delivery-list.component';
import { NavigationComponent } from './components/navigation/navigation.component';
import { MaterialModule } from '../material/material.module';
import { TripsComponent } from './components/trips/trips.component';
import { DeliveryExecutiveRoutingModule } from './delivery-executive-routing.module';




@NgModule({
  declarations: [OrderDeliveryListComponent, NavigationComponent, TripsComponent ],
  imports: [
    CommonModule,
    DeliveryExecutiveRoutingModule,
    MaterialModule
  ],
  exports: [OrderDeliveryListComponent, NavigationComponent, TripsComponent, MaterialModule ]
})
export class DeliveryExecutiveModule { }
