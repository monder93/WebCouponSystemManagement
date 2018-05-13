import { CompanyService } from './../../../services/company-services/company.service';
import { Component, OnInit, ViewChild, Inject } from '@angular/core';
import { Coupon } from '../../../interfaces/coupon';
import { MatTableDataSource, MatSort, MatPaginator, MatDialog, MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';
import { UpdateCouponComponent } from '../update-coupon/update-coupon.component';
import swal from 'sweetalert2';
import { noUndefined } from '@angular/compiler/src/util';
import { AlertService } from 'ngx-alerts';
import { HttpErrorResponse } from '@angular/common/http';
import { Router } from '@angular/router';

@Component({
  selector: 'app-view-all-coupons',
  templateUrl: './view-all-coupons.component.html',
  styleUrls: ['./view-all-coupons.component.css']
})
export class ViewAllCouponsComponent implements OnInit {

  //default 
  private defaultPrice = 1;
  private defaultType = " '' ";
  private defaultDate = " '' ";


  dataSource = new MatTableDataSource();
  private allCoupons: Coupon[];
  displayedColumns = ['id', 'title', 'startDate', 'endDate', 'amount', 'type', 'message', 'price', 'update', 'delete'];

  // angular materials MatSort , MatPaginator
  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;


  constructor(private companyService: CompanyService, public dialog: MatDialog ,
    private alertService: AlertService , private router : Router) { }

  //----------------------------------------------------------ngOnInit----------------------------------------------
  ngOnInit() {
    this.getAllCoupons();
  }

  //----------------------------------------------------------ngAfterViewInit----------------------------------------------
  ngAfterViewInit() {

    // connecting the dataSource sort and paginator with the angular materials ( sort and paginator )
    this.dataSource.sort = this.sort;
    this.dataSource.paginator = this.paginator;
  }

  //----------------------------------------------------------getAllCompanies----------------------------------------------
  getAllCoupons() {
    return this.companyService.get_all_coupons().subscribe(
      companyResponse => {

        this.allCoupons = companyResponse;
        this.dataSource.data = this.allCoupons;

      },
      (err: HttpErrorResponse) => {

        if(err.status == 401)
        {
        //error in creating company 
        //swal message about error , and then reset form 
        swal("UNAUTHOIZED" ,"you not authorized to view coupons " ,"error").then(()=>{
          this.router.navigateByUrl('/login');
        });
        }
      }
    );
  }

  //----------------------------------------------------------resetTable-------------------------------------------------
  resetTable() {
    this.getAllCoupons();
    this.defaultPrice = 1;
    this.defaultType = " '' ";
    this.defaultDate = " '' ";
  }

  //----------------------------------------------------------deleteCompany----------------------------------------------
  deleteCoupon(id: number) {
    return this.companyService.delete_coupon(id).subscribe(
      () => {

        this.getAllCoupons();
      }
    );
  }

  //----------------------------------------------------------onDelete----------------------------------------------
  onDelete(id: number) {
    swal({
      title: 'Are you sure?',
      text: 'You will not be able to recover this company!',
      type: 'warning',
      showCancelButton: true,
      confirmButtonText: 'Yes, delete it!',
      cancelButtonText: 'No, keep it'
    }).then((result) => {
      if (result.value) {
        this.deleteCoupon(id);
        swal(
          'Deleted!',
          'company has been deleted.',
          'success'
        )
      }
    }
    )
  }

  //----------------------------------------------------------updateCoupon----------------------------------------------
  updateCoupon(coupon: Coupon) {
    return this.companyService.update_coupon(coupon).subscribe(
      () => {

        this.getAllCoupons();
        this.alertService.success("coupon updated successfully");
      }
    );
  }

  //----------------------------------------------------------onUpdate----------------------------------------------
  onUpdate(coupon: Coupon) {
    alert("update id " + coupon.title);
  }

  //----------------------------------------------------------openDialog----------------------------------------------
  openDialog(coupon: Coupon): void {
    let dialogRef = this.dialog.open(UpdateCouponComponent, { width: '250px', data: coupon })

    dialogRef.afterClosed().subscribe(result => {
      //receving the new data inside result 
      //calling the update function 
      if (result != null) {
        this.updateCoupon(result);
      }
    });
  }

  //------------------------------------------------------------searchByType()---------------------------------------
  searchByType(type: String) {
    this.companyService.serach_by_type_coupon(type).subscribe(companyResponse => {
      this.allCoupons = companyResponse;
      this.dataSource.data = this.allCoupons;
    });
  }
  //-----------------------------------------------------------test---------------------------------------
  testFunction(x: String) {
    alert(x);
  }

  //------------------------------------------------------------searchByPrice()---------------------------------------
  searchByPrice(price: number) {
    if (price > 0) {
      this.companyService.serach_by_price_coupon(price).subscribe(companyResponse => {
        this.allCoupons = companyResponse;
        this.dataSource.data = this.allCoupons;
      });
    }
    else {
      swal("you must enter a number", '', "warning").then(() => {

        this.defaultPrice = 1;

      });

    }
  }

  //------------------------------------------------------------searchByDate()---------------------------------------
  searchByDate(date: Date) {
    if (date) {
      this.companyService.serach_by_date_coupon(date).subscribe(companyResponse => {
        this.allCoupons = companyResponse;
        this.dataSource.data = this.allCoupons;
      });
    }
    else {
      swal("you must enter a date", '', "warning");
    }
  }


}
