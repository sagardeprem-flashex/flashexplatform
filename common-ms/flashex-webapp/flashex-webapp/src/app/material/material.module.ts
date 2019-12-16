import { NgModule } from '@angular/core';
import { MatToolbarModule } from '@angular/material/toolbar';
import {MatRippleModule, MatNativeDateModule} from '@angular/material/core';
import { MatButtonModule } from '@angular/material/button';
import {
  MatSidenavModule,
  MatProgressBarModule,
  MatListModule,
  MatExpansionModule,
  MatButtonToggleModule,
  MatDialogModule,
  MatFormFieldModule,
  MatInputModule,
  MatTabsModule,
  MatTableModule,
  MatMenuModule,
  MatPaginatorModule,
  MatSelectModule,
  MatProgressSpinnerModule,
  MatTooltipModule,
  MatSortModule,
  MatStepperModule,
  MatRadioModule,
  MatSlideToggleModule,
  MatSliderModule,
  MatIconModule,
  MatDatepickerModule,
  MatSnackBarModule
} from '@angular/material';
import { MatCardModule } from '@angular/material';
import { FlexLayoutModule } from '@angular/flex-layout';
import { MatPasswordStrengthModule } from '@angular-material-extensions/password-strength';
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
  MatMenuModule,
  MatTableModule,
  MatPaginatorModule,
  MatSelectModule,
  MatProgressSpinnerModule,
  MatTooltipModule,
  MatPasswordStrengthModule,
  MatSortModule,
  MatRippleModule,
  MatRadioModule,
  MatSlideToggleModule,
  MatSliderModule,
  MatRadioModule,
  MatIconModule,
  MatDatepickerModule,
  MatNativeDateModule,
  MatSnackBarModule
];
@NgModule({
  imports: [MaterialComponents
  ],
  exports: [MaterialComponents
  ]
})
export class MaterialModule { }
