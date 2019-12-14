import { TestBed } from '@angular/core/testing';

import { TripSummaryService } from './trip-summary.service';

describe('TripSummaryService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: TripSummaryService = TestBed.get(TripSummaryService);
    expect(service).toBeTruthy();
  });
});
