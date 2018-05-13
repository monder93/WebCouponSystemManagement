import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewAllCouponsComponent } from './view-all-coupons.component';

describe('ViewAllCouponsComponent', () => {
  let component: ViewAllCouponsComponent;
  let fixture: ComponentFixture<ViewAllCouponsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ViewAllCouponsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ViewAllCouponsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
