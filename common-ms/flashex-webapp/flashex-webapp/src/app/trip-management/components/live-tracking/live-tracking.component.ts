import { Component, OnInit } from '@angular/core';
import { TripService } from '../../services/trip.service';

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
  public colors = [];
  public zoom = 10;
  public dir;
  public origin: any;
  public destination: any;
  public renderOptions = {
    suppressMarkers: true,
  };
  public markerColor = [];
  public color;
  public storedColor = [];
  public url;
  public warehouse = {
    latitude: 12.95381,
    longitude: 77.6375593
  };



  constructor(private tripService: TripService) { }
  public routes = [];

  ngOnInit() {
    this.tripService.behaviourSubject.subscribe(data => {
      this.dataSource = data;
      this.getRandomColor();
      this.url = '../../../../assets/images/warehouse.png';
      // tslint:disable-next-line: prefer-for-of
      for (let i = 0; i < data.length; i++) {
        const location = this.dataSource[i];

        // console.log(location.orders.length)
        if (location && location.orders) {
          const genColor = this.colors;
          // tslint:disable-next-line: prefer-for-of
          for (let k = 0; k < location.orders.length; k++) {
            this.markerColor.push(genColor[i]);
          }
          this.markers.push(location.orders);
          this.lat = location.orders[1].deliveryLocation.lat;
          this.lng = location.orders[1].deliveryLocation.lng;
        }
      }
      this.markers = [].concat.apply([], this.markers);
    });
  }
  // on click to each trip it will carry value and passed to this function
  trip(value) {
    this.routes = [];
    // store information of trip corresponding to value
    const location = this.dataSource[value];

    if (location && location.orders) {
      this.markers = location.orders;
      // fetch latitude and longitude from each trip to center the map
      this.lat = location.orders[2].deliveryLocation.lat;
      this.lng = location.orders[2].deliveryLocation.lng;
      this.zoom = 12;
      // store orgin and destination of each routes and pass to agm-direction
      this.origin = { lat: this.warehouse.latitude, lng: this.warehouse.longitude };
      this.destination = { lat: location.orders[0].deliveryLocation.lat, lng: location.orders[0].deliveryLocation.lng };
      this.routes.push({ origin: this.origin, dest: this.destination });
      // creates the array that will store all routes between each marker for a particular trip
      for (let j = 0; j < location.orders.length - 1; j++) {
        this.origin = { lat: location.orders[j].deliveryLocation.lat, lng: location.orders[j].deliveryLocation.lng };
        this.destination = { lat: location.orders[j + 1].deliveryLocation.lat, lng: location.orders[j + 1].deliveryLocation.lng };
        this.routes.push({ origin: this.origin, dest: this.destination });
      }
    }
  }

  // provide different color to each trips and corresponding markers
  getRandomColor() {
    this.dataSource.forEach((element, i) => {
      const color = Math.floor(0x1000000 * Math.random()).toString(16);
      const generatedColor = '#' + ('000000' + color).slice(-6);
      this.colors.push(generatedColor);

    });
  }
}


