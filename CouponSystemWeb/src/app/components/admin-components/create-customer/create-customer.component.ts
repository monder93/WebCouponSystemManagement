import { AppError } from './../../../common/app-error';
import { BadInput } from './../../../common/bad-input';
import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { AdminService } from './../../../services/admin-services/admin.service';
import { Router } from '@angular/router';
import { Customer } from '../../../interfaces/customer';
import { HttpErrorResponse } from '@angular/common/http';

import swal from 'sweetalert2';


@Component({
  selector: 'app-create-customer',
  templateUrl: './create-customer.component.html',
  styleUrls: ['./create-customer.component.css']
})
export class CreateCustomerComponent implements OnInit {

  customer: Customer;

  createCustomerForm = new FormGroup(
    {
      custName: new FormControl('', [Validators.required]),
      password: new FormControl('', [Validators.required]),
    }
  );


  constructor(private adminService: AdminService, private router: Router) { }

  ngOnInit() {
  }


  createCustomer() {

    this.customer = this.createCustomerForm.value; 88

    this.adminService.create_customer(this.customer).subscribe(
      response => {

        //swal message about success , and then navigate 
        swal({
          title: 'Success',
          text: 'customer created',
          type: 'success',
          showCancelButton: false,
          confirmButtonText: 'Done',
        }).then((result) => {
          if (result.value) {
            this.router.navigateByUrl('/viewAllCustomers');
          }
        }
      )
      },
      (err: HttpErrorResponse) => {

        if(err.status == 401)
        {
        //error in creating company 
        //swal message about error , and then reset form 
        swal("UNAUTHOIZED" ,"you not authorized to create customer " ,"error").then(()=>{
          this.router.navigateByUrl('/login');
        });
        }

        if(err.status == 409)
        {
        //error in creating company 
        //swal message about error , and then reset form 
        console.log(err.error);
        
        swal(err.error ,"try again with different values " ,"error").then(()=>{
          this.createCustomerForm.get("custName").setValue("");
          this.createCustomerForm.get("password").setValue("");
        });
        }

      }
    );
  }

}
