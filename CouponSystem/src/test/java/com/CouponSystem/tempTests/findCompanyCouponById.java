package com.CouponSystem.tempTests;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.CouponSystem.Entities.Company;
import com.CouponSystem.Entities.Coupon;
import com.CouponSystem.Exceptions.NullConnectionException;
import com.CouponSystem.Repositories.CompanyRepository;
import com.CouponSystem.Repositories.CouponRepository;
import com.CouponSystem.Repositories.CustomerRepository;
import com.CouponSystem.Services.CompanyDBDAO;


@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration
public class findCompanyCouponById
{
	
	@Autowired
	private CompanyRepository companyRepo;
	
	@Autowired
	private CouponRepository couponRepo;
	
	@Autowired
	private CustomerRepository customerRepo;
	
	private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");


	@Test
	public void test() throws ParseException, ClassNotFoundException, InterruptedException, SQLException, NullConnectionException 
	{
	//	CompanyDBDAO c = new CompanyDBDAO(1);
		//Company c1 = companyRepo.findOne(1);
		//System.out.println(c1.getCoupons());

//		Collection<Coupon> coupons =  c1.getCoupons();
	//	Collection<Coupon> coupons =  c.getCoupons();

		//System.out.println("coupons size is : " + coupons.size());
	//	System.out.println(coupons);
		//ArrayList<Coupon> coupons = (ArrayList<Coupon>) c.getCouponsByCompanyId(1);
		//System.out.println(coupons);
	}

}
