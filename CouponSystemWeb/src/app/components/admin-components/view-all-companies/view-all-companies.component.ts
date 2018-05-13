import { AdminService } from './../../../services/admin-services/admin.service';
import { Component, OnInit, ViewChild, Inject } from '@angular/core';
import { Company } from '../../../interfaces/company';
import { MatTableDataSource, MatSort, MatPaginator, MatDialog, MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';
import { UpdateCompanyComponent } from '../update-company/update-company.component';
import swal from 'sweetalert2';
import { AlertService } from 'ngx-alerts';
import { HttpErrorResponse } from '@angular/common/http';
import { Router } from '@angular/router';



@Component({
  selector: 'app-view-all-companies',
  templateUrl: './view-all-companies.component.html',
  styleUrls: ['./view-all-companies.component.css']
})
export class ViewAllCompaniesComponent implements OnInit {

  dataSource = new MatTableDataSource();
  private allCompanies: Company[];
  displayedColumns = ['id', 'compName', 'password', 'email', 'update', 'delete'];

  // angular materials MatSort , MatPaginator
  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;

  //----------------------------------------------------------constructor----------------------------------------------
  constructor(private adminService: AdminService, public dialog: MatDialog , private alertService : AlertService, private router : Router) {
  }

  //----------------------------------------------------------ngOnInit----------------------------------------------
  ngOnInit() {
    // getting the companies into allCompanies array , and into dataSource
    this.getAllCompanies();
  }

  //----------------------------------------------------------ngAfterViewInit----------------------------------------------
  ngAfterViewInit() {

    // connecting the dataSource sort and paginator with the angular materials ( sort and paginator )
    this.dataSource.sort = this.sort;
    this.dataSource.paginator = this.paginator;
  }

  //----------------------------------------------------------getAllCompanies----------------------------------------------
  getAllCompanies() {
    return this.adminService.get_all_companies().subscribe(
      companyResponse => {

        this.allCompanies = companyResponse;
        this.dataSource.data = this.allCompanies;
      },
      (err: HttpErrorResponse ) => {

        if(err.status == 401)
        {
        //error in creating company 
        //swal message about error , and then reset form 
        swal("UNAUTHOIZED" ,"you not authorized to view companies " ,"error").then(()=>{
          this.router.navigateByUrl('/login');
        });
        }
      }
    );
  }

    //----------------------------------------------------------deleteCompany----------------------------------------------
    deleteCompany(id : number) {
      return this.adminService.delete_company(id).subscribe(
        () => {
  
          this.getAllCompanies();
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
        this.deleteCompany(id);
        swal(
          'Deleted!',
          'company has been deleted.',
          'success'
        )
      }
    }
  )
  }


      //----------------------------------------------------------updateCustomer----------------------------------------------
      updateCompany(company : Company) {
        return this.adminService.update_company(company).subscribe(
          () => {
    
            this.getAllCompanies();
            this.alertService.success("company updated successfully");

          }
        );
      }

  //----------------------------------------------------------onUpdate----------------------------------------------
  onUpdate(company: Company) {
    alert("update id " + company.email);
  }

  //----------------------------------------------------------openDialog----------------------------------------------
  openDialog(company: Company): void {
    let dialogRef = this.dialog.open(UpdateCompanyComponent, {width : '250px' ,  data: company })
    
    dialogRef.afterClosed().subscribe(result => {
      //receving the new data inside result 
      //calling the update function 
      if(result != null)
      {
        this.updateCompany(result);
      }
    });
  }
}
