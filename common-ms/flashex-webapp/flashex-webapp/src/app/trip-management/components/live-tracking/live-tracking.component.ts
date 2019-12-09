import { Component, OnInit } from '@angular/core';
import { TriplogService } from '../../services/triplog.service';
import { TripLog, ITripLog } from '../../interfaces/triplog';
import { Observable } from 'rxjs';
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
  public tripLog: any;
  public tripLogById;
  public tripStartTime = new Date();
  public centerMap;
  public markerIcon = ['../../../../assets/mapIcon/78753.svg',
    '../../../../assets/mapIcon/2.svg',
    '../../../../assets/mapIcon/3.svg',
    '../../../../assets/mapIcon/4.svg',
    '../../../../assets/mapIcon/5.svg',
    '../../../../assets/mapIcon/6.svg',
    '../../../../assets/mapIcon/7.svg',
    '../../../../assets/mapIcon/8.svg',
    '../../../../assets/mapIcon/9.svg',
    '../../../../assets/mapIcon/10.svg'
  ];

  constructor(private tripService: TriplogService) { }
  triplogss: Observable<ITripLog[]>;
  trip: TripLog = new TripLog();

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
        center: this.centerMap,
        zoom: 11,
      });
      // tslint:disable-next-line: prefer-for-of
      for (let i = 0; i < this.dataSource.length; i++) {
        this.marks = [];
        const packets = this.dataSource[i].packetLogs;
        // store delivery address and latitude and longitude to marks
        // tslint:disable-next-line: prefer-for-of
        for (let j = 0; j < packets.length; j++) {
          const deliveryAddress = packets[j].deliveryAddress;
          const mark = [deliveryAddress.latitude, deliveryAddress.longitude];
          this.centerMap = mark;
          const address = deliveryAddress.addressLine1;
          this.marks.push(mark);
          this.addressLine.push(address);
        }
        // add marker to the map and attached delivery address to each marker
        // tslint:disable-next-line: prefer-for-of
        for (let m = 0; m < this.marks.length; m++) {
          const marker: any = tomtom.L.marker(this.marks[m], {
            icon: tomtom.L.icon({
              iconUrl: this.markerIcon[i],
              iconSize: [40, 40],
              iconAnchor: [30, 30],
              popupAnchor: [0, -30]
            }),
          }).addTo(map);
          marker.bindPopup(this.addressLine[i]).openPopup();

        }
        for (let n = 0; n < this.marks.length - 1; n++) {
          // store origin and destination for routes
          let routes = [];
          routes = this.marks[n].join(',').concat(':').concat(this.marks[n + 1].join(','));
          tomtom.routing().locations(routes)
            // tslint:disable-next-line: only-arrow-functions
            .go().then(function(routeJson) {
              const route = tomtom.L.geoJson(routeJson, {
                style: { color: 'blue', opacity: 0.5, weight: 5 }
              }).addTo(map);
              map.fitBounds(route.getBounds(), { padding: [5, 5] });
            });

        }
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

  getTripLogById(id: string) {
    this.tripService.getTripLog(id).subscribe(
      data => {
        this.tripLogById = data;

      }
    );
  }
  updateTripStart(tripId) {
    this.trip = new TripLog();
    this.trip.tripStart = new Date();
    this.tripService.updateTripLog(tripId, this.trip).subscribe(data => {
      this.tripLog = data;
    });
  }
  updateTripEnd(tripId) {
    this.trip.tripEnd = new Date();
    this.tripService.updateTripLog(tripId, this.trip).subscribe(data => {
      this.tripLog = data;
    });
  }
}


