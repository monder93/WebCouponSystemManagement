package com.CouponSystem.tests;


import com.CouponSystem.Entities.ClientType;
import com.CouponSystem.Entities.Coupon;
import com.CouponSystem.Facades.CompanyFacade;
import com.CouponSystem.Facades.CouponSystem;
import com.CouponSystem.Facades.CustomerFacade;

public class purchaseCouponsTest 
{
	
	public purchaseCouponsTest(CouponSystem couponSystem) throws Exception
	{
		//login to microsoft company 
		CustomerFacade customerFacade;
		CompanyFacade companyFacade;
		companyFacade = (CompanyFacade) couponSystem.login("microsoft","123",ClientType.COMPANY);
		
		//creating coupons
		Coupon coupon1 = companyFacade.getCoupon(1);
		Coupon coupon2 = companyFacade.getCoupon(2);
		Coupon coupon3 = companyFacade.getCoupon(3);
		Coupon coupon4 = companyFacade.getCoupon(4);
		System.out.println("end of get coupons");
		System.out.println("coupon1 is : "+coupon1);

		//log in to monder customer
		customerFacade = (CustomerFacade) couponSystem.login("monder","123",ClientType.CUSTOMER);
		customerFacade.purchaseCoupon(coupon1);
		customerFacade.purchaseCoupon(coupon2);
		customerFacade.purchaseCoupon(coupon3);
		customerFacade.purchaseCoupon(coupon4);

		//log in to tariq customer
		customerFacade = (CustomerFacade) couponSystem.login("tariq","456",ClientType.CUSTOMER);
		coupon2 = companyFacade.getCoupon(2);
		customerFacade.purchaseCoupon(coupon2);
		
		//log in to majd customer
		customerFacade = (CustomerFacade) couponSystem.login("majd","789",ClientType.CUSTOMER);
		coupon1 = companyFacade.getCoupon(1);
		coupon3 = companyFacade.getCoupon(3);
		customerFacade.purchaseCoupon(coupon3);
		customerFacade.purchaseCoupon(coupon1);

	}

}
