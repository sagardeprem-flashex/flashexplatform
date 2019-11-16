import { NgModule } from '@angular/core';
import { NavbarComponent } from './components/navbar/navbar.component';
import { MaterialModule } from '../material/material.module';
import { CommonModule } from '@angular/common';
import { SharedRoutingModule } from './shared-routing.module';
import { LoginComponent } from './components/login/login.component';


@NgModule({
  declarations: [NavbarComponent, LoginComponent],
  imports: [
    CommonModule,
    MaterialModule,
    SharedRoutingModule,
  ],
  exports: [ NavbarComponent, LoginComponent]
})
export  class SharedModule { }
