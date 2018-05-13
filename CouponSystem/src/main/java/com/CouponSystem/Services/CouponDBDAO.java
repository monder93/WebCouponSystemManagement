package com.CouponSystem.Services;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.CouponSystem.DAO.CouponDAO;
import com.CouponSystem.Entities.Coupon;
import com.CouponSystem.Entities.CouponType;
import com.CouponSystem.Exceptions.DuplicateEntryException;
import com.CouponSystem.Exceptions.NullConnectionException;
import com.CouponSystem.Exceptions.UnAvailableCouponException;
import com.CouponSystem.Repositories.CouponRepository;
/**
 * this class implements a Coupon data access object to perform operations with objects and database 
 * Receives data from the database to the user || write data received from the user to the database 
 * @author monder
 * @version 1.0
 */
@Service
public class CouponDBDAO implements CouponDAO
{

	@Autowired
	private CouponRepository couponRepo;

	//-----------------------------------------------------------------------------------------------------
	/**
	 * getting as a parameter coupon and adding it to the database if not exist
	 * @param coupon instance object of a coupon 
	 */
	@Override
	public void createCoupon(Coupon coupon) throws SQLException, ClassNotFoundException, InterruptedException, DuplicateEntryException, NullConnectionException 
	{
		Coupon tempCoupon=null;
		tempCoupon = couponRepo.findByTitle(coupon.getTitle());

		if( tempCoupon != null ) 
		{
			throw new DuplicateEntryException("the user tried to create a coupon with a title that already exist in the database");
		}
		else
		{
			couponRepo.save(coupon);
			System.out.println("coupon created ");
			//setting the generated id in the parameter coupon object inside insertCouponToDatebase
		}
	}
	//-----------------------------------------------------------------------------------------------------
	/**
	 * getting as a parameter coupon and removing it from the database if exist
	 * @param coupon instance object of a coupon
	 */
	@Override
	public void removeCoupon(Coupon coupon) throws SQLException, ClassNotFoundException, InterruptedException, DuplicateEntryException, NullConnectionException,UnAvailableCouponException,NullPointerException
	{
		Coupon tempCoupon=null;
		tempCoupon = couponRepo.findByTitle(coupon.getTitle());
		
		if(tempCoupon!=null)
		{
			//deleting the coupon from coupon table
			//deleting the coupon from company_coupon table
			//deleting the coupon from customer_coupon table
			
			couponRepo.delete(coupon);
			System.out.println("coupon removed successfully");
		}
		else
		{
			throw new UnAvailableCouponException("coupon not exist - cant remove");
		}
	}
	//-----------------------------------------------------------------------------------------------------
	/**
	 * getting as a parameter coupon and updating it in the database if exist
	 * @param coupon instance object of a coupon
	 * @throws UnAvailableCouponException 
	 */
	@Override
	public void updateCoupon(Coupon coupon) throws ClassNotFoundException, InterruptedException, SQLException, ParseException, NullConnectionException, UnAvailableCouponException 
	{
		Coupon tempCoupon=null;
		tempCoupon = couponRepo.findByTitle(coupon.getTitle());
		
		if(tempCoupon!=null)
		{
			//getting the real coupon from the database , and set the new endDate and Price
			tempCoupon.setEndDate(coupon.getEndDate());
			tempCoupon.setPrice(coupon.getPrice());
			
			couponRepo.save(tempCoupon);
			System.out.println("coupon has been updated!");
		}
		else
		{
			throw new UnAvailableCouponException("coupon not exist - cant update");
		}
	}
	//-----------------------------------------------------------------------------------------------------
	/**
	 * getting as a parameter int id  and retrieve Coupon data from  the database if exist
	 * @param id id of a coupon
	 * @return returns a coupon object 
	 */
	@Override
	public Coupon getCoupon(int id) throws ClassNotFoundException, InterruptedException, SQLException, ParseException, NullConnectionException , UnAvailableCouponException
	{
		Coupon tempCoupon;
		tempCoupon = couponRepo.findOne(id);
		if(tempCoupon == null)
		{
			throw new UnAvailableCouponException("no coupon found in the database with the given id ");
		}
		return tempCoupon;
	}
	//-----------------------------------------------------------------------------------------------------
	/**
	 * getting all the coupon from the database
	 * @return return a collection<Coupon> with all the coupons inside it 
	 * @throws UnAvailableCouponException 
	 */
	@Override
	public Collection<Coupon> getAllCoupons() throws ClassNotFoundException, InterruptedException, SQLException, ParseException, NullConnectionException, UnAvailableCouponException
	{	
		//initialize the List to null
		ArrayList<Coupon> AllCoupons = null;
		
		//getting all coupons inside the List
		AllCoupons =  (ArrayList<Coupon>) couponRepo.findAll();
		
		//returning the List
		return AllCoupons;
	}
	//-----------------------------------------------------------------------------------------------------
	/**
	 * getting coupon from the database by type
	 * @return return a collection<Coupon> with all the coupons inside it with specific type 
	 */
	@Override
	public Collection<Coupon> getCouponByType(CouponType couponType) throws ClassNotFoundException, InterruptedException, SQLException, ParseException, NullConnectionException
	{
		//initialize the List to null
		List<Coupon> AllCouponsByType = null;
		
		//getting all coupons inside the List
		AllCouponsByType =  (List<Coupon>) couponRepo.findByType(couponType);
		
		//returning the List
		return AllCouponsByType;
	}

	//---------------------------------------------------------------------------------------------
	/**
	 * getting all the coupons out of date
	 * @return return collection<coupon> of the out dated coupons
	 */
	@Override
	public Collection<Coupon> getCouponOutOfDate() throws ClassNotFoundException, InterruptedException, SQLException, ParseException, NullConnectionException 
	{
		//initialize the List to null
		List<Coupon> AllCouponsOutOfDate = null;
		
		//getting all coupons inside the List
		AllCouponsOutOfDate =  (List<Coupon>) couponRepo.findByEndDateBefore(new Date());
		
		System.out.println(AllCouponsOutOfDate.getClass());
		//returning the List
		return AllCouponsOutOfDate;	}


}
