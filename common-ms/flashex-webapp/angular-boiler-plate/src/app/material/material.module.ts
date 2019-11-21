import { NgModule } from '@angular/core';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatStepperModule } from '@angular/material';
import { MatButtonModule } from '@angular/material/button';
import { MatSidenavModule, MatFormFieldModule, MatDialogModule, MatInputModule, MatExpansionModule } from '@angular/material';

const MaterialComponents = [
  MatToolbarModule,
  MatButtonModule,
  MatSidenavModule,
  MatExpansionModule,
  MatStepperModule
];

@NgModule({
  imports: [
    MatToolbarModule,
    MatButtonModule,
    MatSidenavModule,
    MatFormFieldModule,
    MatDialogModule,
    MatInputModule,
    MatExpansionModule,
    MatStepperModule
  ],
  exports: [
    MatToolbarModule,
    MatButtonModule,
    MatSidenavModule,
    MatFormFieldModule,
    MatDialogModule,
    MatInputModule,
    MatExpansionModule,
    MatStepperModule
  ]
})
export class MaterialModule { }
