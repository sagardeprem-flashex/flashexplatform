import { Component, OnInit } from '@angular/core';

declare let L;
declare let tomtom: any;

@Component({
  selector: 'app-live-tracking',
  templateUrl: './live-tracking.component.html',
  styleUrls: ['./live-tracking.component.css']
})
export class LiveTrackingComponent implements OnInit {

  constructor() { }

  var1 = [12.933744, 77.6128323];
  var2 = [12.9577129, 77.6764937];
  var3 = [13.1986348, 77.7044041];
  list = [this.var1, this.var2, this.var3];
  location = ['Stackroute', 'Marathalli', 'Airport'];

  ngOnInit() {
    const map = tomtom.L.map('map', {

      key: 'bvlnbSj7Eu5i41bgOFAlfWPZEuPkDcug',

      basePath: '/assets/sdk',

      center: [12.9538477, 77.3507303],

      zoom: 11,
    });
    for (let i = 0; i < this.list.length - 1; i++) {
      const marker: any = tomtom.L.marker(this.list[i], {

        // icon: tomtom.L.icon({
        //   iconUrl: '/assets/images/logo2.png',
        //   iconSize: [50, 75],
        //   iconAnchor: [17, 70],
        //   popupAnchor: [12, -80]
        //  })

      }).addTo(map);

      marker.bindPopup(this.location[i]).openPopup();

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

  }
}

