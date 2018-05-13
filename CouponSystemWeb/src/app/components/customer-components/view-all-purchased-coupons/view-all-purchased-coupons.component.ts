import { Component, OnInit } from '@angular/core';
import { CustomerService } from '../../../services/customer-services/customer.service';
import { Coupon } from '../../../interfaces/coupon';
import { HttpErrorResponse } from '@angular/common/http';
import { Router } from '@angular/router';
import swal from 'sweetalert2';

@Component({
  selector: 'app-view-all-purchased-coupons',
  templateUrl: './view-all-purchased-coupons.component.html',
  styleUrls: ['./view-all-purchased-coupons.component.css']
})
export class ViewAllPurchasedCouponsComponent implements OnInit 
{

  private allPurchasedCoupons : Coupon[];

  constructor(private customerService : CustomerService , private router : Router) { }

      //----------------------------------------------------------ngOnInit----------------------------------------------
  ngOnInit() {
    this.getAllPurchasedCoupons();
  }

    //----------------------------------------------------------getAllCompanies----------------------------------------------
    getAllPurchasedCoupons() {
      return this.customerService.get_all_purchased_coupons().subscribe(
        customerResponse => {
  
          this.allPurchasedCoupons = customerResponse;
          console.log("customerResponse is : " + customerResponse );
          console.log("-------------------------------" );
          console.log("allPurchasedCoupons is : " + this.allPurchasedCoupons );

        },
        (err: HttpErrorResponse) => {
  
          if(err.status == 401)
          {
          //error in creating company 
          //swal message about error , and then reset form 
          swal("UNAUTHOIZED" ,"you not authorized to view purchased coupons " ,"error").then(()=>{
            this.router.navigateByUrl('/login');
          });
          }
          });
    }

}
