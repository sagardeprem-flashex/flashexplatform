import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HomeComponent } from './components/home/home.component';
import { TripDetailsComponent } from './components/trip-details/trip-details.component';
import { OrderDetailsComponent } from './components/order-details/order-details.component';
import { LiveTrackingComponent } from './components/live-tracking/live-tracking.component';
import { VehicleDetailsComponent } from './components/vehicle-details/vehicle-details.component';
import { TripManagementRoutingModule } from './trip-management-routing.module';



@NgModule({
  declarations: [HomeComponent, TripDetailsComponent, OrderDetailsComponent, LiveTrackingComponent, VehicleDetailsComponent],
  imports: [
    CommonModule,
    TripManagementRoutingModule
  ],
  exports: [HomeComponent, TripDetailsComponent, OrderDetailsComponent, LiveTrackingComponent, VehicleDetailsComponent]
})
export class TripManagementModule { }
