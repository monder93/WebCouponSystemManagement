package com.CouponSystem.tests;

import com.CouponSystem.Entities.Company;
import com.CouponSystem.Facades.AdminFacade;

public class updateCompaniesTest 
{
	public updateCompaniesTest(AdminFacade adminFacade)
	{
		// id=2
		Company company1 = new Company("google", "456", "goog@gmail.com");
		// id=3
		Company company2 = new Company("intel", "123", "int@intel.com");
		
		adminFacade.updateCompany(company1);
		adminFacade.updateCompany(company2);
		new getAllCompaniesTest(adminFacade);
		// id=3
		company2 = new Company("intel", "789", "int@intel.com");
		adminFacade.updateCompany(company2);


	}
}
