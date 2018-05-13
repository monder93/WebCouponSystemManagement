package com.CouponSystem;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.CouponSystem.Entities.Coupon;
import com.CouponSystem.Entities.CouponType;
import com.CouponSystem.Exceptions.NullConnectionException;
import com.CouponSystem.Repositories.CompanyRepository;
import com.CouponSystem.Repositories.CouponRepository;
import com.CouponSystem.Repositories.CustomerRepository;
import com.CouponSystem.Services.CompanyDBDAO;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CouponSystemApplicationTests 
{

	@Autowired
	private CompanyRepository companyRepo;
	
	@Autowired
	private CouponRepository couponRepo;
	
	@Autowired
	private CustomerRepository customerRepo;
	
	@Autowired
	private CompanyDBDAO companyDBDAO;
	
	private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

	
	@Test
	public void test1() throws ClassNotFoundException, InterruptedException, SQLException, NullConnectionException, ParseException
	{

	}

}
