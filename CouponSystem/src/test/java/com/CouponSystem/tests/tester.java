package com.CouponSystem.tests;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.CouponSystem.Entities.ClientType;
import com.CouponSystem.Entities.CouponType;
import com.CouponSystem.Facades.AdminFacade;
import com.CouponSystem.Facades.CompanyFacade;
import com.CouponSystem.Facades.CouponSystem;
import com.CouponSystem.Facades.CustomerFacade;
import com.CouponSystem.Utilities.converter;
import com.CouponSystem.tests.createCompaniesTest;
import com.CouponSystem.tests.createCouponsTest;
import com.CouponSystem.tests.createCustomersTest;
import com.CouponSystem.tests.getAllCompaniesTest;
import com.CouponSystem.tests.getAllCustomersTest;
import com.CouponSystem.tests.getCompaniesCouponsByTest;
import com.CouponSystem.tests.getCustomersCouponsByTest;
import com.CouponSystem.tests.purchaseCouponsTest;
import com.CouponSystem.tests.removeCompaniesTest;
import com.CouponSystem.tests.removeCouponsTest;
import com.CouponSystem.tests.removeCustomersTest;
import com.CouponSystem.tests.updateCompaniesTest;
import com.CouponSystem.tests.updateCouponsTest;
import com.CouponSystem.tests.updateCustomersTest;

/**
 * 
 * The main class the simulates a UI for the project.
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class tester 
{	
	@Autowired
	private CouponSystem couponSystem;
	
	@Test
	public void test()
	{	
		// clearing the database
		//new clearDataBase();
		
		//DBDAO variables 
		AdminFacade adminFacade ;
		CompanyFacade companyFacade;
		CustomerFacade customerFacade;
	
		
		adminFacade = (AdminFacade) couponSystem.login("admin", "1234", ClientType.ADMIN);
		System.out.println(adminFacade);
		System.out.println(adminFacade.toString());
		/*
		 * create 3 companies 
		 * 1)Microsoft , 
		 * 2)Google , 
		 * 3)Intel
		 */
		System.out.println("------------------------------------------------------------1st test------------------------------------------------------------");
		System.out.println("------------------------------------------------------------create companies------------------------------------------------------------");
		try 
		{
			new createCompaniesTest(adminFacade);
		} 
		catch (Exception e)
		{
			System.out.println(e.getMessage());
		}
		System.out.println("------------------------------------------------------------finish creating companies------------------------------------------------------------");

		/*
		 * create 3 customers 
		 * 1)Monder, 
		 * 2)Tariq, 
		 * 3)Majd
		 */
		System.out.println("------------------------------------------------------------2nd test------------------------------------------------------------");
		System.out.println("------------------------------------------------------------create customers------------------------------------------------------------");
		
		try
		{
			new createCustomersTest(adminFacade);
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
		}
		
		System.out.println("------------------------------------------------------------finish creating customers------------------------------------------------------------");

		/*
		 * create 3 coupons for Microsoft company , and 3 coupons for Google company , and  trying to add exist coupons
		 * 1)microsoftCoupon1, microsoftCoupon2, microsoftCoupon3
		 * 2)googleCoupon1, googleCoupon2, googleCoupon3 
		 */
		System.out.println("------------------------------------------------------------3rd test------------------------------------------------------------");
		System.out.println("------------------------------------------------------------create Coupons------------------------------------------------------------");
		
		try
		{
			new createCouponsTest(couponSystem);
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
		}
		System.out.println("------------------------------------------------------------finish creating Coupons------------------------------------------------------------");

		
		/*
		 * getting all the companies 
		 */
		System.out.println("------------------------------------------------------------4th test------------------------------------------------------------");
		System.out.println("------------------------------------------------------------getAll companies------------------------------------------------------------");
		new getAllCompaniesTest(adminFacade);
		System.out.println("------------------------------------------------------------finish getting All companies------------------------------------------------------------");

		/*
		 * getting all the customers 
		 */
		System.out.println("------------------------------------------------------------5th test------------------------------------------------------------");
		System.out.println("------------------------------------------------------------getAll customers------------------------------------------------------------");
		getAllCustomersTest.getAllCustomersTest1(adminFacade);
		System.out.println("------------------------------------------------------------finish getting All customers------------------------------------------------------------");

		/*
		 * purchasing coupons for Monder , tariq and Majd customers
		 * Monder -> 4 coupons , tariq 1 coupon , majd 1 coupon and 1 not valid coupon
		 */
		System.out.println("------------------------------------------------------------6th test------------------------------------------------------------");
		System.out.println("------------------------------------------------------------purchase Coupons------------------------------------------------------------");
		
		try
		{
			new purchaseCouponsTest(couponSystem);
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
		}
		
		System.out.println("------------------------------------------------------------finish purchasing coupons------------------------------------------------------------");

		/*
		 * update companies info 
		 *microsoft stay 
		 *google update email to : goog@gmail.com
		 *intel update password to 123 and email to int@intel.com
		 *after finish reset password to intel : 789
		 */
		System.out.println("------------------------------------------------------------7th test------------------------------------------------------------");
		System.out.println("------------------------------------------------------------update Companies------------------------------------------------------------");
		new updateCompaniesTest(adminFacade);
		System.out.println("------------------------------------------------------------finish updating companies------------------------------------------------------------");
	
		/*
		 * update customers info 
		 *monder stay
		 *tariq update password : 111
		 *majd update  password  : 222 
		 *after finish reset the passwords to tariq : 456 , majd : 789
		 */
		System.out.println("------------------------------------------------------------8th test------------------------------------------------------------");
		System.out.println("------------------------------------------------------------update Customers------------------------------------------------------------");
		new updateCustomersTest(adminFacade);
		System.out.println("------------------------------------------------------------finish updating customers------------------------------------------------------------");
		
		getAllCustomersTest.getAllCustomersTest1(adminFacade);

		System.out.println("*************************************************************************************************************************************************");
		/*
		 * get all the company coupons by , date, price , type
		 */
		System.out.println("------------------------------------------------------------9th test------------------------------------------------------------");
		System.out.println("------------------------------------------------------------get company Coupons By ------------------------------------------------------------");
		companyFacade = (CompanyFacade) couponSystem.login("microsoft", "123", ClientType.COMPANY);
		new getCompaniesCouponsByTest(companyFacade, converter.stringToDate("19-02-03"), 31, CouponType.FOOD);
		System.out.println("------------------------------------------------------------finish get company Coupons By------------------------------------------------------------");

		
		/*
		 * get all the customer coupons by , price , type
		 */
		System.out.println("------------------------------------------------------------10th test------------------------------------------------------------");
		System.out.println("------------------------------------------------------------get customer purchased Coupons By ------------------------------------------------------------");
		customerFacade = (CustomerFacade) couponSystem.login("monder", "123", ClientType.CUSTOMER);
		new getCustomersCouponsByTest(customerFacade, 19, CouponType.CAMPING);
		System.out.println("------------------------------------------------------------finish get customer purchased Coupons By------------------------------------------------------------");

		/*
		 * remove customer from the database
		 * removing majd customer
		 * should remove the customer and the coupons that he purchased
		 */
		System.out.println("------------------------------------------------------------11th test------------------------------------------------------------");
		System.out.println("------------------------------------------------------------remove customer ------------------------------------------------------------");
		new removeCustomersTest(adminFacade);
		System.out.println("------------------------------------------------------------finish remove customer------------------------------------------------------------");

		/*
		 * remove company from the database
		 * removing Microsoft company
		 * should remove the company and the coupons that he made , and coupons that customers used
		 */
		System.out.println("------------------------------------------------------------12th test------------------------------------------------------------");
		System.out.println("------------------------------------------------------------remove company ------------------------------------------------------------");
		new removeCompaniesTest(adminFacade);
		System.out.println("------------------------------------------------------------finish remove company------------------------------------------------------------");

		/*
		 * remove coupon from the database
		 * removing googlecoupon1
		 * should remove from coupon table , company_coupon table and customer_coupon table 
		 */
		System.out.println("------------------------------------------------------------13th test------------------------------------------------------------");
		System.out.println("------------------------------------------------------------remove coupon ------------------------------------------------------------");
		new removeCouponsTest(companyFacade, adminFacade);
		System.out.println("------------------------------------------------------------finish remove coupon------------------------------------------------------------");

		/*
		 * update coupon from the database
		 * updating googlecoupon2
		 * should update endDate to out of date and price to 11 
		 * should remove the date after updating 
		 */
		System.out.println("------------------------------------------------------------14th test------------------------------------------------------------");
		System.out.println("------------------------------------------------------------update coupon ------------------------------------------------------------");
		new updateCouponsTest(companyFacade, adminFacade);
		System.out.println("------------------------------------------------------------finish update coupon------------------------------------------------------------");

		/*
		 * removing out of date coupon by starting the thread DailyCouponExpirationTask
		 * should be done every day 
		 * in the test i change the time for 3 seconds to test it if it work 
		 *  
		 */
		System.out.println("-----------------------------------------------------------------------finish------------------------------------------------------------");

	}
}
