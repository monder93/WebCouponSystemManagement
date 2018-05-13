package com.CouponSystem;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.CouponSystem.Entities.Coupon;
import com.CouponSystem.Entities.CouponType;
import com.CouponSystem.Entities.Customer;
import com.CouponSystem.Exceptions.DuplicateEntryException;
import com.CouponSystem.Exceptions.NullConnectionException;
import com.CouponSystem.Exceptions.UnAvailableCouponException;
import com.CouponSystem.Exceptions.WrongDataInputException;
import com.CouponSystem.Repositories.CustomerRepository;
import com.CouponSystem.Services.CouponDBDAO;
import com.CouponSystem.Services.CustomerDBDAO;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CustomerDbdaoTest 
{

	@Autowired
	private CustomerDBDAO customerDbdao;
	
	@Autowired
	private CouponDBDAO couponDbdao;

	@Autowired
	private CustomerRepository customerRepo;
	
	@Test
	public void Tests() throws ClassNotFoundException, InterruptedException, SQLException, NullConnectionException, ParseException, DuplicateEntryException, NullPointerException, UnAvailableCouponException
	{
		Date nowDate = new Date();
		System.out.println("start date : " + nowDate);
		System.out.println("********** starting CustomerDbdao Test **********");

		//login to use monder 
		loginTest();
		System.out.println("--------------------logged id to monder user -------------------");
		
		//test 1
		System.out.println("==================test1createCustomer====================");
		createCustomerTest(); 

		//test 2
		System.out.println("==================test2removeCustomer====================");
		removeCustomerTest();

		//test 3 
		System.out.println("==================test3updateCustomer====================");
		updateCustomerTest();

		//test 4
		System.out.println("==================test4getCustomer====================");
		getCustomerTest();

		//test 5 
		System.out.println("==================test5getAllCustomers====================");
		getAllCustomerTest();

		//test 6 
		System.out.println("==================test6getCoupons====================");
		getCouponsTest();

		//test 7
		System.out.println("==================test7getCouponsByCustomerId====================");
		getCouponsByCustomerIdTest();

		//test 8
		System.out.println("==================test8getCouponsByType====================");
		getCouponsByTypeTest();

		//test 9 
		System.out.println("==================test9getCouponsByPrice====================");
		getCouponsByPriceTest();

		//test 10
		System.out.println("==================test10purchaesCoupon====================");
		purchaseCouponTest();
		
		//test 11 
		System.out.println("==================test11QueryTest====================");
		QueryTest();
		
		//test 12
		System.out.println("==================test12QueryTest2====================");
		QueryTest2();
	}

	public void createCustomerTest() throws ParseException 
	{

		Customer tempCustomer = new Customer ("newCustomer1","pass1");
		try 
		{
			customerDbdao.createCustomer(tempCustomer);
		}
		catch (ClassNotFoundException | InterruptedException | SQLException | DuplicateEntryException
				| NullConnectionException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
	}


	public void removeCustomerTest() 
	{
		Customer tempCustomer;
		try 
		{
			tempCustomer = customerDbdao.getCustomer(2);
			customerDbdao.removeCustomer(tempCustomer);
		} 
		catch (ClassNotFoundException | InterruptedException | SQLException | NullConnectionException e) 
		{
			System.out.println(e.getMessage());
		}
	}

	public void updateCustomerTest() throws ClassNotFoundException, InterruptedException, SQLException, ParseException, NullConnectionException, UnAvailableCouponException
	{
		Customer tempCustomer = customerDbdao.getCustomer(1);
		tempCustomer.setCustName("monderChanged");
		tempCustomer.setPassword("pass1");
		customerDbdao.updateCustomer(tempCustomer);
	}

	public void getCustomerTest() 
	{
		Customer tempCustomer;
		try {
			tempCustomer = customerDbdao.getCustomer(1);
			System.out.println("get user info : "+tempCustomer);
		} 
		catch (ClassNotFoundException | InterruptedException | SQLException | NullConnectionException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void getAllCustomerTest() throws ClassNotFoundException, InterruptedException, SQLException, ParseException, NullConnectionException, UnAvailableCouponException
	{
		List<Customer> allCustomers = null;
		allCustomers = (List<Customer>) customerDbdao.getAllCustomer();
		System.out.println("all customers : " + allCustomers);
	}

	public void getCouponsTest() throws ClassNotFoundException, InterruptedException, SQLException, ParseException, NullConnectionException
	{
		List<Coupon> allCouponsOfCustomer = null;
		allCouponsOfCustomer = (List<Coupon>) customerDbdao.getCoupons();
		System.out.println("getCoupons : " + allCouponsOfCustomer);
	}

	public void loginTest() throws ClassNotFoundException, InterruptedException, SQLException, ParseException, NullConnectionException
	{
		try 
		{
			customerDbdao.login("monder", "pass1");
		}
		catch (WrongDataInputException e) 
		{
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
	}

	public void getCouponsByCustomerIdTest()
	{
		List<Coupon> customer1Coupons = null;
		try 
		{
			customer1Coupons = (List<Coupon>) customerDbdao.getCouponsByCustomerId(1);
			System.out.println("customer 1 coupons : " + customer1Coupons);
		} 
		catch (ClassNotFoundException | NullPointerException | InterruptedException | SQLException
				| NullConnectionException e) 
		{
			System.out.println(e.getMessage());
		}
	}

	public void getCouponsByTypeTest()
	{
		List<Coupon> CouponsByType = null;
		try 
		{
			CouponsByType = (List<Coupon>) customerDbdao.getCouponsByType(CouponType.FOOD);
			System.out.println("Coupons By Type FOOD : " + CouponsByType);
		} 
		catch (ClassNotFoundException | InterruptedException | SQLException | NullConnectionException e)
		{
			System.out.println(e.getMessage());
		}
		
	}

	public void getCouponsByPriceTest()
	{
		List<Coupon> CouponsByPrice = null;
		try 
		{
			CouponsByPrice = (List<Coupon>) customerDbdao.getCouponsByPrice(40);
			System.out.println("Coupons By Price 40 : " + CouponsByPrice);
		} 
		catch (ClassNotFoundException | InterruptedException | SQLException | NullConnectionException e)
		{
			System.out.println(e.getMessage());
		}
	}

	public void purchaseCouponTest()
	{
		Coupon tempCoupon = null;
		try
		{
			tempCoupon = couponDbdao.getCoupon(6);
			customerDbdao.purchaseCoupon(tempCoupon);
			System.out.println("customer 1 coupons  : " + customerDbdao.getCoupons());
		} 
		catch (ClassNotFoundException | InterruptedException | SQLException | ParseException | NullConnectionException
				| UnAvailableCouponException e) 
		{
			System.out.println(e.getMessage());
		}
	}

	public void QueryTest()
	{
		Coupon tempCoupon = null;
		Customer found ;
		try
		{
			tempCoupon = couponDbdao.getCoupon(4);
			found = customerRepo.findByCouponsTitleContaining(tempCoupon.getTitle());
			
			System.out.println("found is : " + found);
		} 
		catch (ClassNotFoundException | InterruptedException | SQLException | ParseException | NullConnectionException
				| UnAvailableCouponException e) 
		{
			System.out.println(e.getMessage());
		}
	}
	
	public void QueryTest2()
	{
		Coupon tempCoupon = null;
		Customer found ;
		try
		{
			tempCoupon = couponDbdao.getCoupon(4);
			found = customerRepo.findByCouponsContaining(tempCoupon);
			
			System.out.println("found is : " + found);
		} 
		catch (ClassNotFoundException | InterruptedException | SQLException | ParseException | NullConnectionException
				| UnAvailableCouponException e) 
		{
			System.out.println(e.getMessage());
		}
	}
	
}
