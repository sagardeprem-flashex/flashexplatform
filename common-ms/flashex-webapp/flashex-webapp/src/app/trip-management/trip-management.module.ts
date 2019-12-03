import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HomeComponent } from './components/home/home.component';
import { TripDetailsComponent } from './components/trip-details/trip-details.component';
import { OrderDetailsComponent } from './components/order-details/order-details.component';
import { LiveTrackingComponent } from './components/live-tracking/live-tracking.component';
import { VehicleDetailsComponent } from './components/vehicle-details/vehicle-details.component';
import { TripManagementRoutingModule } from './trip-management-routing.module';
import { HttpClientModule } from '@angular/common/http';
import { MaterialModule } from '../material/material.module';
import { TripPlanningPropertiesComponent } from './components/trip-planning-properties/trip-planning-properties.component';
import { ReactiveFormsModule } from '@angular/forms';
import { TripItineraryService } from './services/trip-itinerary.service';
import { DashboardComponent } from './components/dashboard/dashboard.component';



@NgModule({
  declarations: [
    HomeComponent,
    TripDetailsComponent,
    OrderDetailsComponent,
    LiveTrackingComponent,
    VehicleDetailsComponent,
    TripPlanningPropertiesComponent,
    DashboardComponent
  ],
  imports: [
    CommonModule,
    TripManagementRoutingModule,
    HttpClientModule,
    MaterialModule,
    ReactiveFormsModule
  ],
  entryComponents: [
    TripPlanningPropertiesComponent
  ],
  exports: [
    HomeComponent,
    TripDetailsComponent,
    OrderDetailsComponent,
    LiveTrackingComponent,
    VehicleDetailsComponent,
    TripPlanningPropertiesComponent
  ],
  providers: [TripItineraryService],

})
export class TripManagementModule { }