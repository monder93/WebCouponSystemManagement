package com.CouponSystem.tests;

import com.CouponSystem.Entities.Coupon;
import com.CouponSystem.Facades.AdminFacade;
import com.CouponSystem.Facades.CompanyFacade;
import com.CouponSystem.Utilities.converter;

public class updateCouponsTest 
{
	public updateCouponsTest(CompanyFacade companyFacade,AdminFacade adminFacade)
	{
		//getting coupon from the database
		Coupon coupon = companyFacade.getCoupon(5);
		
		//removing from the database
		coupon.setEndDate(converter.stringToDate("2017-02-01"));
		coupon.setPrice(11);
		companyFacade.updateCoupon(coupon);
		
		//get all the companies 
		new getAllCompaniesTest(adminFacade);
		System.out.println("------------------------------");
		//new getAllCustomersTest(adminFacade);
		getAllCustomersTest.getAllCustomersTest1(adminFacade);				
	}
}
