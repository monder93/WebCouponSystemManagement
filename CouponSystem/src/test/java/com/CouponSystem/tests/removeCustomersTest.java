package com.CouponSystem.tests;

import com.CouponSystem.Entities.Customer;
import com.CouponSystem.Facades.AdminFacade;

public class removeCustomersTest 
{
	public removeCustomersTest(AdminFacade adminFacade)
	{
		//getting customer from the database
		Customer customer = adminFacade.getCustomer(3);
		
		//removing from the database
		adminFacade.removeCustomer(customer);
		
		//get all the customers 
		//new getAllCustomersTest(adminFacade);
		getAllCustomersTest.getAllCustomersTest1(adminFacade);
	}
}
