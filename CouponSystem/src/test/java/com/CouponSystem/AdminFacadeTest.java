package com.CouponSystem;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.CouponSystem.Entities.ClientType;
import com.CouponSystem.Entities.Company;
import com.CouponSystem.Exceptions.NullConnectionException;
import com.CouponSystem.Facades.AdminFacade;
import com.CouponSystem.Facades.CouponClientFacade;
import com.CouponSystem.Facades.CouponSystem;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AdminFacadeTest 
{

	@Autowired
	private AdminFacade adminFacade;
	
	@Autowired
	private CouponSystem couponSystem;
	
	CouponClientFacade c = null;

	private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");


	@Test
	public void Tests() throws ClassNotFoundException, InterruptedException, SQLException, NullConnectionException, ParseException
	{
		LoginTest();
		getCompanyTest();
	}


	public void LoginTest() throws ClassNotFoundException, InterruptedException, SQLException, NullConnectionException, ParseException
	{
		String username = "admin";
		String password = "1234";

		adminFacade = (AdminFacade) couponSystem.login(username, password, ClientType.ADMIN);
		
		System.out.println("end of loginTest");
	}

	//get current company 
	public void getCompanyTest() throws ClassNotFoundException, InterruptedException, SQLException, NullConnectionException, ParseException
	{
		Company company = adminFacade.getCompany(2);
		System.out.println(company);
	}

}
