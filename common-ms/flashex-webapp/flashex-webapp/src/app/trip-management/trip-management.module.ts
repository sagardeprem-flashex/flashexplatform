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
import { ReactiveFormsModule, FormsModule } from '@angular/forms';
import { TripItineraryService } from './services/trip-itinerary.service';
import { DashboardComponent } from './components/dashboard/dashboard.component';
import { LiveTrackingGoogleComponent } from './components/live-tracking-google/live-tracking-google.component';
import { AgmCoreModule } from '@agm/core';
import { AgmDirectionModule } from 'agm-direction';
import { SharedModule } from '../shared/shared.module';
import { SettingsComponent } from './components/settings/settings.component';




@NgModule({
  declarations: [
    HomeComponent,
    TripDetailsComponent,
    OrderDetailsComponent,
    LiveTrackingComponent,
    VehicleDetailsComponent,
    TripPlanningPropertiesComponent,
    DashboardComponent,
    LiveTrackingGoogleComponent,
    SettingsComponent
  ],
  imports: [
    CommonModule,
    TripManagementRoutingModule,
    HttpClientModule,
    MaterialModule,
    ReactiveFormsModule,
    FormsModule,
    AgmCoreModule.forRoot({
      apiKey: 'AIzaSyBLw09tedbxbyfc0Fgpy9Z30XhK53ClJDk'
   }),
   AgmDirectionModule,
   SharedModule

  ],
  entryComponents: [
    TripPlanningPropertiesComponent,
    SettingsComponent
  ],
  exports: [
    HomeComponent,
    TripDetailsComponent,
    OrderDetailsComponent,
    LiveTrackingComponent,
    VehicleDetailsComponent,
    TripPlanningPropertiesComponent,
    SettingsComponent
  ],
  providers: [TripItineraryService],

})
export class TripManagementModule { }
