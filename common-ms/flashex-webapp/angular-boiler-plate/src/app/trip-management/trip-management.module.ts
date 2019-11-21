import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HomeComponent } from './components/home/home.component';
import { TripDetailsComponent } from './components/trip-details/trip-details.component';
import { OrderDetailsComponent } from './components/order-details/order-details.component';
import { LiveTrackingComponent } from './components/live-tracking/live-tracking.component';
import { VehicleDetailsComponent } from './components/vehicle-details/vehicle-details.component';
import { TripManagementRoutingModule } from './trip-management-routing.module';
import { AgmCoreModule } from '@agm/core';
import { HttpClientModule } from '@angular/common/http';
import { MaterialModule } from '../material/material.module';
import { AgmDirectionModule } from 'agm-direction';
import { TripItineraryService } from './services/trip-itinerary.service';



@NgModule({
  declarations: [HomeComponent, TripDetailsComponent, OrderDetailsComponent, LiveTrackingComponent, VehicleDetailsComponent],
  imports: [
    CommonModule,
    TripManagementRoutingModule,
    HttpClientModule,
    AgmCoreModule.forRoot({
      apiKey: 'AIzaSyDWuoodBo_sLP8B1_wWVDwkyGwaavc3UUY'
    }),
    MaterialModule,
    AgmDirectionModule

  ],
  providers: [TripItineraryService],
  exports: [HomeComponent, TripDetailsComponent, OrderDetailsComponent, LiveTrackingComponent, VehicleDetailsComponent]
})
export class TripManagementModule { }
