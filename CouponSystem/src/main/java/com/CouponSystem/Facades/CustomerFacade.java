package com.CouponSystem.Facades;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.CouponSystem.Entities.ClientType;
import com.CouponSystem.Entities.Coupon;
import com.CouponSystem.Entities.CouponType;
import com.CouponSystem.ExceptionHandler.CouponExceptionHandler;
import com.CouponSystem.ExceptionHandler.CustomerExceptionHandler;
import com.CouponSystem.ExceptionHandler.GeneralExceptionHandler;
import com.CouponSystem.Exceptions.NullConnectionException;
import com.CouponSystem.Exceptions.UnAvailableCouponException;
import com.CouponSystem.Exceptions.WrongDataInputException;
import com.CouponSystem.Services.CustomerDBDAO;

/**
 * 
 * The CustomerFacade class is used by the customer users of the CouponSystem.
 * It grants them access to all of the relevant methods for their uses.
 *
 */
@Service
public class CustomerFacade implements CouponClientFacade
{
	@Autowired
	private CustomerDBDAO customerdbdao;

	//----------------------------------------------------------------------------------------
	/**
	 * Receives a coupon instance and updates it's purchase in the database
	 * @param coupon a coupon instance
	 * @throws Exception 
	 */
	public void purchaseCoupon(Coupon coupon) throws Exception
	{
		try 
		{
			customerdbdao.purchaseCoupon(coupon);
		}
		catch (ClassNotFoundException | InterruptedException | SQLException | NullConnectionException| UnAvailableCouponException | NullPointerException e) 
		{
			CouponExceptionHandler.handle(e);
			throw e;
		}
	}
	//----------------------------------------------------------------------------------------
	/**
	 * getting all the purchased coupons for the current customer
	 * @return an ArrayList of all the current customer's purchased coupons
	 * @throws  
	 */
	public Collection<Coupon> getAllPurchasedCoupons() 
	{

			List<Coupon> allCustomerCoupons = null;

			try 
			{
				allCustomerCoupons =  (List<Coupon>) customerdbdao.getCoupons();
				
				// initializing array if null
				if(allCustomerCoupons == null)
					allCustomerCoupons= new ArrayList<>();
				
				//avoiding the null objects in the array 
				Iterator<Coupon> couponIterator = allCustomerCoupons.iterator();
				while(couponIterator.hasNext())
				{
					Coupon  c =  couponIterator.next();
					if(c == null)
						couponIterator.remove();
				}
				
			} 
			catch (NullPointerException| ClassNotFoundException | InterruptedException | NullConnectionException | SQLException e)
			{
				CustomerExceptionHandler.handle(e);
			}

			return allCustomerCoupons;

	}
	//----------------------------------------------------------------------------------------
	/**
	 * getting all the coupons for all the companies
	 * @return an ArrayList of all the coupons
	 * @throws  
	 */
	public Collection<Coupon> getAllCoupons() 
	{

			List<Coupon> allCoupons = null;

			try 
			{
				allCoupons =  (List<Coupon>) customerdbdao.getAllCoupons();
				
				// initializing array if null
				if(allCoupons == null)
					allCoupons = new ArrayList<>();
				
				//avoiding the null objects in the array 
				Iterator<Coupon> couponIterator = allCoupons.iterator();
				while(couponIterator.hasNext())
				{
					Coupon  c =  couponIterator.next();
					if(c == null)
						couponIterator.remove();
				}
				
				
			} 
			catch (NullPointerException| ClassNotFoundException | InterruptedException | NullConnectionException | SQLException e)
			{
				CustomerExceptionHandler.handle(e);
			}

			return allCoupons;

	}
	
	//----------------------------------------------------------------------------------------
	/**
	 * getting all the purchased coupons for the current customer by type
	 * @return an ArrayList of all the current customer's purchased coupons by type
	 */
	public Collection<Coupon> getAllPurchasedCouponsByType(CouponType coupontype) 
	{
		ArrayList<Coupon> allCustomerCoupons = new ArrayList<>();

		try 
		{
			allCustomerCoupons = (ArrayList<Coupon>) customerdbdao.getCouponsByType(coupontype);
			
			// initializing array if null
			if(allCustomerCoupons == null)
				allCustomerCoupons = new ArrayList<>();
			
			//avoiding the null objects in the array 
			Iterator<Coupon> couponIterator = allCustomerCoupons.iterator();
			while(couponIterator.hasNext())
			{
				Coupon  c =  couponIterator.next();
				if(c == null)
					couponIterator.remove();
			}
			
		} 
		catch (ClassNotFoundException | InterruptedException | NullConnectionException | SQLException e)
		{
			CustomerExceptionHandler.handle(e);
		}

		return allCustomerCoupons;
	}
	//----------------------------------------------------------------------------------------
	/**
	 * getting all the purchased coupons for the current customer by price
	 * @return an ArrayList of all the current customer's purchased coupons by price
	 */
	public Collection<Coupon> getAllPurchasedCouponsByPrice(double price) 
	{
		ArrayList<Coupon> allCustomerCoupons = new ArrayList<>();

		try 
		{
			allCustomerCoupons = (ArrayList<Coupon>) customerdbdao.getCouponsByPrice(price);
			
			// initializing array if null
			if(allCustomerCoupons == null)
				allCustomerCoupons = new ArrayList<>();
			
			//avoiding the null objects in the array 
			Iterator<Coupon> couponIterator = allCustomerCoupons.iterator();
			while(couponIterator.hasNext())
			{
				Coupon  c =  couponIterator.next();
				if(c == null)
					couponIterator.remove();
			}
		}
		catch (ClassNotFoundException | InterruptedException | NullConnectionException | SQLException e) 
		{
			CustomerExceptionHandler.handle(e);
		}

		return allCustomerCoupons;
	}

	//----------------------------------------------------------------------------------------
	/**
	 * checks the database for a customer entry with the given name and the given password
	 * returns this instance if exist , else return null
	 */
	@Override
	public CouponClientFacade login(String name, String password, ClientType clientType)
	{

		try
		{
			if(customerdbdao.login(name, password))
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
}
