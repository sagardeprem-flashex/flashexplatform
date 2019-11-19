import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { LiveTrackingComponent } from './live-tracking.component';
import { MaterialModule } from 'src/app/material/material.module';
import { AgmCoreModule, MapsAPILoader } from '@agm/core';
import { HttpClientModule } from '@angular/common/http';
import { TripItineraryService } from '../../services/trip-itinerary.service';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { HttpClientTestingModule} from '@angular/common/http/testing';


describe('LiveTrackingComponent', () => {
  let component: LiveTrackingComponent;
  let fixture: ComponentFixture<LiveTrackingComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [LiveTrackingComponent],
      imports: [MaterialModule,
        AgmCoreModule.forRoot({
          apiKey: 'AIzaSyDWuoodBo_sLP8B1_wWVDwkyGwaavc3UUY'
        }),
        HttpClientModule,
        BrowserAnimationsModule,
        HttpClientTestingModule
      ],
      providers: [TripItineraryService ]
    })
      .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(LiveTrackingComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
