import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Coupon } from '../../interfaces/coupon';
import { Observable } from 'rxjs/Observable';

@Injectable()
export class CompanyService {

  // Rest URLS 
  private GET_ALL_COUPONS_URL = "/company/getallcoupons";
  private CREATE_NEW_COUPON_URL = "/company/createcoupon";
  private UPDATE_COUPON_URL = "/company/updateCoupon";
  private DELETE_COUPON_URL = "/company/deletecoupon/";
  private SEARCH_BY_TYPE_COUPON_URL = "/company/getallcouponsbycoupontype/";
  private SEARCH_BY_PRICE_COUPON_URL = "/company/getallcouponsbyprice/";
  private SEARCH_BY_DATE_COUPON_URL = "/company/getallcouponsbydate/";


  //HttpClient to use it with http requests ,, get post put delete
  constructor(private http: HttpClient) { }

  //view all COUPONS (get)
  get_all_coupons() {
    //httpClient automatically calls response.json() internally
    return this.http.get<Coupon[]>(this.GET_ALL_COUPONS_URL)
    .catch((error : HttpErrorResponse) => {
      return Observable.throw(error);
    });
  }

  //create company (post)
  create_coupon(coupon: Coupon) {
    return this.http.post<Coupon>(this.CREATE_NEW_COUPON_URL, coupon)
    .catch((error : HttpErrorResponse) => {
      return Observable.throw(error);
    });
  }

  //update coupon (put)
  update_coupon(coupon: Coupon) {
    return this.http.put<Coupon>(this.UPDATE_COUPON_URL, coupon)
    .catch((error : HttpErrorResponse) => {
      return Observable.throw(error);
    });
  }

  //delete company (delete)
  delete_coupon(id: number) {
    return this.http.delete<Coupon>(this.DELETE_COUPON_URL + id)
    .catch((error : HttpErrorResponse) => {
      return Observable.throw(error);
    });
  }

  //search coupon by type (get)
  serach_by_type_coupon(type: String) {
    return this.http.get<Coupon[]>(this.SEARCH_BY_TYPE_COUPON_URL + type)
    .catch((error : HttpErrorResponse) => {
      return Observable.throw(error);
    });
  }

  //search coupon by price (get)
  serach_by_price_coupon(price: number) {
    return this.http.get<Coupon[]>(this.SEARCH_BY_PRICE_COUPON_URL + price)
    .catch((error : HttpErrorResponse) => {
      return Observable.throw(error);
    });
  }

    //search coupon by price (get)
    serach_by_date_coupon(date: Date) {
      return this.http.get<Coupon[]>(this.SEARCH_BY_DATE_COUPON_URL + date)
      .catch((error : HttpErrorResponse) => {
        return Observable.throw(error);
      });
    }

}
