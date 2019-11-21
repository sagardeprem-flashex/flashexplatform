import { NgModule } from '@angular/core';
import { NavbarComponent } from './components/navbar/navbar.component';
import { MaterialModule } from '../material/material.module';
import { CommonModule } from '@angular/common';
import { SharedRoutingModule } from './shared-routing.module';
import { LoginComponent } from './components/login/login.component';
import { AppRoutingModule } from '../app-routing.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { AuthGuard } from './auth/auth-guard';


@NgModule({
  declarations: [NavbarComponent, LoginComponent],
  imports: [
  CommonModule,
    MaterialModule,
    SharedRoutingModule,
    AppRoutingModule,
    SharedRoutingModule,
    FormsModule,
    ReactiveFormsModule
  ],
  providers: [AuthGuard],
  exports: [ NavbarComponent, LoginComponent]
})
export  class SharedModule { }
