package com.CouponSystem.tests;

import java.util.Collection;

import com.CouponSystem.Entities.Customer;
import com.CouponSystem.Facades.AdminFacade;

public class getAllCustomersTest 
{
	public static void getAllCustomersTest1(AdminFacade adminFacade)
	{
		Collection<Customer> Array_of_customers = adminFacade.getAllCustomer();
		
		for (Customer c : Array_of_customers)
		{
			System.out.println(c);
		}
	}
}
