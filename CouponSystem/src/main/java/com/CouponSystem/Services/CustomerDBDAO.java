package com.CouponSystem.Services;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.CouponSystem.DAO.CustomerDAO;
import com.CouponSystem.Entities.Coupon;
import com.CouponSystem.Entities.CouponType;
import com.CouponSystem.Entities.Customer;
import com.CouponSystem.Exceptions.DuplicateEntryException;
import com.CouponSystem.Exceptions.NullConnectionException;
import com.CouponSystem.Exceptions.UnAvailableCouponException;
import com.CouponSystem.Exceptions.WrongDataInputException;
import com.CouponSystem.Repositories.CouponRepository;
import com.CouponSystem.Repositories.CustomerRepository;


/**
 * this class implements a Customer data access object to perform operations with objects and database 
 * Receives data from the database to the user || write data received from the user to the database 
 * @author monder
 * @version 1.0
 */
@Service
public class CustomerDBDAO implements CustomerDAO
{
	@Autowired
	private CustomerRepository customerRepo;

	@Autowired
	private CouponRepository couponRepo;

	//for testing facades and restcontroller , need to reset without value
	//private int customerId=1;
	//private int customerId=2;

	private int customerId;


	//-----------------------------------------------------------------------------------------------------
	/**
	 * getting as a parameter customer and adding it to the database if not exist
	 * @param customer instance object of a customer 
	 */
	@Override
	public void createCustomer(Customer customer) throws ClassNotFoundException, InterruptedException, SQLException, DuplicateEntryException, NullConnectionException 
	{
		Customer tempCustomer = null;
		tempCustomer = customerRepo.findByCustName(customer.getCustName());

		//check if customer exist 
		if (tempCustomer != null)
		{
			throw new DuplicateEntryException("the admin tried to create a customer with a name and password that already exist in the database");
		}
		else
		{
			customerRepo.save(customer);
		}

	}
	//-----------------------------------------------------------------------------------------------------
	/**
	 * getting as a parameter customer and removing it from the database if exist
	 * @param customer instance object of a customer
	 */
	@Override
	public void removeCustomer(Customer customer) throws ClassNotFoundException, InterruptedException, SQLException, NullConnectionException 
	{
		if(customer != null)	
		{
			if(customerRepo.exists(customer.getId()))
			{
				customerRepo.delete(customer);
				System.out.println("customer  : " + customer.getCustName() + " removerd successfull");		    		
			}
			else
			{
				System.out.println("customer : not exist in the database");
			}
		}
		else
		{
			System.out.println("customer parameter is null");
		}
	}	
	//-----------------------------------------------------------------------------------------------------
	/**
	 * getting as a parameter customer and updating it in the database if exist
	 * @param customer instance object of a customer
	 */
	@Override
	public void updateCustomer(Customer customer) throws ClassNotFoundException, InterruptedException, SQLException, NullConnectionException
	{
		if(customerRepo.exists(customer.getId()))
		{
			customer.setCustName(customerRepo.findOne(customer.getId()).getCustName());
			customerRepo.save(customer);
			System.out.println("customer  : " + customer.getCustName() + " updated successfull");
			System.out.println(customerRepo.findOne(customer.getId()));
		}
		else
		{
			System.out.println("customer : not exist in the database");
		}
	}
	//----------------------------------------------------------------------------------------------------------
	/**
	 * getting as a parameter int id  and retrieve customer data from  the database if exist
	 * @param id id of a customer
	 * @return returns a customer object 
	 */
	@Override
	public Customer getCustomer(int id) throws ClassNotFoundException, InterruptedException, SQLException, NullConnectionException 
	{
		Customer tempCustomer = null;

		if(customerRepo.exists(id))
		{
			tempCustomer = customerRepo.findOne(id);
		}
		else
		{
			System.out.println("there is no customer with this id ");
		}
		return tempCustomer;
	}
	//----------------------------------------------------------------------------------------------------------
	/**
	 * getting all the customers from the database
	 * @return return a collection<Customer> with all the customers inside it 
	 */
	@Override
	public Collection<Customer> getAllCustomer() throws ClassNotFoundException, InterruptedException, SQLException, NullConnectionException 
	{
		ArrayList<Customer> tempCustomerArray = new ArrayList<>();
		tempCustomerArray = (ArrayList<Customer>) customerRepo.findAll();
		return tempCustomerArray;
	}
	//----------------------------------------------------------------------------------------------------------
	/**
	 * getting all the coupons of this customer 
	 * @return returning collection<Coupon> with all the coupons of this customer inside it 
	 */
	@Override
	public Collection<Coupon> getCoupons() throws ClassNotFoundException, InterruptedException, SQLException, NullConnectionException , NullPointerException
	{
		Customer tempCustomer = customerRepo.findOne(customerId);
		List<Coupon> AllCustomerCoupons = null;

		if(tempCustomer != null)
		{
			//System.out.println(c);
			AllCustomerCoupons = tempCustomer.getCoupons();
		}

		return AllCustomerCoupons;
	}
	//----------------------------------------------------------------------------------------------------------
	@Override
	public Collection<Coupon> getAllCoupons() throws SQLException, ClassNotFoundException, InterruptedException,
	NullConnectionException, NullPointerException 
	{
		List<Coupon> AllCustomerCoupons = null;
		AllCustomerCoupons = (List<Coupon>) couponRepo.findAll();
		
		return AllCustomerCoupons;
	}
	//----------------------------------------------------------------------------------------------------------
	/**
	 * login function to perform login operation
	 * @param custName customer name 
	 * @param password password of the customer
	 * @return returns true if custName and password is correct and they are in the database , else returns false
	 */
	@Override
	public boolean login(String custName, String password) throws ClassNotFoundException, InterruptedException, SQLException, WrongDataInputException, NullConnectionException 
	{
		Customer tempCustomer = customerRepo.findByCustNameAndPassword(custName, password); 
		if(tempCustomer==null)
		{
			return false;
		}
		setCustomerId(tempCustomer.getId());
		return true;
	}
	//---------------------------------------------------------------------------------------------------------
	/**
	 * getting coupons by customer ID
	 * @param id id of the customer 
	 * @return returning a collection<Coupon> inside it coupons of customer with the id in the parameter
	 */
	@Override
	public Collection<Coupon> getCouponsByCustomerId(int id) throws ClassNotFoundException, InterruptedException, SQLException, NullConnectionException , NullPointerException
	{
		Collection<Coupon> tempCustomerCoupons = null;

		Customer tempCustomer =  customerRepo.findOne(id);
		if(tempCustomer == null )
		{
			System.out.println("customer is null");
			return null;
		}
		tempCustomerCoupons = tempCustomer.getCoupons();
		return tempCustomerCoupons;
	}
	//-----------------------------------------------------------------------------------------------
	/**
	 * getting customer coupons by Type 
	 * @param couponType type of the coupon
	 * @return return collection<Coupon> inside it the customer coupons by specific type
	 */
	@Override
	public Collection<Coupon> getCouponsByType(CouponType couponType) throws ClassNotFoundException, InterruptedException, SQLException, NullConnectionException
	{
		ArrayList<Coupon> tempCouponsArray ;

		//coupons of this company that their id and coupon type as the parameters

		tempCouponsArray =  (ArrayList<Coupon>) customerRepo.findByCouponType(customerId,couponType);
		return tempCouponsArray;
	}
	//-----------------------------------------------------------------------------------------------
	/**
	 * getting customer coupons by price limited 
	 * @param price limit price
	 * @return return collection<Coupon> inside it the customer coupons by price limit
	 */
	@Override
	public Collection<Coupon> getCouponsByPrice(double price) throws ClassNotFoundException, InterruptedException, SQLException, NullConnectionException
	{
		ArrayList<Coupon> tempCouponsArray ;

		//coupons of this company that their id and price as the parameters

		tempCouponsArray =  (ArrayList<Coupon>) customerRepo.findByCouponPrice(customerId,price);
		return tempCouponsArray;
	}
	//-----------------------------------------------------------------------------------------------
	/**
	 * 
	 * @return customer id 
	 */
	public int getCustomerId() 
	{
		return customerId;
	}
	//-----------------------------------------------------------------------------------------------
	/**
	 * setting id to this customer
	 * @param customerId customer id
	 */
	public void setCustomerId(int customerId) 
	{
		this.customerId = customerId;
	}
	//-----------------------------------------------------------------------------------------------
	/**
	 * buying coupon and adding it to the customer
	 * @param coupon
	 */
	@Override
	public void purchaseCoupon(Coupon coupon) throws ClassNotFoundException, InterruptedException, SQLException, NullConnectionException,UnAvailableCouponException
	{		
		boolean isValidAmount = isValidAmount(coupon);
		boolean isValidDate = isValidDate(coupon);
		boolean isFirstBuy = isFirstBuy(coupon);

		if(isValidAmount && isValidDate && isFirstBuy)
		{
			//updating the amount of the coupon 
			coupon.setAmount(coupon.getAmount()-1);

			//saving the coupon
			couponRepo.save(coupon);

			//getting the current customer
			Customer tempCustomer = getCustomer(customerId);

			//adding the coupon to customer
			tempCustomer.addCoupon(coupon);

			//saving the customer , automatic save the purchased coupon in the join table
			customerRepo.save(tempCustomer);

			System.out.println("customer purchased coupon : " +coupon );
		}
		else
		{
			throw new UnAvailableCouponException("customer tried to purchse a coupon that is either out of date , purchased in the past , or it's available amount is 0");
		}
	}
	//-----------------------------------------------------------------------------------------------
	/**
	 * checking if the amount is valid and bigger than 0
	 * @param coupon the coupon customer wants to buy
	 * @return return true if amount > 0 else false
	 */
	@Override
	public boolean isValidAmount(Coupon coupon) throws ClassNotFoundException, InterruptedException, SQLException, NullConnectionException,UnAvailableCouponException
	{
		Coupon tempCoupon = couponRepo.findByIdAndAmountGreaterThan(coupon.getId(), 0);

		if(tempCoupon != null)
		{
			System.out.println("tempCoupon amount is : " + tempCoupon.getAmount());
			return true;
		}
		return false;
	}
	//-----------------------------------------------------------------------------------------------
	/**
	 * checking if today is before the end date of the coupon
	 * @param coupon coupon customer wants to buy 
	 * @return return true if today is before , else false
	 */
	@Override
	public boolean isValidDate(Coupon coupon) throws ClassNotFoundException, InterruptedException, SQLException, NullConnectionException,UnAvailableCouponException 
	{

		//today date
		Date todayDate = new Date();

		Coupon tempCoupon = couponRepo.findByIdAndEndDateAfter(coupon.getId(), todayDate);

		if(tempCoupon != null)
		{
			System.out.println("tempCoupon title is : " + tempCoupon.getTitle()+ "endDate " + coupon.getEndDate());
			return true;
		}
		return false;

	}
	//-----------------------------------------------------------------------------------------------
	/**
	 * checking if the customer have purchased the coupon before 
	 * @param coupon coupon customer wants to buy 
	 * @return return true if this is the first time customer wants to buy , else false
	 */
	@Override
	public boolean isFirstBuy(Coupon coupon) throws ClassNotFoundException, InterruptedException, SQLException, NullConnectionException,UnAvailableCouponException 
	{
		Customer tempCustomer = customerRepo.findOne(customerId);

		//List<Coupon> customerCoupons = null;

		System.out.println("customer is : " +  tempCustomer);
		System.out.println("customerId is : " + customerId);
		if(tempCustomer == null)
		{
			System.out.println("coupon in if is : " + coupon);
			return false;
		}
		else
		{
			System.out.println("coupon in else is : " + coupon);

			//customerCoupons = tempCustomer.getCoupons();	
			/*	for(Coupon coup : customerCoupons )
			{
				if(coup.getTitle().equals(coupon.getTitle()))
					return false;
			}
			 */
			Coupon tempCoupon = customerRepo.findByIdAndTitle(customerId, coupon.getTitle());
			if(tempCoupon != null)
				return false;
		}
		System.out.println("coupon out of the if and the else is : " + coupon);
		return true;

	}

}
