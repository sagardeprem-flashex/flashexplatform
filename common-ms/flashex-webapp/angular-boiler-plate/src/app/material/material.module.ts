import { NgModule } from '@angular/core';
import { MatToolbarModule} from '@angular/material/toolbar';
import {MatButtonModule} from '@angular/material/button';

const MaterialComponents = [
  MatToolbarModule,
  MatButtonModule
];

@NgModule({
  imports: [ MatToolbarModule, MatButtonModule ],
  exports: [   MatToolbarModule, MatButtonModule ]
})
export class MaterialModule { }
