package com.CouponSystem.tempTests;

import java.sql.SQLException;
import java.text.ParseException;
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
import com.CouponSystem.Exceptions.WrongDataInputException;
import com.CouponSystem.Services.CompanyDBDAO;


@RunWith(SpringRunner.class)
@SpringBootTest
public class findCouponsByType
{
	
	@Autowired
	private CompanyDBDAO companyDBDAO;

	@Test
	public void test() throws ParseException, WrongDataInputException 
	{
		try 
		{
			companyDBDAO.login("intel","123");
			Collection<Coupon>  c = companyDBDAO.getCompanyCouponByType(CouponType.FOOD);
			System.out.println(c.getClass());
			System.out.println(c);
		} 
		catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NullConnectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
