import { Component, OnInit } from '@angular/core';
import { ChangeDetectorRef, OnDestroy } from '@angular/core';
import { MediaMatcher } from '@angular/cdk/layout';
import { TripItineraryService } from '../../services/trip-itinerary.service';
import { TokenStorageService } from '../../../shared/services/token-storage.service';
import { Router } from '@angular/router';

declare let L;
declare let tomtom: any;


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
  public startTime;
  public details;
  public id;
  public listofOrders;
  public tripDetails;
  public routes = [];
  step = 0;

  constructor(changeDetectorRef: ChangeDetectorRef,
              media: MediaMatcher, private tripService: TripItineraryService,
              private tokenStorage: TokenStorageService, private router: Router) {
    this.mobileQuery = media.matchMedia('(max-width: 600px)');
    this.mobileQueryListener = () => changeDetectorRef.detectChanges();
    // tslint:disable-next-line: deprecation
    this.mobileQuery.addListener(this.mobileQueryListener);
  }

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





