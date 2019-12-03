import { Component, OnInit } from '@angular/core';
import { TripService } from '../../services/trip.service';

declare let tomtom: any;
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
  var1 = [12.933744, 77.6128323];
  var2 = [12.9577129, 77.6764937];
  var3 = [13.1986348, 77.7044041];
  var4 = [77.7044041, 13.1986348];
  list = [this.var1, this.var2, this.var3];
  location1 = ['Stackroute', 'Marathalli', 'Airport'];

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
    setTimeout(() => {
      const map = tomtom.L.map('map', {
        key: 'bvlnbSj7Eu5i41bgOFAlfWPZEuPkDcug',
        basePath: '/assets/sdk',
        center: [12.9538477, 77.3507303],
        zoom: 11,
      });
      map.setView(this.var4);
      for (let i = 0; i < this.list.length - 1; i++) {
        const marker: any = tomtom.L.marker(this.list[i], {
        }).addTo(map);
        marker.bindPopup(this.location1[i]).openPopup();
        const store = this.list[i].join(',').concat(':').concat(this.list[i + 1].join(','));
        tomtom.routing()
          .locations(store)
          // tslint:disable-next-line: only-arrow-functions
          .go().then(function(routeJson) {
            const route = tomtom.L.geoJson(routeJson, {
              style: { color: 'red', opacity: 0.6, weight: 6 }
            }).addTo(map);
            map.fitBounds(route.getBounds(), { padding: [5, 5] });
          });
      }
    }, 500);
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


