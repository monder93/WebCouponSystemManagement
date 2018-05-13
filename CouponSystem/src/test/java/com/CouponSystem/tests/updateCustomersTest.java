package com.CouponSystem.tests;


import com.CouponSystem.Entities.Customer;
import com.CouponSystem.Facades.AdminFacade;

public class updateCustomersTest 
{
	public updateCustomersTest(AdminFacade adminFacade)
	{
		Customer customer1 = adminFacade.getCustomer(2);
		customer1.setPassword("111");
		
		adminFacade.updateCustomer(customer1);

		Customer customer2 = adminFacade.getCustomer(3); 
		customer2.setPassword("222");
		
		adminFacade.updateCustomer(customer2);

		System.out.println("before getallcustomerstest");

		//new getAllCustomersTest(adminFacade);
		
		System.out.println("after getallcustomerstest");
		
		//customer1.setPassword("456");
		//customer2.setPassword("789");
		
		//adminFacade.updateCustomer(customer1);
		//adminFacade.updateCustomer(customer2);
	
	}
}
