import { TestBed } from '@angular/core/testing';

import { TripItineraryService } from './trip-itinerary.service';
import { HttpClientTestingModule } from '@angular/common/http/testing';

describe('TripItineraryService', () => {
  beforeEach(() => TestBed.configureTestingModule({
    imports: [HttpClientTestingModule]
  }));

  it('should be created', () => {
    const service: TripItineraryService = TestBed.get(TripItineraryService);
    expect(service).toBeTruthy();
  });
});
