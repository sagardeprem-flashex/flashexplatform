import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { OrderDeliveryListComponent } from './components/order-delivery-list/order-delivery-list.component';
import { NavigationComponent } from './components/navigation/navigation.component';
import { MaterialModule } from '../material/material.module';
import { TripsComponent } from './components/trips/trips.component';
import { DeliveryExecutiveRoutingModule } from './delivery-executive-routing.module';
import { DeliveryExecutiveHomeComponent } from './components/delivery-executive-home/delivery-executive-home.component';



@NgModule({
  declarations: [OrderDeliveryListComponent, NavigationComponent, TripsComponent, DeliveryExecutiveHomeComponent],
  imports: [
    CommonModule,
    DeliveryExecutiveRoutingModule,
    MaterialModule
  ],
  exports: [OrderDeliveryListComponent, NavigationComponent, TripsComponent, MaterialModule, DeliveryExecutiveHomeComponent]
})
export class DeliveryExecutiveModule { }
