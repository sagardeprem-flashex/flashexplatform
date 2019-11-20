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
  public colors = [];
  public zoom = 10;
  public dir;
  public origin: any;
  public destination: any;
  public renderOptions;
  public markerColor = [];
  public color;
  public storedColor = [];
  public url;
  public routeColor = [];

  constructor(private tripService: TripItineraryService) { }
  public routes = [];

  ngOnInit() {
    this.tripService.behaviourSubject.subscribe(data => {
      this.dataSource = data;
      this.getRandomColor();
      this.url = 'http://labs.google.com/ridefinder/images/mm_20_gray.png';      // tslint:disable-next-line: prefer-for-of
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

  trip(value) {
    const location = this.dataSource[value];
    const rotColor = this.colors;

    if (location && location.orders) {
      // tslint:disable-next-line: prefer-for-of
      for (let k = 0; k < location.orders.length - 1; k++) {
        this.routeColor.push(rotColor[value]);
      }
      this.markers = location.orders;
      this.lat = location.orders[0].deliveryLocation.lat;
      this.lng = location.orders[0].deliveryLocation.lng;
      this.zoom = 12;

      for (let j = 0; j < location.orders.length - 1; j++) {
        this.origin = { lat: location.orders[j].deliveryLocation.lat, lng: location.orders[j].deliveryLocation.lng };
        this.destination = { lat: location.orders[j + 1].deliveryLocation.lat, lng: location.orders[j + 1].deliveryLocation.lng };
        this.routes.push({ origin: this.origin, dest: this.destination });
        this.renderOptions = {
          suppressMarkers: true,
          polylineOptions: { strokeColor: this.routeColor[j] }

        };
      }
      this.routeColor = [];
    }
  }
  getRandomColor() {
    this.dataSource.forEach((element, i) => {
      const color = Math.floor(0x1000000 * Math.random()).toString(16);
      const generatedColor = '#' + ('000000' + color).slice(-6);
      this.colors.push(generatedColor);

    });
  }
}


