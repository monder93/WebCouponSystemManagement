package com.CouponSystem.tests;

import com.CouponSystem.Entities.Coupon;
import com.CouponSystem.Facades.AdminFacade;
import com.CouponSystem.Facades.CompanyFacade;

public class removeCouponsTest 
{
	public removeCouponsTest(CompanyFacade companyFacade,AdminFacade adminFacade)
	{
		//getting coupon from the database
		Coupon coupon = companyFacade.getCoupon(4);
		
		//removing from the database
		companyFacade.removeCoupon(coupon);
		
		//get all the companies 
		new getAllCompaniesTest(adminFacade);
		System.out.println("------------------------------");
		//new getAllCustomersTest(adminFacade);
		getAllCustomersTest.getAllCustomersTest1(adminFacade);
				
	}
}
