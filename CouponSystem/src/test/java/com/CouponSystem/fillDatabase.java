package com.CouponSystem;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.CouponSystem.Entities.Company;
import com.CouponSystem.Entities.Coupon;
import com.CouponSystem.Entities.CouponType;
import com.CouponSystem.Entities.Customer;
import com.CouponSystem.Repositories.CompanyRepository;
import com.CouponSystem.Repositories.CouponRepository;
import com.CouponSystem.Repositories.CustomerRepository;


@RunWith(SpringRunner.class)
@SpringBootTest
public class fillDatabase
{
	
	@Autowired
	private CompanyRepository companyRepo;
	
	@Autowired
	private CouponRepository couponRepo;
	
	@Autowired
	private CustomerRepository customerRepo;
	
	private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");


	@Test
	public void test() throws ParseException 
	{
			//creating 3 companies
			Company Intelcomp = new Company("intel", "123", "intel@intel.com");
			Company Googlecomp = new Company("google", "456", "google@google.com");
			Company Ibmcomp = new Company("ibm", "789", "ibm@ibm.com");

			//creating 2 coupons for each company 
			//intel coupons 
			Coupon coup1 = new Coupon("Intel-coupon1",  simpleDateFormat.parse("2017-12-11"), simpleDateFormat.parse("2018-12-15"), 5, CouponType.FOOD, "intel coupon1", 30, "1");
			Coupon coup2 = new Coupon("Intel-coupon2",  simpleDateFormat.parse("2017-12-11"), simpleDateFormat.parse("2018-12-15"), 5, CouponType.FOOD, "intel coupon2", 30, "1");

			//google coupons 
			Coupon coup3 = new Coupon("Google-coupon1",  simpleDateFormat.parse("2017-12-11"), simpleDateFormat.parse("2018-12-15"), 5, CouponType.CAMPING, "google coupon1", 30, "1");
			Coupon coup4 = new Coupon("Google-coupon2",  simpleDateFormat.parse("2017-12-11"), simpleDateFormat.parse("2018-12-15"), 5, CouponType.CAMPING, "google coupon2", 30, "1");

			//ibm coupons 
			Coupon coup5 = new Coupon("Ibm-coupon1",  simpleDateFormat.parse("2017-12-11"), simpleDateFormat.parse("2018-12-15"), 5, CouponType.ELECTRICITY, "ibm coupon1", 30, "1");
			Coupon coup6 = new Coupon("Ibm-coupon2",  simpleDateFormat.parse("2017-12-11"), simpleDateFormat.parse("2018-12-15"), 5, CouponType.ELECTRICITY, "ibm coupon2", 30, "1");
			
			Intelcomp.addCoupon(coup1);
			Intelcomp.addCoupon(coup2);
			
			Googlecomp.addCoupon(coup3);
			Googlecomp.addCoupon(coup4);
			
			Ibmcomp.addCoupon(coup5);
			Ibmcomp.addCoupon(coup6);
			
			//save companies in the database
			companyRepo.save(Intelcomp);
			companyRepo.save(Googlecomp);
			companyRepo.save(Ibmcomp);
			
			//create 2 customers
			Customer customer1 = new Customer(1,"monder", "pass1");
			Customer customer2 = new Customer(2,"tariq", "pass2");
					
			//add 2 different coupons (for different companies made them ) to each customer

			customer1.addCoupon(couponRepo.findOne(1));
			customer1.addCoupon(couponRepo.findOne(2));
			customer1.addCoupon(couponRepo.findOne(3));

			customer2.addCoupon(couponRepo.findOne(1));
			customer2.addCoupon(couponRepo.findOne(4));
			customer2.addCoupon(couponRepo.findOne(6));
			
			//save the customer
			customerRepo.save(customer1);
			customerRepo.save(customer2);
		
	}

}
