import { NgModule } from '@angular/core';
import { MatToolbarModule} from '@angular/material/toolbar';
import {MatButtonModule} from '@angular/material/button';
import { MatSidenavModule, MatExpansionModule, MatStepperModule} from '@angular/material';

const MaterialComponents = [
  MatToolbarModule,
  MatButtonModule,
  MatSidenavModule,
  MatExpansionModule,
  MatStepperModule
];

@NgModule({
  imports: [ MatToolbarModule, MatButtonModule, MatSidenavModule, MatExpansionModule, MatStepperModule ],
  exports: [ MatToolbarModule, MatButtonModule, MatSidenavModule, MatExpansionModule, MatStepperModule ]
})
export class MaterialModule { }
