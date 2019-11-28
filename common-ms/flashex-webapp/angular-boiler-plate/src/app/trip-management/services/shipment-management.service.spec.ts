import { TestBed } from '@angular/core/testing';

import { ShipmentManagementService } from './shipment-management.service';

describe('ShipmentManagementService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: ShipmentManagementService = TestBed.get(ShipmentManagementService);
    expect(service).toBeTruthy();
  });
});
