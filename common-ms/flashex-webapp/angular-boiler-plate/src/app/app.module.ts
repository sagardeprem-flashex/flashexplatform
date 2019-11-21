import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MaterialModule } from './material/material.module';
import { SharedModule } from './shared/shared.module';
import { LandingPageComponent } from './landing-page/landing-page.component';
import { TripManagementModule } from './trip-management/trip-management.module';
import { DeliveryExecutiveModule } from './delivery-executive/delivery-executive.module';


@NgModule({
  declarations: [
    AppComponent,
    LandingPageComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    SharedModule,
    BrowserAnimationsModule,
    MaterialModule,
    TripManagementModule,
    DeliveryExecutiveModule
    ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
