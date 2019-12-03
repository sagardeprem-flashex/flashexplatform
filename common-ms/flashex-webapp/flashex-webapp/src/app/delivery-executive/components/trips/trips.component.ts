import { Component, OnInit } from '@angular/core';
import { ChangeDetectorRef, OnDestroy } from '@angular/core';
import { MediaMatcher } from '@angular/cdk/layout';
import { TripService } from 'src/app/trip-management/services/trip.service';
import { Router } from '@angular/router';
import { TokenStorageService } from 'src/app/shared/services/token-storage.service';

@Component({
  selector: 'app-trips',
  templateUrl: './trips.component.html',
  styleUrls: ['./trips.component.css']
})
export class TripsComponent implements OnInit {

  mobileQuery: MediaQueryList;
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
  public routes = [];
  public startTime;
  public details;
  public id;
  public listofOrders;
  public tripDetails;

  constructor(changeDetectorRef: ChangeDetectorRef, media: MediaMatcher,
              private tripService: TripService, private router: Router,
              private tokenStorage: TokenStorageService) {
    this.mobileQuery = media.matchMedia('(max-width: 600px)');
    this.mobileQueryListener = () => changeDetectorRef.detectChanges();
    // tslint:disable-next-line: deprecation
    this.mobileQuery.addListener(this.mobileQueryListener);
  }

  private mobileQueryListener: () => void;
  ngOnInit() {
    // tslint:disable-next-line: deprecation
    this.mobileQuery.removeListener(this.mobileQueryListener);
    this.tripService.behaviourSubject.subscribe(data => {
      this.dataSource = data;
      // console.log('mm', this.dataSource);
      this.trip(0);
    });
  }
  trip(value) {
    // console.log('g', value);
    // console.log(this.dataSource)

    this.details = this.dataSource[value];
    // console.log('ff', this.details);
    if (this.details) {
      // this.details = this.details.orders[value].deliveryAddress;
      this.tripDetails = this.details;
      this.listofOrders = this.details.orders;
      // console.log('hh', this.tripDetails);
    }

  }
  logout() {
    this.tokenStorage.signOut();
    this.router.navigate(['/auth/login']);
  }
}





