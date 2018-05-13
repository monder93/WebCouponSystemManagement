package com.CouponSystem;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.CouponSystem.Entities.Coupon;
import com.CouponSystem.Entities.CouponType;
import com.CouponSystem.Exceptions.DuplicateEntryException;
import com.CouponSystem.Exceptions.NullConnectionException;
import com.CouponSystem.Exceptions.UnAvailableCouponException;
import com.CouponSystem.Services.CouponDBDAO;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CouponDbdaoTest 
{

	@Autowired
	private CouponDBDAO couponDbdao;

	private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");



	@Test
	public void Tests() throws ClassNotFoundException, InterruptedException, SQLException, NullConnectionException, ParseException, DuplicateEntryException, NullPointerException, UnAvailableCouponException
	{
		Date nowDate = new Date();
		System.out.println("start date : " + nowDate);
		System.out.println("********** starting CompanyDbdao Test **********");

		//test 1
		System.out.println("==================test1createCoupon====================");
		createCouponTest(); 

		//test 2
		System.out.println("==================test2removeCoupon====================");
		removeCouponTest();

		//test 3 
		System.out.println("==================test3updateCoupon====================");
		updateCouponTest();

		//test 4
		System.out.println("==================test4getCoupon====================");
		getCouponTest();

		//test 5 
		System.out.println("==================test5getAllCoupons====================");
		getAllCouponsTest();

		//test 6 
		System.out.println("==================test6getCouponsByType====================");
		getCouponByTypeTest();

		//test 7
		System.out.println("==================test7getCouponsOutOfDate====================");
		getCouponOutOfDateTest();

	}

	public void createCouponTest() throws ParseException 
	{
		Coupon tempCoupon = new Coupon("tempCoupon", simpleDateFormat.parse("2019-01-02"), simpleDateFormat.parse("2020-05-06"), 2, CouponType.RESTURANS, "temp temp", 3, "image");

		try 
		{
			couponDbdao.createCoupon(tempCoupon);
		}
		catch (ClassNotFoundException | SQLException | InterruptedException | DuplicateEntryException
				| NullConnectionException e) 
		{
			// TODO Auto-generated catch block
			System.err.println(e.getMessage());
		}

	}


	public void removeCouponTest() throws ClassNotFoundException, InterruptedException, SQLException, ParseException, NullConnectionException 
	{

	try 
	{
		Coupon tempCoupon = couponDbdao.getCoupon(7); 
		couponDbdao.removeCoupon(tempCoupon);
	}
	catch (NullPointerException | DuplicateEntryException | UnAvailableCouponException e) 
	{
		// TODO Auto-generated catch block
		System.err.println(e.getMessage());	
	}
		

	}

	public void updateCouponTest() throws ClassNotFoundException, InterruptedException, SQLException, ParseException, NullConnectionException, UnAvailableCouponException
	{
		Coupon tempCoupon = couponDbdao.getCoupon(6); 

		tempCoupon.setEndDate(simpleDateFormat.parse("2025-02-02"));
		tempCoupon.setAmount(10);
		tempCoupon.setImage("aaaaaaa");
		tempCoupon.setPrice(50);

		couponDbdao.updateCoupon(tempCoupon);


	}

	public void getCouponTest() 
	{
		Coupon tempCoupon;
		try 
		{
			tempCoupon = couponDbdao.getCoupon(7);
			System.out.println("coupon is : ");
			System.out.println(tempCoupon);
		}
		catch (ClassNotFoundException | InterruptedException | SQLException | ParseException | NullConnectionException
				| UnAvailableCouponException e) 
		{
			// TODO Auto-generated catch block
			System.err.println(e.getMessage());
		}



	}

	public void getAllCouponsTest() throws ClassNotFoundException, InterruptedException, SQLException, ParseException, NullConnectionException, UnAvailableCouponException
	{
		List<Coupon> AllCoupons = (List<Coupon>) couponDbdao.getAllCoupons();
		System.out.println("all coupons size : " + AllCoupons.size());
		System.out.println(AllCoupons);
	}

	public void getCouponByTypeTest() throws ClassNotFoundException, InterruptedException, SQLException, ParseException, NullConnectionException
	{
		List<Coupon> AllCoupons = (List<Coupon>) couponDbdao.getCouponByType(CouponType.RESTURANS);
		System.out.println(AllCoupons);
	}

	public void getCouponOutOfDateTest() throws ClassNotFoundException, InterruptedException, SQLException, ParseException, NullConnectionException
	{
		List<Coupon> AllCoupons = (List<Coupon>) couponDbdao.getCouponOutOfDate();
		System.out.println("out of date coupons ");
		System.out.println(AllCoupons);
	}
}
