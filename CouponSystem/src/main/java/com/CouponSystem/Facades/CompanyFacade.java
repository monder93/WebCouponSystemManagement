package com.CouponSystem.Facades;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.CouponSystem.Entities.ClientType;
import com.CouponSystem.Entities.Company;
import com.CouponSystem.Entities.Coupon;
import com.CouponSystem.Entities.CouponType;
import com.CouponSystem.ExceptionHandler.CouponExceptionHandler;
import com.CouponSystem.ExceptionHandler.GeneralExceptionHandler;
import com.CouponSystem.Exceptions.DuplicateEntryException;
import com.CouponSystem.Exceptions.NullConnectionException;
import com.CouponSystem.Exceptions.UnAvailableCouponException;
import com.CouponSystem.Exceptions.WrongDataInputException;
import com.CouponSystem.Services.CompanyDBDAO;
import com.CouponSystem.Services.CouponDBDAO;

/**
 * 
 * The CompanyFacade class is used by the company users of the CouponSystem.
 * It grants them access to all of the relevant methods for their uses.
 *
 */

@Service
public class CompanyFacade implements CouponClientFacade
{
	@Autowired
	private CompanyDBDAO companydbdao;

	@Autowired
	private CouponDBDAO coupondbdao;

	//----------------------------------------------------------------------------------------
	/**
	 * Receives a coupon instance and register it in the database
	 * @param coupon a coupon instance
	 */
	public void createCoupon(Coupon coupon) throws Exception
	{	
		try 
		{
			System.out.println("logged in to try");
			coupondbdao.createCoupon(coupon);
			System.out.println("after creating coupon");
			companydbdao.addCouponToCompany(coupon.getId());
			System.out.println("after adding coupon to company");
			System.out.println("company id : " + companydbdao.getCompanyId());
		}
		catch (ClassNotFoundException | SQLException | InterruptedException | DuplicateEntryException
				| NullConnectionException e) 
		{
			CouponExceptionHandler.handle(e);
			throw e;
		}
	}
	//----------------------------------------------------------------------------------------
	/**
	 * Receives a coupon instance and removes its entries from the database
	 * @param coupon a coupon instance
	 */
	public void removeCoupon(Coupon coupon)
	{

		try 
		{
			coupondbdao.removeCoupon(coupon);

		}
		catch (ClassNotFoundException | SQLException | InterruptedException | DuplicateEntryException
				| NullConnectionException | UnAvailableCouponException e)
		{
			CouponExceptionHandler.handle(e);
		}

	}
	//-----------------------------------------------------------------------------------------------------
	public void removeCoup(int id)
	{
		Coupon tempCoup = getCoupon(id);
		removeCoupon(tempCoup);
	}
	//----------------------------------------------------------------------------------------
	/**
	 * Receives a coupon instance and update its entries in the database
	 * @param coupon a coupon instance
	 */
	public void updateCoupon(Coupon coupon)
	{
		try 
		{
			coupondbdao.updateCoupon(coupon);
		}
		catch (ClassNotFoundException | InterruptedException | SQLException | ParseException
				| NullConnectionException | UnAvailableCouponException e) 
		{
			CouponExceptionHandler.handle(e);
		}
	}
	//----------------------------------------------------------------------------------------
	/**
	 * Receives a coupon' id and return an instance of that coupon from the database
	 * @param id a coupon's id
	 * @return an instance of the desired coupon from the database
	 */
	public Coupon getCoupon(int id)
	{
		Coupon tempCoupon = new Coupon();

		try 
		{
			tempCoupon = coupondbdao.getCoupon(id);
		}
		catch (SQLException | ClassNotFoundException | InterruptedException | ParseException | NullConnectionException | UnAvailableCouponException e) 
		{
			CouponExceptionHandler.handle(e);
			return null;
		}

		return tempCoupon;
	}
	//----------------------------------------------------------------------------------------
	/**
	 * getting all the coupons
	 * @return an ArrayList of all the company's coupons in the database
	 */
	public Collection<Coupon> getAllCoupon()
	{
		List<Coupon> ArrayOfCompanyCoupons = new ArrayList<>();

		try 
		{
			ArrayOfCompanyCoupons = (List<Coupon>) companydbdao.getCoupons();
			//System.out.println("size before null : " + ArrayOfCompanyCoupons.size());
			
			
			// initializing array if null
			if(ArrayOfCompanyCoupons == null)
				ArrayOfCompanyCoupons = new ArrayList<>();
			
			//avoiding the null objects in the array 
			Iterator<Coupon> couponIterator = ArrayOfCompanyCoupons.iterator();
			while(couponIterator.hasNext())
			{
				Coupon  c =  couponIterator.next();
				if(c == null)
					couponIterator.remove();
			}
			
		//System.out.println("size after null : " + ArrayOfCompanyCoupons.size());

	}
	catch (SQLException | ClassNotFoundException | InterruptedException | ParseException | NullConnectionException e) 
	{
		CouponExceptionHandler.handle(e);
	}
	return ArrayOfCompanyCoupons;
}
//----------------------------------------------------------------------------------------
/**
 * getting company coupons in the database by a given type
 * @param couponType a coupon type
 * @return an ArrayList of all the company's coupons in the database by a given type
 */
public Collection<Coupon> getCouponByType(CouponType couponType)
{
	ArrayList<Coupon> ArrayOfCompanyCouponsByType = new ArrayList<>();

	try 
	{
		ArrayOfCompanyCouponsByType = (ArrayList<Coupon>) companydbdao.getCompanyCouponByType(couponType);
	}
	catch (ClassNotFoundException | InterruptedException | SQLException | NullConnectionException
			| ParseException e)
	{
		CouponExceptionHandler.handle(e);
	}

	return ArrayOfCompanyCouponsByType;
}
//----------------------------------------------------------------------------------------
/**
 * getting company coupons in the database by a given price
 * @param price  price limit
 * @return an ArrayList of all the company's coupons in the database by a given price
 */
public Collection<Coupon> getCouponByPrice(double price)
{
	ArrayList<Coupon> ArrayOfCompanyCouponsByPrice = new ArrayList<>();

	try 
	{
		ArrayOfCompanyCouponsByPrice = (ArrayList<Coupon>) companydbdao.getCompanyCouponByPrice(price);
	} 
	catch (ClassNotFoundException | InterruptedException | SQLException | NullConnectionException
			| ParseException e) 
	{
		CouponExceptionHandler.handle(e);
	}

	return ArrayOfCompanyCouponsByPrice;
}
//----------------------------------------------------------------------------------------
/**
 * getting company coupons in the database by a given date
 * @param date limit date
 * @return an ArrayList of all the company's coupons in the database by a given date
 */
public Collection<Coupon> getCouponByDate(Date date)
{
	ArrayList<Coupon> ArrayOfCompanyCouponsByDate = new ArrayList<>();

	try
	{
		ArrayOfCompanyCouponsByDate = (ArrayList<Coupon>) companydbdao.getCompanyCouponByDate(date);
	}
	catch (ClassNotFoundException | InterruptedException | SQLException | NullConnectionException
			| ParseException e) 
	{
		CouponExceptionHandler.handle(e);
	}
	return ArrayOfCompanyCouponsByDate;
}
//----------------------------------------------------------------------------------------
/**
 * checks the database for a company entry with the given name and the given password
 * returns this if exist, else return false 
 */
@Override
public CouponClientFacade login(String name, String password, ClientType clientType)
{

	try 
	{
		if(companydbdao.login(name, password))
		{
			return this;
		}
	}
	catch (ClassNotFoundException | InterruptedException | SQLException | WrongDataInputException
			| NullConnectionException | NullPointerException e)
	{
		GeneralExceptionHandler.handle(e);
	}

	return null;
}
//----------------------------------------------------------------------------------------
/**
 * sets the id of the current user in the coupon DBDAO
 */
public void setUserId()
{
	this.companydbdao.setCompanyId(companydbdao.getCompanyId());
}
}
