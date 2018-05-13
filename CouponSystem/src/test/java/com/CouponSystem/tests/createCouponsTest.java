package com.CouponSystem.tests;

import com.CouponSystem.Entities.ClientType;
import com.CouponSystem.Entities.Coupon;
import com.CouponSystem.Entities.CouponType;
import com.CouponSystem.Facades.CompanyFacade;
import com.CouponSystem.Facades.CouponSystem;
import com.CouponSystem.Utilities.converter;

public class createCouponsTest 
{
	
	public createCouponsTest(CouponSystem couponSystem) throws Exception
	{
		CompanyFacade companyFacade;
		companyFacade = (CompanyFacade) couponSystem.login("microsoft","123",ClientType.COMPANY);
		//preparing the coupons
		// id=1
		Coupon coupon1 = new Coupon("microsoftCoupon1", converter.stringToDate("2018-01-01"), converter.stringToDate("2019-01-01"), 1, CouponType.CAMPING, "coupon1", 10, "image1");
		// id=2
		Coupon coupon2 = new Coupon("microsoftCoupon2", converter.stringToDate("2018-02-02"), converter.stringToDate("2019-02-02"), 2, CouponType.CAMPING, "coupon2", 20, "image2");
		// id=3
		Coupon coupon3 = new Coupon("microsoftCoupon3", converter.stringToDate("2018-03-03"), converter.stringToDate("2019-03-03"), 3, CouponType.FOOD, "coupon3", 30, "image3");

		//creating the coupons
		System.out.println("------------------------------------------------------------first time creating coupons------------------------------------------------------------");
		companyFacade.createCoupon(coupon1);
		companyFacade.createCoupon(coupon2);
		companyFacade.createCoupon(coupon3);
		System.out.println("------------------------------------------------------------creating exist coupon------------------------------------------------------------");
		companyFacade.createCoupon(coupon3);

		//login to google company
		companyFacade = (CompanyFacade) couponSystem.login("google","456",ClientType.COMPANY);

		//preparing the coupons
		// id=4
		Coupon coupon4 = new Coupon("googleCoupon1", converter.stringToDate("2018-04-04"), converter.stringToDate("2019-04-04"), 1, CouponType.CAMPING, "coupon4", 40, "image4");
		// id=5
		Coupon coupon5 = new Coupon("googleCoupon2", converter.stringToDate("2018-05-05"), converter.stringToDate("2019-05-05"), 2, CouponType.CAMPING, "coupon5", 50, "image5");
		// id=6
		Coupon coupon6 = new Coupon("googleCoupon3", converter.stringToDate("2018-06-06"), converter.stringToDate("2019-06-06"), 3, CouponType.FOOD, "coupon6", 60, "image6");

		//creating the coupons
		System.out.println("------------------------------------------------------------second time creating coupons------------------------------------------------------------");
		companyFacade.createCoupon(coupon4);
		companyFacade.createCoupon(coupon5);
		companyFacade.createCoupon(coupon6);
		System.out.println("------------------------------------------------------------creating exist coupon from microsoft company------------------------------------------------------------");
		companyFacade.createCoupon(coupon3);
	}
}
