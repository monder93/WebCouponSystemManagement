package com.CouponSystem.tests;

import com.CouponSystem.Entities.Customer;
import com.CouponSystem.Facades.AdminFacade;

public class createCustomersTest 
{
	public createCustomersTest(AdminFacade adminFacade) throws Exception
	{		
		Customer customer1 = new Customer("monder","123"); // id=1
		Customer customer2 = new Customer("tariq","456"); // id=2
		Customer customer3 = new Customer("majd","789"); // id=3
		
		System.out.println("------------------------------------------------------------first time creating customers------------------------------------------------------------");
		adminFacade.createCustomer(customer1);
		adminFacade.createCustomer(customer2);
		adminFacade.createCustomer(customer3);
		
		System.out.println("------------------------------------------------------------creating exist customer------------------------------------------------------------");
		adminFacade.createCustomer(customer1);
		
		System.out.println("------------------------------------------------------------finish creating customers------------------------------------------------------------");
	}

}
