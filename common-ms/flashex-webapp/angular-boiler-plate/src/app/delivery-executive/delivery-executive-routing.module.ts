import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { NavigationComponent } from './components/navigation/navigation.component';
import { OrderDeliveryListComponent } from './components/order-delivery-list/order-delivery-list.component';
import { TripsComponent } from './components/trips/trips.component';
const deliveryRoutes: Routes = [
  {
    path: 'navigation', component: NavigationComponent
  },
  {
    path: 'order', component: OrderDeliveryListComponent
  },
  {
    path: 'trip', component: TripsComponent
  },
];
@NgModule({
  imports: [RouterModule.forChild(deliveryRoutes)],
  exports: [RouterModule],
})
export class DeliveryExecutiveRoutingModule { }
