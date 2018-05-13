import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {RouterModule,Routes,} from '@angular/router'; //importing the routerModule , Routes

// importing components 
import { HeaderComponent } from '../../components/header/header.component';
import { ViewAllCompaniesComponent } from '../../components/admin-components/view-all-companies/view-all-companies.component';
import { ViewAllCustomersComponent } from '../../components/admin-components/view-all-customers/view-all-customers.component';
import { CreateCompanyComponent } from '../../components/admin-components/create-company/create-company.component';
import { CreateCustomerComponent } from '../../components/admin-components/create-customer/create-customer.component';
import { CreateCouponComponent } from '../../components/company-components/create-coupon/create-coupon.component';
import { ViewAllCouponsComponent } from '../../components/company-components/view-all-coupons/view-all-coupons.component';
import { PurchaseCouponComponent } from '../../components/customer-components/purchase-coupon/purchase-coupon.component';
import { ViewAllPurchasedCouponsComponent } from '../../components/customer-components/view-all-purchased-coupons/view-all-purchased-coupons.component';
import { LoginComponent } from '../../components/login/login.component';

const routes: Routes = [
  { path: '', redirectTo: 'login', pathMatch: 'full' },
  { path: 'login', component: LoginComponent },

  { path: 'createCompany', component: CreateCompanyComponent },
  { path: 'viewAllCompanies', component: ViewAllCompaniesComponent },

  { path: 'createCustomer', component: CreateCustomerComponent },
  { path: 'viewAllCustomers', component: ViewAllCustomersComponent },

  { path: 'createCoupon', component: CreateCouponComponent },
  { path: 'viewAllCoupons', component: ViewAllCouponsComponent },

  { path: 'purchaseCoupon', component: PurchaseCouponComponent },
  { path: 'viewAllPurchasedCoupons', component: ViewAllPurchasedCouponsComponent }

];

@NgModule(
  {
  imports: [
    CommonModule,
    RouterModule.forRoot(routes) // <-- installs Router routes, components and services
  ],
  exports: [
    RouterModule
  ]
})
export class RoutermoduleModule { }