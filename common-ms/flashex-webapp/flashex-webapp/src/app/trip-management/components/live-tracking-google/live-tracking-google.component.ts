import { Component, OnInit } from '@angular/core';
import { TriplogService } from '../../services/triplog.service';
import { TripLog, ITripLog } from '../../interfaces/triplog';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-live-tracking-google',
  templateUrl: './live-tracking-google.component.html',
  styleUrls: ['./live-tracking-google.component.css']
})
export class LiveTrackingGoogleComponent implements OnInit {

  public lng;
  public lat;
  public dataSource;
  public orders;
  public tripLogById;
  public tripLog: any;

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
  public routes = [];

  displayedColumns: string[] = ['orderId', 'status'];
  public scheduledDate = new Date();
  triplogss: Observable<ITripLog[]>;
  public trip: any;
  constructor(private tripLogService: TriplogService) { }

  ngOnInit() {
    this.tripLogService.behaviourSubject.subscribe(data => {
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
  trips(value) {
    this.routes = [];
    const location = this.dataSource[value];

    if (location && location.orders) {
      this.markers = location.orders;
      this.lat = location.orders[2].deliveryLocation.lat;
      this.lng = location.orders[2].deliveryLocation.lng;
      this.zoom = 12;
      this.origin = { lat: this.warehouse.latitude, lng: this.warehouse.longitude };
      this.destination = { lat: location.orders[0].deliveryLocation.lat, lng: location.orders[0].deliveryLocation.lng };
      this.routes.push({ origin: this.origin, dest: this.destination });
      for (let j = 0; j < location.orders.length - 1; j++) {
        this.origin = { lat: location.orders[j].deliveryLocation.lat, lng: location.orders[j].deliveryLocation.lng };
        this.destination = { lat: location.orders[j + 1].deliveryLocation.lat, lng: location.orders[j + 1].deliveryLocation.lng };
        this.routes.push({ origin: this.origin, dest: this.destination });
      }
    }
  }
  getRandomColor() {
    this.dataSource.forEach((element, i) => {
      const color = Math.floor(0x1000000 * Math.random()).toString(16);
      const generatedColor = '#' + ('000000' + color).slice(-6);
      this.colors.push(generatedColor);

    });
  }
  // get trip log by its id from backend
  getTripLogById(id: string) {
    this.tripLogService.getTripLog(id).subscribe(
      data => {
        this.tripLogById = data;

      }
    );
  }
  // update trip start time for particular trip with its id being fetched from UI
  updateTripStart(tripId) {
    this.trip = new TripLog();
    this.trip.tripStart = new Date();
    this.tripLogService.updateTripLog(tripId, this.trip).subscribe(data => {
      this.tripLog = data;
    });
  }
  // update trip end time for particular trip with its id being fetched from UI
  updateTripEnd(tripId) {
    this.trip.tripEnd = new Date();
    this.tripLogService.updateTripLog(tripId, this.trip).subscribe(data => {
      this.tripLog = data;
    });
  }

  // update packet status of particular packet id inside a particular trip itinerary
  updatePacketLog(tripId, tripPacketId) {
    if ( this.trip && this.trip.packetLogs && this.trip.packetLogs.packetStatus) {
      this.trip.packetLogs = [{ packetStatus: 'Delhivery'}];
    } else {
      /* tslint:disable:no-string-literal */
      this.trip['packetLogs'] = [{packetStatus : 'Delivered'}];
    }
    this.tripLogService.updatePacketLog(tripId, this.trip, tripPacketId).subscribe(
      data => {
        this.tripLog = data;
      }
    );
  }
}
