import { BadInput } from './../../common/bad-input';
import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { Company } from '../../interfaces/company';
import { Customer } from '../../interfaces/customer';
import { AppError } from '../../common/app-error';
import 'rxjs/add/operator/map'; // importing to use the map function in the http request 
import "rxjs/add/operator/catch";
import "rxjs/add/observable/throw";



@Injectable()
export class AdminService {

  // Rest URLS 
  private GET_ALL_COMPANIES_URL = "/admin/getAllCompanies";
  private CREATE_NEW_COMPANY_URL = "/admin/createcompany";
  private UPDATE_COMPANY_URL = "/admin/updatecompany";
  private DELETE_COMPANY_URL = "/admin/deletecompany/";

  private GET_ALL_CUSTOMERS_URL = "/admin/getAllCustomers";
  private CREATE_NEW_CUSTOMER_URL = "/admin/createcustomer";
  private UPDATE_CUSTOMER_URL = "/admin/updatecustomer";
  private DELETE_CUSTOMER_URL = "/admin/deletecustomer/";

  //HttpClient to use it with http requests ,, get post put delete
  constructor(private http: HttpClient) {

  }

  // http requests ..  --> REQUEST AND MAP TO JSON  .map(response => response.json);

  //view all companies (get)
  get_all_companies() {
    //httpClient automatically calls response.json() internally
    return this.http.get<Company[]>(this.GET_ALL_COMPANIES_URL)
    .catch((error: HttpErrorResponse) => {
      //throwing the error object
        return Observable.throw(error);
    }
    );
  }

  //create company (post)
  create_company(company: Company) {
    return this.http.post<Company>(this.CREATE_NEW_COMPANY_URL, company)
      .catch((error: HttpErrorResponse) => {
        //throwing the error object
          return Observable.throw(error);
      }
      );
  }

  //update company (put)
  update_company(company: Company) {
    //httpClient automatically calls response.json() internally
    return this.http.put<Customer>(this.UPDATE_COMPANY_URL, company)
    .catch((error: HttpErrorResponse) => {
      //throwing the error object
        return Observable.throw(error);
    }
    );
  }
  //delete company (delete)
  delete_company(id: number) {
    //httpClient automatically calls response.json() internally
    return this.http.delete<Customer>(this.DELETE_COMPANY_URL + id)
    .catch((error: HttpErrorResponse) => {
      //throwing the error object
        return Observable.throw(error);
    }
    );
  }

  //view all customers (get)

  get_all_customers() {
    //httpClient automatically calls response.json() internally
    return this.http.get<Customer[]>(this.GET_ALL_CUSTOMERS_URL)
    .catch((error: HttpErrorResponse) => {
      //throwing the error object
        return Observable.throw(error);
    }
    );
  }
  //create customer (post)
  create_customer(customer: Customer) {
    return this.http.post<Customer>(this.CREATE_NEW_CUSTOMER_URL, customer)
    .catch((error : HttpErrorResponse) => {
      //throwing the error object
      return Observable.throw(error);
      }
      );
  }
  //update customer (put)
  update_customer(customer: Customer) {
    //httpClient automatically calls response.json() internally
    return this.http.put<Customer>(this.UPDATE_CUSTOMER_URL, customer)
    .catch((error: HttpErrorResponse) => {
      //throwing the error object
        return Observable.throw(error);
    }
    );
  }
  //delete customer (delete)
  delete_customer(id: number) {
    //httpClient automatically calls response.json() internally
    return this.http.delete<Customer>(this.DELETE_CUSTOMER_URL + id)
    .catch((error: HttpErrorResponse) => {
      //throwing the error object
        return Observable.throw(error);
    }
    );
  }

}
