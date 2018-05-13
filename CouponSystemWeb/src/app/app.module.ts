import { CompanyService } from './services/company-services/company.service';
import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';  //<<<< import it here
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { HttpClientModule } from '@angular/common/http';

import { ReactiveFormsModule } from '@angular/forms';

import { AppComponent } from './app.component';
import { HeaderComponent } from './components/header/header.component'; // importing the materialModule we made
import { RoutermoduleModule } from './modules/routermodule/routermodule.module';
import { FooterComponent } from './components/footer/footer.component'; // importing the RouterModule
import { MaterialModule } from './modules/material/material.module';
import { SidebarAdminComponent } from './components/sidebar-admin/sidebar-admin.component';
import { SidebarCompanyComponent } from './components/sidebar-company/sidebar-company.component';
import { SidebarCustomerComponent } from './components/sidebar-customer/sidebar-customer.component';
import { ViewAllCompaniesComponent } from './components/admin-components/view-all-companies/view-all-companies.component';

//importing services
import { AdminService } from './services/admin-services/admin.service';
import { UpdateCompanyComponent } from './components/admin-components/update-company/update-company.component';
import { ViewAllCustomersComponent } from './components/admin-components/view-all-customers/view-all-customers.component';
import { UpdateCustomerComponent } from './components/admin-components/update-customer/update-customer.component';
import { CreateCompanyComponent } from './components/admin-components/create-company/create-company.component';
import { CreateCustomerComponent } from './components/admin-components/create-customer/create-customer.component';
import { CreateCouponComponent } from './components/company-components/create-coupon/create-coupon.component';
import { ViewAllCouponsComponent } from './components/company-components/view-all-coupons/view-all-coupons.component';
import { PurchaseCouponComponent } from './components/customer-components/purchase-coupon/purchase-coupon.component';
import { UpdateCouponComponent } from './components/company-components/update-coupon/update-coupon.component';
import { ViewAllPurchasedCouponsComponent } from './components/customer-components/view-all-purchased-coupons/view-all-purchased-coupons.component';
import { CustomerService } from './services/customer-services/customer.service';


import {HttpClient } from '@angular/common/http';
import { TranslateModule, TranslateLoader } from '@ngx-translate/core';
import { TranslateHttpLoader } from '@ngx-translate/http-loader';
import { LoginComponent } from './components/login/login.component';

// Importing ngx-alerts // used one 
import { AlertModule } from 'ngx-alerts';

//session


export function HttpLoaderFactory(http: HttpClient) {
  return new TranslateHttpLoader(http);
}


@NgModule({
  //every component must be added to declarations 
  declarations: [
    AppComponent,
    HeaderComponent,
    FooterComponent,
    SidebarAdminComponent,
    SidebarCompanyComponent,
    SidebarCustomerComponent,
    ViewAllCompaniesComponent,
    UpdateCompanyComponent,
    ViewAllCustomersComponent,
    UpdateCustomerComponent,
    CreateCompanyComponent,
    CreateCustomerComponent,
    CreateCouponComponent,
    ViewAllCouponsComponent,
    PurchaseCouponComponent,
    UpdateCouponComponent,
    ViewAllPurchasedCouponsComponent,
    LoginComponent
    ],
  imports: [
    BrowserModule,
    HttpClientModule, // including the HttpClient , need to be after BrowserModule
    BrowserAnimationsModule,
    MaterialModule, // including all the angular materials from the module material.module.ts
    RoutermoduleModule,
    FormsModule, // including all the angular Routes 
    ReactiveFormsModule,
    HttpClientModule,
    TranslateModule.forRoot({
      loader: {
        provide: TranslateLoader,
        useFactory: HttpLoaderFactory,
        deps: [HttpClient]
      }
    }),
        // Specify your library as an import
        AlertModule.forRoot({maxMessages: 5, timeout: 5000})
  ],
  entryComponents : [UpdateCompanyComponent , UpdateCustomerComponent , UpdateCouponComponent] ,
  // every service must be added to providers
  providers: [AdminService , CompanyService , CustomerService ],
  bootstrap: [AppComponent]

})
export class AppModule { }
