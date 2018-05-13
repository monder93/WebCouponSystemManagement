import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewAllPurchasedCouponsComponent } from './view-all-purchased-coupons.component';

describe('ViewAllPurchasedCouponsComponent', () => {
  let component: ViewAllPurchasedCouponsComponent;
  let fixture: ComponentFixture<ViewAllPurchasedCouponsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ViewAllPurchasedCouponsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ViewAllPurchasedCouponsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
