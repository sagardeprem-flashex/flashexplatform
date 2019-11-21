import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TripPlanningPropertiesComponent } from './trip-planning-properties.component';

describe('TripPlanningPropertiesComponent', () => {
  let component: TripPlanningPropertiesComponent;
  let fixture: ComponentFixture<TripPlanningPropertiesComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TripPlanningPropertiesComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TripPlanningPropertiesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
