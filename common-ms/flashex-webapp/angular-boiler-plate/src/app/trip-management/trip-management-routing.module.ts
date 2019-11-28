import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { HomeComponent } from './components/home/home.component';
import { LiveTrackingComponent } from './components/live-tracking/live-tracking.component';
import { VehicleDetailsComponent } from './components/vehicle-details/vehicle-details.component';
import { TripDetailsComponent } from './components/trip-details/trip-details.component';
import { OrderDetailsComponent } from './components/order-details/order-details.component';
import { AuthguardService } from '../shared/services/authguard.service';


const tripRoutes: Routes = [
  {
    path: 'admin',
    component: HomeComponent,
    canActivate: [AuthguardService],
    data: {
      expectedRole: 'ROLE_ADMIN'
    },
    children: [
      {
        path: 'tripitinerary',
        component: TripDetailsComponent,
        canActivate: [AuthguardService],
        data: {
          expectedRole: 'ROLE_ADMIN'
        }
      },
      {
        path: 'triptrack',
        component: LiveTrackingComponent,
        canActivate: [AuthguardService],
        data: {
          expectedRole: 'ROLE_ADMIN'
        }
      },
      {
        path: 'orderdetail',
        component: OrderDetailsComponent,
        canActivate: [AuthguardService],
        data: {
          expectedRole: 'ROLE_ADMIN'
        }
      },
      {
        path: 'vehicledetial',
        component: VehicleDetailsComponent,
        canActivate: [AuthguardService],
        data: {
          expectedRole: 'ROLE_ADMIN'
        }
      }
    ]
  },

];



@NgModule({
  imports: [RouterModule.forChild(tripRoutes)],
  exports: [RouterModule],
})
export class TripManagementRoutingModule { }
