import { Component, OnInit } from '@angular/core';
import { TripItineraryService } from '../../services/trip-itinerary.service';


@Component({
  selector: 'app-live-tracking',
  templateUrl: './live-tracking.component.html',
  styleUrls: ['./live-tracking.component.css']
})
export class LiveTrackingComponent implements OnInit {
  public lng;
  public lat;
  public dataSource;
  public orders;
  public location;
  public markers = [];
  public zoom = 10;

  constructor(private tripService: TripItineraryService) { }


  ngOnInit() {
    this.tripService.behaviourSubject.subscribe(data => {
      this.dataSource = data;
      // tslint:disable-next-line: prefer-for-of
      for (let i = 0; i < data.length; i++) {
        const location = this.dataSource[i];
        if (location && location.orders) {
          this.markers.push(location.orders);
          this.lat = location.orders[i].deliveryLocation.lat;
          this.lng = location.orders[i].deliveryLocation.lng;
        }
      }
      this.markers = [].concat.apply([], this.markers);
    });
  }

  trip(value) {
    const location = this.dataSource[value];
    if (location && location.orders) {
      this.markers = location.orders;
      this.lat = location.orders[0].deliveryLocation.lat;
      this.lng = location.orders[0].deliveryLocation.lng;
      this.zoom = 12;
    }
  }
}

