import { NgModule } from '@angular/core';
import { MatToolbarModule} from '@angular/material/toolbar';
import {MatButtonModule} from '@angular/material/button';
import { MatSidenavModule, MatExpansionModule} from '@angular/material';

const MaterialComponents = [
  MatToolbarModule,
  MatButtonModule,
  MatSidenavModule,
  MatExpansionModule
];

@NgModule({
  imports: [ MatToolbarModule, MatButtonModule, MatSidenavModule, MatExpansionModule ],
  exports: [   MatToolbarModule, MatButtonModule, MatSidenavModule, MatExpansionModule ]
})
export class MaterialModule { }
