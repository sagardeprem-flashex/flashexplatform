import { Component, OnInit } from '@angular/core';
import { TripItineraryService } from '../../services/trip-itinerary.service';
import { MatDialog } from '@angular/material';
import { TripPlanningPropertiesComponent } from '../trip-planning-properties/trip-planning-properties.component';
import { ITripProperties } from '../../interfaces/trip-planning-properties';
import { IItinerary } from '../../interfaces/trip-itinerary';
import { TripSummaryService } from '../../services/trip-summary.service';
import { Itripsummary } from '../../interfaces/trip-summary';
import { MatSlideToggleChange } from '@angular/material';

@Component({
  selector: 'app-trip-details',
  templateUrl: './trip-details.component.html',
  styleUrls: ['./trip-details.component.css']
})
export class TripDetailsComponent implements OnInit {

  public timeWindowDeliveryTrips = [];
  public vrpWithCCTrips = [];
  public vrpWithDVTrips = [];
  public vrpWithCCTripsUsingBing = [];
  public vrpWithDVTripsUsingBing = [];
  public timeWindowDeliveryTripsUsingBing = [];
  public otherTrips = [];
  public dataSource;
  public selectedAlgo;
  public orders;
  public userName;
  public tripsDate = new Date().toDateString();
  public properties: ITripProperties;
  public algorithms = ['Vrp with Capacity Constraint', 'Vrp with Dropping Visit', 'Vrp with Time Window Delivery'];
  public Bingalgorithm = ['Vrp with Capacity Constraint using Bing',
    'Vrp with Dropping Visit using Bing',
    'Vrp with Time Window Delivery using Bing'
  ];
  Bing = false;
  Default;

  multi1 : any[];
  multi2 : any[];

  summary: Itripsummary;
  public distanceCover = [];
  public totalTime = [];
  public totalExpense = [];
  public nTrips = [];
  public algorithm = [];

  view: any[] = [700, 400];

  // options
  showXAxis = true;
  showYAxis = true;
  gradient = false;
  showLegend = true;
  showXAxisLabel = true;
  showYAxisLabel = true;
  yAxisLabel1 = 'units/day';
  xAxisLabel = 'Algorithm';


  colorScheme = {
    domain: ['#5AA454', '#A10A28', '#C7B42C'] // , '#AAAAAA', '#009688', '#69F0AE'
  };

  constructor(private tripService: TripItineraryService, private tripsummary: TripSummaryService, private dialog: MatDialog) { }


  ngOnInit() {
    this.properties = this.tripService.planningProperties;
    // console.log('Trip planning properties inside trip-details component-----> ');
    // console.log(this.properties);
    this.tripService.behaviourSubject.subscribe(data => {
      this.dataSource = data;

      // console.log(data);

      data.forEach(d => {
        // console.log('Algos list ----------> ', d.algorithm);
        if (d.algorithm === 'Vrp with Time Window Delivery') {
          this.timeWindowDeliveryTrips.unshift(d);
        } else
          if (d.algorithm === 'Vrp with Capacity Constraint') {
            this.vrpWithCCTrips.unshift(d);
          } else
            if (d.algorithm === 'Vrp with Dropping Visit') {
              this.vrpWithDVTrips.unshift(d);
            } else
              if (d.algorithm === 'Vrp with Capacity Constraint using Bing') {
                this.vrpWithCCTripsUsingBing.unshift(d);
              } else
                if (d.algorithm === 'Vrp with Dropping Visit using Bing') {
                  this.vrpWithDVTripsUsingBing.unshift(d);
                } else
                  if (d.algorithm === 'Vrp with Time Window Delivery using Bing') {
                    this.timeWindowDeliveryTripsUsingBing.unshift(d);
                  } else {
                    this.otherTrips.unshift(d);
                  }
      });

    });

    this.tripsummary.behaviourSubject.subscribe(data => {
      // console.log("Fetching summary ------->", data);

      data.forEach((d) => {
        // console.log("In loop ---->", d);
        d.distanceSummary.forEach(summary => {
          this.distanceCover.push(summary);
        });

        d.timeSummary.forEach(summary => {
          this.totalTime.push(summary);
        });

        d.costSummary.forEach(summary => {
          this.totalExpense.push(summary);
        });

        d.ntrips.forEach(summary => {
          this.nTrips.push(summary);
        });

        d.algorithms.forEach(summary => {
          this.algorithm.push(summary);
        });


        this.multi1 = [
        {
          name: 'Distance',
          series: [
            {
              name: this.algorithm[2],
              value: this.distanceCover[2]
            },
            {
              name: this.algorithm[0],
              value: this.distanceCover[0]
            },
            {
              name: this.algorithm[4],
              value: this.distanceCover[4]
            }
          ]
        },
      
        {
          name: 'Time',
          series: [
            {
              name: this.algorithm[2],
              value: this.totalTime[2]
            },
            {
              name: this.algorithm[0],
              value: this.totalTime[0]
            },
            {
              name: this.algorithm[4],
              value: this.totalTime[4]
            }
          ]
        },
        {
          name: 'Trips',
          series: [
            {
              name: this.algorithm[2],
              value: this.nTrips[2]
            },
            {
              name: this.algorithm[0],
              value: this.nTrips[0]
            },
            {
              name: this.algorithm[4],
              value: this.nTrips[4]
            }
          ]
        },
        {
          name: 'Fuel Cost',
          series: [
            {
              name: this.algorithm[2],
              value: this.totalExpense[2]
            },
            {
              name: this.algorithm[0],
              value: this.totalExpense[0]
            },
            {
              name: this.algorithm[4],
              value: this.totalExpense[4]
            }
          ]
        }
      ];

      this.multi2 = [
        {
          name: 'Distance',
          series: [
            {
              name: this.algorithm[3],
              value: this.distanceCover[3]
            },
            {
              name: this.algorithm[1],
              value: this.distanceCover[1]
            },
            {
              name: this.algorithm[5],
              value: this.distanceCover[5]
            }
          ]
        },
      
        {
          name: 'Time',
          series: [
            {
              name: this.algorithm[3],
              value: this.totalTime[3]
            },
            {
              name: this.algorithm[1],
              value: this.totalTime[1]
            },
            {
              name: this.algorithm[5],
              value: this.totalTime[5]
            }
          ]
        },
        {
          name: 'Trips',
          series: [
            {
              name: this.algorithm[3],
              value: this.nTrips[3]
            },
            {
              name: this.algorithm[1],
              value: this.nTrips[1]
            },
            {
              name: this.algorithm[5],
              value: this.nTrips[5]
            }
          ]
        },
        {
          name: 'Fuel Cost',
          series: [
            {
              name: this.algorithm[3],
              value: this.totalExpense[3]
            },
            {
              name: this.algorithm[1],
              value: this.totalExpense[1]
            },
            {
              name: this.algorithm[5],
              value: this.totalExpense[5]
            }
          ]
        }
      ];
      
      });
    });

  }

  Option1(){
    let temp = this.Bing;
    this.Bing =!temp;
  };

  Option2(){
    alert('service not avaliable');
  };

  Option3(){
    let temp = this.Bing;
    this.Bing =!temp;
  };

  // public toggle(event: MatSlideToggleChange) {
  //   console.log('toggle', event.checked);
  //   this.Bing = event.checked;
  // }

  onSelect(event) {
    console.log(event);
  }

  changeAlgo(algo: string) {
    this.selectedAlgo = algo;
    this.tripService.selectedAlgo = this.selectedAlgo;
    this.tripService.planningProperties.algorithmSelected = algo;
    this.tripService.planningProperties.lastUpdated = new Date();
    this.tripService.updateOptimizationProperties(this.tripService.selectedAlgo);
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
