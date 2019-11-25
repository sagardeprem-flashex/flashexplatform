import { Component, OnInit } from '@angular/core';
import { TripItineraryService } from '../../services/trip-itinerary.service';
import { MatDialog } from '@angular/material';
import { TripPlanningPropertiesComponent } from '../trip-planning-properties/trip-planning-properties.component';
import { ITripProperties } from '../../interfaces/trip-planning-properties';

@Component({
  selector: 'app-trip-details',
  templateUrl: './trip-details.component.html',
  styleUrls: ['./trip-details.component.css']
})
export class TripDetailsComponent implements OnInit {

  public dataSource;
  public orders;
  public selectedOptimization;
  public userName;
  public properties: ITripProperties;
  constructor(private tripService: TripItineraryService, private dialog: MatDialog) { }

  ngOnInit() {
    this.properties = this.tripService.planningProperties;
    console.log('Trip planning properties inside trip-details component-----> ');
    console.log(this.properties);
    this.tripService.behaviourSubject.subscribe(data => {
      this.dataSource = data;

    });
    // console.log(this.dataSource);


  }

  openPropertiesDialog(): void {
    const dialogRef = this.dialog.open(TripPlanningPropertiesComponent, {
      width: '65%',
      data: {userName: this.userName, properties: this.properties}
    });

    dialogRef.afterClosed().subscribe(result => {
      this.properties = this.tripService.planningProperties;
      console.log(this.tripService.planningProperties, this.properties);
    });
  }

}
