import { NgModule } from '@angular/core';
import { MatToolbarModule} from '@angular/material/toolbar';
import {MatButtonModule} from '@angular/material/button';
import {  MatSidenavModule,
          MatProgressBarModule,
          MatListModule,
          MatExpansionModule,
          MatButtonToggleModule,
          MatDialogModule,
          MatFormFieldModule,
          MatInputModule,
          MatTabsModule,
          MatMenuModule} from '@angular/material';
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
  FlexLayoutModule,
  MatListModule,
  MatMenuModule
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
              FlexLayoutModule,
              MatListModule,
<<<<<<< HEAD
              MatTableModule
=======
              MatMenuModule
>>>>>>> c37b0c061a397da694a50ebf60583250eb664a25
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
              FlexLayoutModule,
              MatListModule,
<<<<<<< HEAD
              MatTableModule
=======
              MatMenuModule
>>>>>>> c37b0c061a397da694a50ebf60583250eb664a25
            ]
})
export class MaterialModule { }
