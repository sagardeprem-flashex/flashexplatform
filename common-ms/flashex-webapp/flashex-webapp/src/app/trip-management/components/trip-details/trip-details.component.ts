import { Component, OnInit } from '@angular/core';
import { TripItineraryService } from '../../services/trip-itinerary.service';
import { MatDialog } from '@angular/material';
import { TripPlanningPropertiesComponent } from '../trip-planning-properties/trip-planning-properties.component';
import { ITripProperties } from '../../interfaces/trip-planning-properties';
import { IItinerary } from '../../interfaces/trip-itinerary';

@Component({
  selector: 'app-trip-details',
  templateUrl: './trip-details.component.html',
  styleUrls: ['./trip-details.component.css']
})
export class TripDetailsComponent implements OnInit {

  public timeWindowDeliveryTrips = [];
  public vrpWithCCTrips = [];
  public vrpWithDVTrips = [];
  public otherTrips = [];
  public dataSource;
  public selectedAlgo;
  public orders;
  public userName;
  public properties: ITripProperties;
  public algorithms = ['Time Window Delivery', 'VRP with Capacity constraint', 'VRP with Dropping Visit'];

  constructor(private tripService: TripItineraryService, private dialog: MatDialog) { }

  ngOnInit() {
    this.properties = this.tripService.planningProperties;
    // console.log('Trip planning properties inside trip-details component-----> ');
    // console.log(this.properties);
    this.tripService.behaviourSubject.subscribe(data => {
      this.dataSource = data;
      data.forEach(d => {
        if (d.algorithm === 'VrpWithTimeWindowDelivery') {
          this.timeWindowDeliveryTrips.unshift(d);
        } else
        if (d.algorithm === 'VrpWithCapacityConstraint') {
          this.vrpWithCCTrips.unshift(d);
        } else
        if (d.algorithm === 'VrpWithDroppingVisit') {
          this.vrpWithDVTrips.unshift(d);
        } else {
          this.otherTrips.unshift(d);
        }
      });

    });
    // console.log(this.dataSource);

  }

  changeAlgo(algo: string) {
    this.selectedAlgo = algo;
    this.tripService.selectedAlgo = this.selectedAlgo;
    // console.log(this.selectedAlgo);
  }

  // openPropertiesDialog(): void {
  //   const dialogRef = this.dialog.open(TripPlanningPropertiesComponent, {
  //     width: '65%',
  //     data: {userName: this.userName, properties: this.properties}
  //   });

  //   dialogRef.afterClosed().subscribe(result => {
  //     this.properties = this.tripService.planningProperties;
  //     // console.log(this.tripService.planningProperties, this.properties);
  //   });
  // }

}
