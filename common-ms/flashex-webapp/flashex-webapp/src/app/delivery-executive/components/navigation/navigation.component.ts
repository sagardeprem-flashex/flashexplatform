import { Component, OnInit, Inject } from '@angular/core';
import {MatDialog, MatDialogRef, MAT_DIALOG_DATA} from '@angular/material/dialog';
import { TripLog } from '../../../trip-management/interfaces/triplog';

declare let L;
declare let tomtom: any;

@Component({
  selector: 'app-navigation',
  templateUrl: './navigation.component.html',
  styleUrls: ['./navigation.component.css']
})
export class NavigationComponent implements OnInit {

  public tomtom;
  constructor( public dialogRef: MatDialogRef<NavigationComponent>,
               @Inject(MAT_DIALOG_DATA) public data: any) {
                 this.tomtom = data;
                }

  ngOnInit() {
  }

  onNoClick(): void {
    this.dialogRef.close();
  }


}
