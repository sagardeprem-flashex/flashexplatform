import { Component, OnInit, Inject } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';
import { ITripProperties } from '../../interfaces/trip-planning-properties';
import { FormBuilder, Validators } from '@angular/forms';
import { TripItineraryService } from '../../services/trip-itinerary.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-trip-planning-properties',
  templateUrl: './trip-planning-properties.component.html',
  styleUrls: ['./trip-planning-properties.component.css']
})
export class TripPlanningPropertiesComponent implements OnInit {

  constructor(private dialogRef: MatDialogRef<TripPlanningPropertiesComponent>,
              private fb: FormBuilder, private tripService: TripItineraryService,
              private router: Router) { }

  public algorithms = ['Time Window Delivery', 'VRP with Capacity constraint', 'VRP with Dropping Visit'];
  public addProperties = this.fb.group({
    algorithmSelected: ['', Validators.required],
    maxElementsForDistanceMatrix: [''],
    solverTimeLimit: [''],
    noOfDepot: [''],
    distancePenalty: [''],
    durationPenalty: ['']
  });

  ngOnInit() {
  }

  onSubmit() {
    console.log(`From trip planning component -->`);
    console.log(this.addProperties.value, this.addProperties.valid);
    this.tripService.planningProperties = this.addProperties.value;
    console.log(this.tripService.planningProperties);
    // this.router.navigate(['trips']);
    this.dialogRef.close();
  }

  onNoClick(): void {
    this.dialogRef.close();
  }

}
