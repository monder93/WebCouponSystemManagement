import { HeaderComponent } from './../header/header.component';
import { AppComponent } from './../../app.component';
import { HttpClient } from '@angular/common/http';
import { Component, OnInit, ViewChild, Host } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { User } from '../../interfaces/user';
import { Router } from '@angular/router';
import { AlertService } from 'ngx-alerts';


import swal from 'sweetalert2';


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  private url = "/loginService";
  private user: User;
  loginForm = new FormGroup(
    {
      username: new FormControl('', [Validators.required]),
      password: new FormControl('', [Validators.required]),
      type: new FormControl('admin', [Validators.required])
    }
  );

  constructor(private http: HttpClient, private router: Router,
     @Host() private app: AppComponent ,
      private alertService: AlertService
      ) { }

  ngOnInit() {

    console.log("app type is : " + this.app.type);
    

    if(sessionStorage.getItem('type') == "admin")
    {
      this.router.navigateByUrl('/viewAllCompanies');
    }

    if(sessionStorage.getItem('type') == "company")
    {
      this.router.navigateByUrl('/viewAllCoupons');
    }

    if(sessionStorage.getItem('type') == "customer")
    {
      this.router.navigateByUrl('/viewAllPurchasedCoupons');
    }
  }

  login() {
    console.log(this.loginForm.value.type);
    this.user = this.loginForm.value;
    this.http.post(this.url, this.user).subscribe(response => {
      //checking if the response status 202 accepted or 400 bad request
      this.app.type = this.user.type;
      sessionStorage.setItem("type" , this.user.type);
      if (this.user.type == "admin") 
      {
        this.alertService.success('Welcome to admin control panel ');
          this.router.navigateByUrl('/viewAllCompanies');
  
      }
      else if (this.user.type == "company") 
      {
        this.alertService.success('Welcome to company control panel ');
          this.router.navigateByUrl('/viewAllCoupons');
      }
      else 
      {
        this.alertService.success('Welcome to customer control panel ');
          this.router.navigateByUrl('/viewAllPurchasedCoupons');
      }
    }, (error: Response) => {
      if (error.status == 401) 
      {
        this.loginForm.get("username").setValue("");
        this.loginForm.get("password").setValue("");
        this.alertService.danger('wrong username or password ');  
      }

    });
  }

}
