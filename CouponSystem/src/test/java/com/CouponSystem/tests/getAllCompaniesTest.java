package com.CouponSystem.tests;



import java.util.Collection;

import com.CouponSystem.Entities.Company;
import com.CouponSystem.Facades.AdminFacade;

public class getAllCompaniesTest
{
	public getAllCompaniesTest(AdminFacade adminFacade)
	{
		Collection<Company> Array_of_companies = adminFacade.getAllCompanies();
		
		for (Company c : Array_of_companies)
		{
			System.out.println(c);
		}
	}
}
