package com.CouponSystem.tests;

import com.CouponSystem.Entities.Company;
import com.CouponSystem.Facades.AdminFacade;

public class createCompaniesTest 
{
	public createCompaniesTest(AdminFacade adminFacade) throws Exception
	{

		Company company1 = new Company("microsoft","123","microsoft@hotmail.com"); // id=1
		Company company2 = new Company("google","456","google@gmail.com"); // id=2
		Company company3 = new Company("intel","789","intel@intel.com"); // id=3

		System.out.println("------------------------------------------------------------first time creating companies------------------------------------------------------------");
		adminFacade.createCompany(company1);
		adminFacade.createCompany(company2);
		adminFacade.createCompany(company3);

		System.out.println("------------------------------------------------------------creating exist company------------------------------------------------------------");
		adminFacade.createCompany(company1);
	}
}
