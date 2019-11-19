import { TestBed } from '@angular/core/testing';

import { TripItineraryService } from './trip-itinerary.service';

describe('TripItineraryService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: TripItineraryService = TestBed.get(TripItineraryService);
    expect(service).toBeTruthy();
  });
});
