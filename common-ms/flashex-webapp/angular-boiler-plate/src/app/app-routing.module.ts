import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AppComponent } from './app.component';
import { LandingPageComponent } from './landing-page/landing-page.component';

const routes: Routes = [
    {
      path: 'shared',
      loadChildren: './shared/shared-routing.module#SharedRoutingModule',
    },
    {
      path: '', redirectTo: '/landing', pathMatch: 'full'
    },
    {
      path: 'landing', component: LandingPageComponent
    },
    {
      path: 'trips-managemet',
      loadChildren: './trip-management/trip-management-routing.module#TripManagementRoutingModule',
    },
    {
      path: 'delivery-executive',
      loadChildren: './delivery-executive/delivery-executive-routing.module#DeliveryExecutiveRoutingModule',
    },
];

@NgModule({
  imports: [RouterModule.forRoot(routes, {useHash: true})],
  exports: [RouterModule]
})
export class AppRoutingModule { }
