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
          MatTableModule,
          MatMenuModule,
          MatPaginatorModule} from '@angular/material';
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
  MatMenuModule,
  MatTableModule,
  MatPaginatorModule
];

@NgModule({
  imports: [MaterialComponents
  ],
  exports: [MaterialComponents
  ]
})
export class MaterialModule { }
