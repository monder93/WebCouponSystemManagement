import { AppError } from './../../../common/app-error';
import { BadInput } from './../../../common/bad-input';
import { Company } from './../../../interfaces/company';
import { ViewAllCompaniesComponent } from './../view-all-companies/view-all-companies.component';
import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { AdminService } from './../../../services/admin-services/admin.service';
import { Router } from '@angular/router';
import { HttpErrorResponse } from '@angular/common/http';
import swal from 'sweetalert2';

@Component({
  selector: 'app-create-company',
  templateUrl: './create-company.component.html',
  styleUrls: ['./create-company.component.css']
})
export class CreateCompanyComponent implements OnInit {

  company: Company;


  createCompanyForm = new FormGroup(
    {
      compName: new FormControl('', [Validators.required ]),
      password: new FormControl('' , [Validators.required ]),
      email: new FormControl('' , [Validators.required ])
    }
  );


  constructor(private adminService: AdminService, private router: Router) { }

  ngOnInit() {
  }


  createCompany() {

    this.company = this.createCompanyForm.value;

    this.adminService.create_company(this.company).subscribe(
      response => {

        //swal message about success , and then navigate 
        swal({
          title: 'Success',
          text: 'company created',
          type: 'success',
          showCancelButton: false,
          confirmButtonText: 'Done',
        }).then((result) => {
          if (result.value) {
            this.router.navigateByUrl('/viewAllCompanies');
          }
        }
      )
      },
      (err: HttpErrorResponse ) => {

        if(err.status == 401)
        {
        //error in creating company 
        //swal message about error , and then reset form 
        swal("UNAUTHOIZED" ,"you not authorized to create company " ,"error").then(()=>{
          this.router.navigateByUrl('/login');
        });
        }

        if(err.status == 409)
        {
        //error in creating company 
        //swal message about error , and then reset form 
        swal(err.error ,"try again with different values " ,"error").then(()=>{
          this.createCompanyForm.get("compName").setValue("");
          this.createCompanyForm.get("password").setValue("");
          this.createCompanyForm.get("email").setValue("");

        });
        }


      }
    );
  }

}
