package com.CouponSystem.tempTests;

import java.text.ParseException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit4.SpringRunner;

import com.CouponSystem.Entities.ClientType;
import com.CouponSystem.Facades.AdminFacade;
import com.CouponSystem.Facades.CompanyFacade;
import com.CouponSystem.Facades.CouponSystem;


@RunWith(SpringRunner.class)
@SpringBootTest
public class testConstructorAndAutowired
{
	private AdminFacade adminFacade ;
	
	
	private CompanyFacade companyFacade;
	
	@Autowired
	private CouponSystem couponSystem;

		
	@Test
	public void test() throws ParseException 
	{
		System.out.println("couponSystem instance : "+ couponSystem);

		System.out.println("started Test ");
		
		adminFacade = (AdminFacade) couponSystem.login("admin", "1234", ClientType.ADMIN);
		companyFacade = (CompanyFacade) couponSystem.login("google", "4561", ClientType.COMPANY);
		System.out.println("after adminFacade");
		System.out.println(adminFacade.getCompany(2));
		System.out.println("end Test");
	}
}
