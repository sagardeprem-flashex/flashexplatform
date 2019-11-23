import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AppComponent } from './app.component';
import { LandingPageComponent } from './landing-page/landing-page.component';

const routes: Routes = [
    {
      path: 'shared',
      loadChildren: './shared/shared-routing.module#SharedRoutingModule',
    },
    // {
    //   path: '', redirectTo: '/landing', pathMatch: 'full'
    // },
    {
      path: '', component: LandingPageComponent, pathMatch: 'full'
    },
    {
      path: 'trips-managemet',
      loadChildren: './trip-management/trip-management-routing.module#TripManagementRoutingModule',
    },
    {
      path: 'delivery-executive',
      loadChildren: './delivery-executive/delivery-executive-routing.module#DeliveryExecutiveRoutingModule',
    },
    {
      path: '**', redirectTo: ''
    }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
