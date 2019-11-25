import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { HomeComponent } from './components/home/home.component';
import { LiveTrackingComponent } from './components/live-tracking/live-tracking.component';
import { VehicleDetailsComponent } from './components/vehicle-details/vehicle-details.component';
import { TripDetailsComponent } from './components/trip-details/trip-details.component';
import { OrderDetailsComponent } from './components/order-details/order-details.component';
import { AuthGuard } from '../shared/auth/auth-guard';


const tripRoutes: Routes = [
  {
    path: 'home',
    component: HomeComponent,
    canActivate: [AuthGuard]
  },
  {
    path: 'track' , component: LiveTrackingComponent
  },
  {
    path: 'vehicle' , component: VehicleDetailsComponent
  },
  {
    path: 'trips' , component: TripDetailsComponent
  },
  {
    path: 'orders' , component: OrderDetailsComponent
  }
];

@NgModule({
  imports: [RouterModule.forChild(tripRoutes)],
exports: [RouterModule],
})
export class TripManagementRoutingModule { }
