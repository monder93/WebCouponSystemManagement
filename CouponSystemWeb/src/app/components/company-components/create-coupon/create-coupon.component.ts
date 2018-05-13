import { element } from 'protractor';
import { Component, OnInit, ViewChild } from '@angular/core';
import { CompanyService } from '../../../services/company-services/company.service';
import { Router } from '@angular/router';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { HttpErrorResponse } from '@angular/common/http';
import { BadInput } from '../../../common/bad-input';
import { Coupon } from '../../../interfaces/coupon';

import swal from 'sweetalert2';

@Component({
  selector: 'app-create-coupon',
  templateUrl: './create-coupon.component.html',
  styleUrls: ['./create-coupon.component.css']
})
export class CreateCouponComponent implements OnInit {

  coupon : Coupon;
  image  : any ;
  img : any;
  startDate = new Date();
  endDate = new Date();

  createCouponForm = new FormGroup(
    {
      title: new FormControl('', [Validators.required]),
      startDate: new FormControl(this.startDate, [Validators.required]),
      endDate: new FormControl(this.endDate, [Validators.required]),
      amount: new FormControl('1', [Validators.required]),
      type: new FormControl('RESTURANS', [Validators.required]),
      message: new FormControl('', [Validators.required]),
      price: new FormControl('1', [Validators.required]),
      image: new FormControl(this.image, [Validators.required])

    }
  );

  constructor(private compnayService: CompanyService, private router: Router) { }

  ngOnInit() {
    this.endDate.setDate(this.endDate.getDate()+1);
  }

  createCoupon() {

    this.coupon = this.createCouponForm.value;

    this.compnayService.create_coupon(this.coupon).subscribe(
      response => {
 
        //swal message about success , and then navigate 
        swal({
          title: 'Success',
          text: 'coupon created',
          type: 'success',
          showCancelButton: false,
          confirmButtonText: 'Done',
        })
        .then((result) => {
          if (result.value) {
            this.router.navigateByUrl('/viewAllCoupons');
          }
        }
      )
      },
      (err: HttpErrorResponse) => {

        console.log("logged in to error catch");
        console.log(err.status);

        if(err.status == 401)
        {
          console.log("logged in to error 401");
          //error in creating company 
        //swal message about error , and then reset form 
        swal("UNAUTHOIZED" ,"you not authorized to create coupon " ,"error").then(()=>{
          this.router.navigateByUrl('/login');
        });
        }

        //error in creating company 
        //swal message about error , and then reset form 
        if(err.status == 409)
        {
          console.log("logged in to error 409");

        console.log(err.error);
        swal(err.error ,"try again with different values " ,"error").then(()=>{
          this.createCouponForm.get("title").setValue("");
          this.createCouponForm.get("amount").setValue("1");
          this.createCouponForm.get("message").setValue("");
          this.createCouponForm.get("price").setValue("1");
          this.createCouponForm.get("image").setValue("");



        });
      }

      }
    );
  }

  chooseFile(image) {
    if(image.target.files.length > 0) 
    {
      //this.image = image.target.value;
      this.image = image.target.files[0].name;
    }
      else
    {
      this.image ="";
    }
    }

  onImage(price : Object)
  {
    console.log(price);
    
    alert(price);
  }

}
