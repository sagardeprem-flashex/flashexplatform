import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { NavbarComponent } from './components/navbar/navbar.component';
import { LoginComponent } from './components/login/login.component';


const sharedRoutes: Routes = [
  {
    path: 'navbar', component: NavbarComponent
    },
  {
    path: 'login' , component: LoginComponent
  }
];

@NgModule({
  imports: [RouterModule.forChild(sharedRoutes)],
  exports: [RouterModule],
})
export class SharedRoutingModule { }
