package com.CouponSystem;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.CouponSystem.Entities.Company;
import com.CouponSystem.Entities.Coupon;
import com.CouponSystem.Entities.CouponType;
import com.CouponSystem.Exceptions.DuplicateEntryException;
import com.CouponSystem.Exceptions.NullConnectionException;
import com.CouponSystem.Exceptions.WrongDataInputException;
import com.CouponSystem.Services.CompanyDBDAO;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CompanyDbdaoTest 
{

	@Autowired
	private CompanyDBDAO companyDBDAO;

	private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");


	@Test
	public void Tests() throws ClassNotFoundException, InterruptedException, SQLException, NullConnectionException, ParseException
	{
		Date nowDate = new Date();
		System.out.println("start date : " + nowDate);
		System.out.println("********** starting CompanyDbdao Test **********");
		
		//test 1
		System.out.println("==================test1Login====================");
		LoginTest();

		//test 2
		System.out.println("==================test2CreateCompany====================");
		//CreateCompanyTest();

		//test 3
		System.out.println("==================test3removeCompanyTest====================");
		removeCompanyTest();

		//test 4
		System.out.println("==================test4updateCompany====================");
		updateCompanyTest();

		//test 5
		System.out.println("==================test5getCompany====================");
		getCompanyTest();

		//test 6
		System.out.println("==================test6getAllCompanies====================");
		getAllCompaniesTest();

		//test 7
		System.out.println("==================test7getCoupons====================");
		getCouponsTest();

		//test 8
		System.out.println("==================test8getCouponsByType====================");
		getCompanyCouponByTypeTest();

		//test 9
		System.out.println("==================test9getCouponsByPrice====================");
		getCompanyCouponByPriceTest();

		//test 10
		System.out.println("==================test10getCouponsByDate====================");
		getCompanyCouponByDateTest();

	}


	//get current company coupons
	public void LoginTest() throws ClassNotFoundException, InterruptedException, SQLException, NullConnectionException, ParseException
	{
		try 
		{
			companyDBDAO.login("intel", "123");
			//companyDBDAO.login("google", "456");
		}
		catch (WrongDataInputException e) 
		{
			System.out.println("exception in Login");
		}
	}

	public void CreateCompanyTest() throws ClassNotFoundException, InterruptedException, SQLException, NullConnectionException, ParseException
	{
		Company c = new Company("comp11","comp1pass","comp1email");
		try 
		{
			companyDBDAO.createCompany(c);
		}
		catch (DuplicateEntryException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void removeCompanyTest() throws ClassNotFoundException, InterruptedException, SQLException, NullConnectionException, ParseException
	{
		Company company = companyDBDAO.getCompany(4);
		System.out.println("company in removeCompany is : " + company);

		companyDBDAO.removeCompany(company);
	}

	public void updateCompanyTest() throws ClassNotFoundException, InterruptedException, SQLException, NullConnectionException, ParseException
	{
		Company company = companyDBDAO.getCompany(1);
		company.setEmail("intellll@in");
		companyDBDAO.updateCompany(company);
	}

	//getting id 
	public void getCompanyTest() throws ClassNotFoundException, InterruptedException, SQLException, NullConnectionException, ParseException
	{
		Company tempC = new Company();
		tempC = companyDBDAO.getCompany(1);
		System.out.println(tempC);

	}

	public void getAllCompaniesTest() throws ClassNotFoundException, InterruptedException, SQLException, NullConnectionException, ParseException
	{
		ArrayList<Company> allCompanies = (ArrayList<Company>) companyDBDAO.getAllCompanies();
		System.out.println("allCompanies size : "+allCompanies.size());
		System.out.println("allCompanies : " + allCompanies);
	}

	public void getCouponsTest() throws ClassNotFoundException, InterruptedException, SQLException, NullConnectionException, ParseException
	{
		System.out.println(companyDBDAO.getCoupons());
	}

	public void getCouponsByCompanyIdTest() throws ClassNotFoundException, InterruptedException, SQLException, NullConnectionException, ParseException
	{
		Collection<Coupon> c =   companyDBDAO.getCouponsByCompanyId(1);
		System.out.println(c);
	}

	public void getCompanyCouponByTypeTest() throws ClassNotFoundException, InterruptedException, SQLException, NullConnectionException, ParseException
	{
		ArrayList<Coupon> c = (ArrayList<Coupon>) companyDBDAO.getCompanyCouponByType(CouponType.FOOD);
		System.out.println(c);
	}

	public void getCompanyCouponByPriceTest() throws ClassNotFoundException, InterruptedException, SQLException, NullConnectionException, ParseException
	{
		ArrayList<Coupon> c = (ArrayList<Coupon>) companyDBDAO.getCompanyCouponByPrice(30);
		System.out.println(c);
	}

	public void getCompanyCouponByDateTest() throws ClassNotFoundException, InterruptedException, SQLException, NullConnectionException, ParseException
	{
		ArrayList<Coupon> c = (ArrayList<Coupon>) companyDBDAO.getCompanyCouponByDate(simpleDateFormat.parse("2018-12-15"));
		System.out.println(c);
	}

}
