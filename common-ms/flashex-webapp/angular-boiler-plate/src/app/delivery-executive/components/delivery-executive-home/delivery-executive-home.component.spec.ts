import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DeliveryExecutiveHomeComponent } from './delivery-executive-home.component';

describe('DeliveryExecutiveHomeComponent', () => {
  let component: DeliveryExecutiveHomeComponent;
  let fixture: ComponentFixture<DeliveryExecutiveHomeComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DeliveryExecutiveHomeComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DeliveryExecutiveHomeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
