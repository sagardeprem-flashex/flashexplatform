import { Component, OnInit } from '@angular/core';
import {ChangeDetectorRef, OnDestroy} from '@angular/core';
import {MediaMatcher} from '@angular/cdk/layout';

@Component({
  selector: 'app-trips',
  templateUrl: './trips.component.html',
  styleUrls: ['./trips.component.css']
})
export class TripsComponent implements OnInit {

  mobileQuery: MediaQueryList;

  fillerNav = Array.from({length: 5}, (_, i) => `Trip  ${i + 1} Detail`);

  fillerContent = Array.from({length: 10}, () =>
      `Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut`);

      private mobileQueryListener: () => void;



      constructor(changeDetectorRef: ChangeDetectorRef, media: MediaMatcher) {
        this.mobileQuery = media.matchMedia('(max-width: 600px)');
        this.mobileQueryListener = () => changeDetectorRef.detectChanges();
        this.mobileQuery.addListener(this.mobileQueryListener);
      }

  ngOnInit() {
    this.mobileQuery.removeListener(this.mobileQueryListener);
  }

}
