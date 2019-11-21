import { NgModule } from '@angular/core';
import { MatToolbarModule} from '@angular/material/toolbar';
import {MatButtonModule} from '@angular/material/button';
import {  MatSidenavModule,
          MatProgressBarModule,
          MatExpansionModule,
          MatButtonToggleModule,
          MatDialogModule,
          MatFormFieldModule,
          MatInputModule,
          MatTabsModule} from '@angular/material';
import { MatCardModule } from '@angular/material';
import { MatStepperModule } from '@angular/material';
import { MatIconModule } from '@angular/material';
import { FlexLayoutModule } from '@angular/flex-layout';

const MaterialComponents = [
  MatToolbarModule,
  MatButtonModule,
  MatSidenavModule,
  MatCardModule,
  MatStepperModule,
  MatIconModule,
  MatProgressBarModule,
  MatExpansionModule,
  MatButtonToggleModule,
  MatDialogModule,
  MatFormFieldModule,
  MatInputModule,
  MatTabsModule,
  FlexLayoutModule
];

@NgModule({
  imports: [  MatToolbarModule,
              MatButtonModule,
              MatSidenavModule,
              MatCardModule,
              MatStepperModule,
              MatIconModule,
              MatProgressBarModule,
              MatExpansionModule,
              MatButtonToggleModule,
              MatDialogModule,
              MatFormFieldModule,
              MatInputModule,
              MatTabsModule,
              FlexLayoutModule
            ],
  exports: [  MatToolbarModule,
              MatButtonModule,
              MatSidenavModule,
              MatCardModule,
              MatStepperModule,
              MatIconModule,
              MatProgressBarModule,
              MatExpansionModule,
              MatButtonToggleModule,
              MatDialogModule,
              MatFormFieldModule,
              MatInputModule,
              MatTabsModule,
              FlexLayoutModule
            ]
})
export class MaterialModule { }
