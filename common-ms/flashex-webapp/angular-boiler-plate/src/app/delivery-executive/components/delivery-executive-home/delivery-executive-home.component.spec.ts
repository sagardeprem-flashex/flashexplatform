import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DeliveryExecutiveHomeComponent } from './delivery-executive-home.component';
import { RouterTestingModule } from '@angular/router/testing';
import { HttpClientTestingModule } from '@angular/common/http/testing';

describe('DeliveryExecutiveHomeComponent', () => {
  let component: DeliveryExecutiveHomeComponent;
  let fixture: ComponentFixture<DeliveryExecutiveHomeComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DeliveryExecutiveHomeComponent ],
      imports: [RouterTestingModule, HttpClientTestingModule]
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
