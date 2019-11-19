import { NgModule } from '@angular/core';
import { MatToolbarModule} from '@angular/material/toolbar';
import {MatButtonModule} from '@angular/material/button';
import { MatSidenavModule} from '@angular/material';

const MaterialComponents = [
  MatToolbarModule,
  MatButtonModule,
  MatSidenavModule
];

@NgModule({
  imports: [ MatToolbarModule, MatButtonModule, MatSidenavModule ],
  exports: [   MatToolbarModule, MatButtonModule, MatSidenavModule ]
})
export class MaterialModule { }
