import { Component, OnInit } from '@angular/core';
import { ChangeDetectorRef, OnDestroy } from '@angular/core';
import { MediaMatcher } from '@angular/cdk/layout';
import { TriplogService } from '../../services/triplog.service';
import { TokenStorageService } from '../../../shared/services/token-storage.service';
import { Router } from '@angular/router';
import { TripLog } from '../../interfaces/triplog';
import { Observable } from 'rxjs';

declare let L;
declare let tomtom: any;


@Component({
  selector: 'app-trips',
  templateUrl: './trips.component.html',
  styleUrls: ['./trips.component.css']
})
export class TripsComponent implements OnInit {

  // public lng;
  // public lat;
  // public dataSource;
  // public orders;
  // public location;
  // public markers = [];
  // public colors = [];
  // public zoom = 10;
  // public dir;
  // public origin: any;
  // public destination: any;
  // public renderOptions = {
  //   suppressMarkers: true,
  // };
  // public markerColor = [];
  // public color;
  // public storedColor = [];
  // public url;
  // public warehouse = {
  //   latitude: 12.95381,
  //   longitude: 77.6375593
  // };
  // public startTime;
  // public details;
  // public id;
  // public listofOrders;
  // public tripDetails;
  // public routes = [];
  // step = 0;

  mobileQuery: MediaQueryList;
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
  public details;
  public id;
  public listofOrders;
  public tripDetails;
  public tripStartTime = new Date();
  step = 0;
  public snav: any;

  constructor(changeDetectorRef: ChangeDetectorRef,
              media: MediaMatcher, private tripService: TriplogService,
              private tokenStorage: TokenStorageService, private router: Router) {
    this.mobileQuery = media.matchMedia('(max-width: 600px)');
    this.mobileQueryListener = () => changeDetectorRef.detectChanges();
    // tslint:disable-next-line: deprecation
    this.mobileQuery.addListener(this.mobileQueryListener);
  }

  // trip: TripLog = new TripLog();
  public trip: any;

  private mobileQueryListener: () => void;
  ngOnInit() {
    setTimeout(() => {
      const map = tomtom.L.map('map', {
        key: 'bvlnbSj7Eu5i41bgOFAlfWPZEuPkDcug',
        basePath: '/assets/sdk',
        center: [52.360306, 4.876935],
        zoom: 15
      });
    }, 500);

    // tslint:disable-next-line: deprecation
    this.mobileQuery.removeListener(this.mobileQueryListener);
    this.tripService.behaviourSubject.subscribe(data => {
      this.dataSource = data;
      // console.log('mm', this.dataSource);
      this.trips(0);
    });
  }
  trips(value) {
    // console.log('g', value);
    // console.log(this.dataSource);

    this.details = this.dataSource[value];
    // console.log('ff', this.details);
    if (this.details) {
      // this.details = this.details.orders[value].deliveryAddress;
      this.tripDetails = this.details;
      this.listofOrders = this.details.packetLogs;
      // console.log('hh', this.tripDetails);
    }

  }

   // get trip log by its id from backend
   getTripLogById(id: string) {
    this.tripService.getTripLog(id).subscribe(
      data => {
        this.tripLogById = data;

      }
    );
  }
  // update trip start time for particular trip with its id being fetched from UI
  updateTripStart(tripId) {
    const data = new TripLog();
    data.tripStart = new Date();
    // tslint:disable-next-line: no-shadowed-variable
    this.tripService.updateTripLog(tripId, data).subscribe( data => {
      this.tripLog = data;
    });
  }
  // update trip end time for particular trip with its id being fetched from UI
  // updateTripEnd(tripId) {
  //   this.trip.tripEnd = new Date();
  //   this.tripService.updateTripLog(tripId, this.trip).subscribe(data => {
  //     this.tripLog = data;
  //   });
  // }

  // update packet status of particular packet id inside a particular trip itinerary
  updatePacketLog(tripId, tripPacketId) {
    if ( this.trip && this.trip.packetLogs && this.trip.packetLogs.packetStatus) {
      this.trip.packetLogs = [{ packetStatus: 'Delhivery'}];
    } else {
      /* tslint:disable:no-string-literal */
      this.trip['packetLogs'] = [{packetStatus : 'Delivered'}];
    }
    this.tripService.updatePacketLog(tripId, this.trip, tripPacketId).subscribe(
      data => {
        this.tripLog = data;
      }
    );
  }

  // getRandomColor() {
  //   this.dataSource.forEach((element, i) => {
  //     const color = Math.floor(0x1000000 * Math.random()).toString(16);
  //     const generatedColor = '#' + ('000000' + color).slice(-6);
  //     this.colors.push(generatedColor);

  //   });
  // }
  logout() {
    this.tokenStorage.signOut();
    this.router.navigate(['/auth/login']);
  }
}





