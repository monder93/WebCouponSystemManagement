import { Router } from '@angular/router';
import { Component, OnInit, ViewChild } from '@angular/core';
import { Coupon } from '../../../interfaces/coupon';
import { CustomerService } from '../../../services/customer-services/customer.service';
import { MatTableDataSource, MatPaginator, MatSort } from '@angular/material';
import { HttpErrorResponse } from '@angular/common/http';
import swal from 'sweetalert2';
@Component({
  selector: 'app-purchase-coupon',
  templateUrl: './purchase-coupon.component.html',
  styleUrls: ['./purchase-coupon.component.css']
})
export class PurchaseCouponComponent implements OnInit {

  dataSource = new MatTableDataSource();
  private allCoupons : Coupon[];
  displayedColumns = ['id', 'title', 'startDate', 'endDate', 'amount', 'type', 'message', 'price', 'purchase'];


  // angular materials MatSort , MatPaginator
  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;
  
  constructor(private customerService : CustomerService ,private router : Router) { }

  ngOnInit() {
    this.getAllCoupons();
  }

    //----------------------------------------------------------ngAfterViewInit----------------------------------------------
    ngAfterViewInit() {

      // connecting the dataSource sort and paginator with the angular materials ( sort and paginator )
      this.dataSource.sort = this.sort;
      this.dataSource.paginator = this.paginator;
    }

      //----------------------------------------------------------getAllCoupons----------------------------------------------
      getAllCoupons() {
        return this.customerService.get_all_coupons().subscribe(
          customerResponse => {
    
            this.allCoupons = customerResponse;
            this.dataSource.data = this.allCoupons;
            console.log("customerResponse is : " + customerResponse );
            console.log("-------------------------------" );
            console.log("allCoupons is : " + this.allCoupons );
  
          },
          (err : HttpErrorResponse) => 
          {
            if(err.status == 401)
            {
            //error in creating company 
            //swal message about error , and then reset form 
            swal("UNAUTHOIZED" ,"you not authorized to purchase coupon " ,"error").then(()=>{
              this.router.navigateByUrl('/login');
            });
            }

          }
        );
      }

      purchase(coupon : Coupon) : void
      {
        this.customerService.purchase_coupon(coupon).subscribe( response => {

        //swal message about success , and then navigate 
        swal({
          title: 'Success',
          text: 'coupon purchased',
          type: 'success',
          showCancelButton: false,
          confirmButtonText: 'Done',
        }).then((result) => {
          if (result.value) {
            this.router.navigateByUrl('/viewAllPurchasedCoupons')
          }
        }
      )

        },
        (err: HttpErrorResponse) => {
          console.log("logge in to error catch");
          
          if(err.status == 401)
          {
          //error in creating company 
          //swal message about error , and then reset form 
          swal("UNAUTHOIZED" ,"you not authorized to purchase coupon " ,"error").then(()=>{
            this.router.navigateByUrl('/login');
          });
          }

          if(err.status == 409)
          {
          //error in purchasing coupon
          //swal message about error 
          swal(err.error ,"try with different coupon " ,"error");
          }

  
          });
        }
      }

