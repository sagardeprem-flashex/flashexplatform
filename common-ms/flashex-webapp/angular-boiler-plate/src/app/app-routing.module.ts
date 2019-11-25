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
      path: '', redirectTo: '/in', pathMatch: 'full'
    },
    {
      path: 'in', component: LandingPageComponent, pathMatch: 'full'
    },
    {
      path: 'trips-management',
      loadChildren: './trip-management/trip-management-routing.module#TripManagementRoutingModule',
    },
    {
      path: 'delivery-executive',
      loadChildren: './delivery-executive/delivery-executive-routing.module#DeliveryExecutiveRoutingModule',
    },
    // {
    //   path: '**', component: LandingPageComponent, pathMatch: 'full'
    // }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
