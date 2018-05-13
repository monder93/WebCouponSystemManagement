import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Coupon } from '../../interfaces/coupon';
import { Observable } from 'rxjs/Observable';

@Injectable()
export class CustomerService {


  private GET_ALL_PURCHASED_COUPONS_URL = "/customer/getallpurchasedcoupons";
  private GET_ALL_COUPONS_URL = "/customer/getallcoupons";
  private POST_PURCHASE_COUPON_URL = "/customer/purchasecoupon";


  constructor(private http: HttpClient) { }

  get_all_purchased_coupons()
  {
    return this.http.get<Coupon[]>(this.GET_ALL_PURCHASED_COUPONS_URL)
    .catch((error : HttpErrorResponse) => {
      return Observable.throw(error);
    });
  }

  get_all_coupons()
  {
    return this.http.get<Coupon[]>(this.GET_ALL_COUPONS_URL)
    .catch((error : HttpErrorResponse) => {
      return Observable.throw(error);
    });
  }

  purchase_coupon(coupon: Coupon)
  {
    return this.http.post(this.POST_PURCHASE_COUPON_URL , coupon)
    .catch((error : HttpErrorResponse) => {
      return Observable.throw(error);
    });

  }
}
