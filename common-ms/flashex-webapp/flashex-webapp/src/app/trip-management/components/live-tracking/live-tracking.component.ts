import { Component, OnInit } from '@angular/core';
import { TripItineraryService } from '../../services/trip-itinerary.service';

declare let tomtom: any;
@Component({
  selector: 'app-live-tracking',
  templateUrl: './live-tracking.component.html',
  styleUrls: ['./live-tracking.component.css']
})
export class LiveTrackingComponent implements OnInit {
  public dataSource;
  public orders;
  public location;
  public markers = [];
  public colors = [];
  public color;
  public marks = [];
  public addressLine = [];



  constructor(private tripService: TripItineraryService) { }

  ngOnInit() {
    this.tripService.behaviourSubject.subscribe(data => {
      this.dataSource = data;
      this.getRandomColor();
    });
    // dom should create map container and then only tomtom will load map
    // on refreshing page map container is being created after tomtom already called
    // so time out is provided to wait for dom to be created
    setTimeout(() => {
      const map = tomtom.L.map('map', {
        key: 'bvlnbSj7Eu5i41bgOFAlfWPZEuPkDcug',
        basePath: '/assets/sdk',
        center: [12.9538477, 77.3507303],
        zoom: 11,
      });
      // tslint:disable-next-line: prefer-for-of
      for (let i = 0; i < this.dataSource.length; i++) {
        const packets = this.dataSource[i].packets;
        // store delivery address and latitude and longitude to marks
        // tslint:disable-next-line: prefer-for-of
        for (let j = 0; j < packets.length; j++) {
          const deliveryAddress = packets[j].deliveryAddress;
          const mark = [deliveryAddress.latitude, deliveryAddress.longitude];
          const address = deliveryAddress.addressLine1;
          this.marks.push(mark);
          this.addressLine.push(address);
        }
      }
      // add marker to the map and attached delivery address to each marker
      // tslint:disable-next-line: prefer-for-of
      for (let i = 0; i < this.marks.length; i++) {
        const marker: any = tomtom.L.marker(this.marks[i], {
        }).addTo(map);
        marker.bindPopup(this.addressLine[i]).openPopup();

      }
      for (let i = 0; i < this.marks.length - 23; i++) {
        // store origin and destination for routes
        const routes = this.marks[i].join(',').concat(':').concat(this.marks[i + 1].join(','));
        tomtom.routing().locations(routes)
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


