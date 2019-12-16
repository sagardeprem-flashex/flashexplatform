import { Component, OnInit, Sanitizer } from '@angular/core';
import { ChangeDetectorRef, OnDestroy } from '@angular/core';
import { MediaMatcher } from '@angular/cdk/layout';
import { TokenStorageService } from '../../../shared/services/token-storage.service';
import { Router } from '@angular/router';
import * as moment from 'moment';
import { TriplogService } from '../../../trip-management/services/triplog.service';
import { ITripLog, TripLog } from '../../../trip-management/interfaces/triplog';
import { Observable } from 'rxjs';
import { MatSnackBar } from '@angular/material/snack-bar';

declare let L;
declare let tomtom: any;


@Component({
  selector: 'app-trips',
  templateUrl: './trips.component.html',
  styleUrls: ['./trips.component.css']
})
export class TripsComponent implements OnInit {

  mobileQuery: MediaQueryList;
  public dataSource = [];
  public details;
  public listofOrders;
  public tripDetails;
  step = 0;
  public tripLog: any;
  public tripLogById;
  triplogss: Observable<ITripLog[]>;
  public trip: any;
  public authority;
  public role;
  public userName;
  public scheduledDate = new Date();
  public intialData;

  constructor(changeDetectorRef: ChangeDetectorRef, media: MediaMatcher,
              private tripService: TriplogService, private tokenStorage: TokenStorageService,
              private router: Router) {
    this.mobileQuery = media.matchMedia('(max-width: 600px)');
    this.mobileQueryListener = () => changeDetectorRef.detectChanges();
    // tslint:disable-next-line: deprecation
    this.mobileQuery.addListener(this.mobileQueryListener);
  }

  private mobileQueryListener: () => void;
  ngOnInit() {
    this.authority = this.tokenStorage.getAuthorities();
    if (this.authority[0] === 'ROLE_ADMIN') {
      this.role = 'Manager';

    } else if (this.authority[0] === 'ROLE_USER') {
      this.role = 'Delivery Executive';

    }

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

    this.userName = this.tokenStorage.getUsername();
    this.tripService.behaviourSubject.subscribe(data => {
      if (data && data.length > 0) {
        if (this.userName === 'anurag123') {
          this.dataSource[0] = data[0];
        } else if (this.userName === 'anup123') {
          this.dataSource[0] = data[1];

        } else if (this.userName === 'parth123') {
          this.dataSource[0] = data[2];

        }
      }
      this.trips(0);
    });
  }
  trips(value) {
    // console.log('vali', value);
    this.details = this.dataSource[value];
    // console.log('deta', this.details);
    if (this.details) {
      this.tripDetails = this.details;
      this.listofOrders = this.details.packetLogs;
      // console.log('lis', this.listofOrders);
    }
    // toggle();

  }
  getTripLogById(id: string) {
    this.tripService.getTripLog(id).subscribe(
      data => {
        this.tripLogById = data;

      }
    );
  }
  // update trip start time for particular trip with its id being fetched from UI
  updateTripStart(tripId) {
    this.trip = new TripLog();
    this.trip.tripStart = new Date();
    this.tripService.updateTripLog(tripId, this.trip).subscribe(data => {
      this.tripLog = data;
    });
  }
  // update trip end time for particular trip with its id being fetched from UI
  updateTripEnd(tripId) {
    this.trip.tripEnd = new Date();
    this.tripService.updateTripLog(tripId, this.trip).subscribe(data => {
      this.tripLog = data;
    });
  }

  // update packet status of particular packet id inside a particular trip itinerary
  updatePacketLog(tripId, tripPacketId) {
    // console.log('tr', tripId, ' pacl', tripPacketId);
    if (this.trip && this.trip.packetLogs && this.trip.packetLogs.packetStatus) {
      this.trip.packetLogs = [{ packetStatus: 'Delivered' }];
    } else {
      /* tslint:disable:no-string-literal */
      this.trip['packetLogs'] = [{ packetStatus: 'Delivered' }];
    }
    this.tripService.updatePacketLog(tripId, this.trip, tripPacketId).subscribe(
      data => {
        this.tripLog = data;
      }
    );
  }
  logout() {
    this.tokenStorage.signOut();
    this.router.navigate(['/auth/login']);
  }
}





