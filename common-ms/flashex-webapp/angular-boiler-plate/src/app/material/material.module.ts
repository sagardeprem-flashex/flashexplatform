import { NgModule } from '@angular/core';
import { MatToolbarModule} from '@angular/material/toolbar';
import {MatButtonModule} from '@angular/material/button';
import { MatSidenavModule, MatFormFieldModule, MatDialogModule, MatInputModule} from '@angular/material';

const MaterialComponents = [
  MatToolbarModule,
  MatButtonModule,
  MatSidenavModule
];

@NgModule({
  imports: [
    MatToolbarModule,
    MatButtonModule,
    MatSidenavModule,
    MatFormFieldModule,
    MatDialogModule,
    MatInputModule
  ],
  exports: [
    MatToolbarModule,
    MatButtonModule,
    MatSidenavModule,
    MatFormFieldModule,
    MatDialogModule,
    MatInputModule
  ]
})
export class MaterialModule { }
