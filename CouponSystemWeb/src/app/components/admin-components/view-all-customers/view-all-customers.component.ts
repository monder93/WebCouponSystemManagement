import { Component, OnInit, ViewChild, Inject } from '@angular/core';
import { AdminService } from './../../../services/admin-services/admin.service';
import { Customer } from '../../../interfaces/customer';
import { MatTableDataSource, MatSort, MatPaginator, MatDialog, MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';
import { UpdateCustomerComponent } from '../update-customer/update-customer.component';

import swal from 'sweetalert2';
import { AlertService } from 'ngx-alerts';
import { HttpErrorResponse } from '@angular/common/http';
import { Router } from '@angular/router';
@Component({
  selector: 'app-view-all-customers',
  templateUrl: './view-all-customers.component.html',
  styleUrls: ['./view-all-customers.component.css']
})
export class ViewAllCustomersComponent implements OnInit {

  dataSource = new MatTableDataSource();
  private allCustomers: Customer[];
  displayedColumns = ['id', 'custName', 'password', 'update', 'delete'];

  // angular materials MatSort , MatPaginator
  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;

  //----------------------------------------------------------constructor----------------------------------------------
  constructor(private adminService: AdminService, public dialog: MatDialog , private alertService : AlertService, private router : Router) { }


  //----------------------------------------------------------ngOnInit----------------------------------------------
  ngOnInit() {
    this.getAllCustomers();
  }

  //----------------------------------------------------------ngAfterViewInit----------------------------------------------
  ngAfterViewInit() {

    // connecting the dataSource sort and paginator with the angular materials ( sort and paginator )
    this.dataSource.sort = this.sort;
    this.dataSource.paginator = this.paginator;
  }

  //----------------------------------------------------------getAllCustomer----------------------------------------------
  getAllCustomers() {
    return this.adminService.get_all_customers().subscribe(
      customerResponse => {

        this.allCustomers = customerResponse;
        this.dataSource.data = this.allCustomers;
      },
      (err: HttpErrorResponse ) => {

        if(err.status == 401)
        {
        //error in creating company 
        //swal message about error , and then reset form 
        swal("UNAUTHOIZED" ,"you not authorized to view customers " ,"error").then(()=>{
          this.router.navigateByUrl('/login');
        });
        }
      }
    );
  }

  //----------------------------------------------------------deleteCustomer----------------------------------------------
  deleteCustomer(id : number) {
    return this.adminService.delete_customer(id).subscribe(
      () => {

        this.getAllCustomers();
      }
    );
  }

  //----------------------------------------------------------onDelete----------------------------------------------
  onDelete(id: number) {
    swal({
      title: 'Are you sure?',
      text: 'You will not be able to recover this customer!',
      type: 'warning',
      showCancelButton: true,
      confirmButtonText: 'Yes, delete it!',
      cancelButtonText: 'No, keep it'
    }).then((result) => {
      if (result.value) {
        this.deleteCustomer(id);
        swal(
          'Deleted!',
          'customer has been deleted.',
          'success'
        )
      }
    }
  )
  }


    //----------------------------------------------------------updateCustomer----------------------------------------------
    updateCustomer(customer : Customer) {
      return this.adminService.update_customer(customer).subscribe(
        () => {
  
          this.getAllCustomers();
          this.alertService.success("customer updated successfully");

        }
      );
    }

  //----------------------------------------------------------openDialog----------------------------------------------
  openDialog(customer: Customer): void {
    let dialogRef = this.dialog.open(UpdateCustomerComponent, { width: '250px', data: customer })

    dialogRef.afterClosed().subscribe(result => {
      //receving the new data inside result 
      //calling the update function 
      if(result != null)
      {
        this.updateCustomer(result);
      }
    });
  }

}
