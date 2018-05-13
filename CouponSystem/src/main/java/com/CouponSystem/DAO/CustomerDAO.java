package com.CouponSystem.DAO;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.Collection;

import org.springframework.stereotype.Component;

import com.CouponSystem.Entities.Coupon;
import com.CouponSystem.Entities.CouponType;
import com.CouponSystem.Entities.Customer;
import com.CouponSystem.Exceptions.DuplicateEntryException;
import com.CouponSystem.Exceptions.NullConnectionException;
import com.CouponSystem.Exceptions.UnAvailableCouponException;
import com.CouponSystem.Exceptions.WrongDataInputException;



/**
 * this is a interface to configure all the functions and operations that customer needs
 * @author monder
 * @version 1.0
 */
@Component
public interface CustomerDAO 
{
	//------------------------------------------interface base functions----------------------------------------- 
	
	/**
	 * Receives a customer instance and register it in the database
	 * @param customer a customer instance
	 * @throws ClassNotFoundException thrown when the customer class is not available
	 * @throws InterruptedException thrown when the thread is interrupted - might be because the system is shutting down
	 * @throws SQLException thrown when the sql query is wrong
	 * @throws DuplicateEntryException thrown when trying to register a customer that already exist
	 * @throws NullConnectionException thrown when the connection is null
	 */
	public void createCustomer(Customer customer) throws ClassNotFoundException, InterruptedException, SQLException, DuplicateEntryException, NullConnectionException ;

	/**
	 * Receives a customer instance and removes it's entries from the database
	 * @param customer a customer instance
	 * @throws ClassNotFoundException thrown when the customer class is not available
	 * @throws InterruptedException thrown when the thread is interrupted - might be because the system is shutting down
	 * @throws SQLException thrown when the sql query is wrong
	 * @throws NullConnectionException thrown when the connection is null
	 */
	public void removeCustomer(Customer customer) throws SQLException, ClassNotFoundException, InterruptedException, NullConnectionException;

	/**
	 * Receives a customer instance and update its entries in the database
	 * @param customer a customer instance
	 * @throws ClassNotFoundException thrown when the customer class is not available
	 * @throws InterruptedException thrown when the thread is interrupted - might be because the system is shutting down
	 * @throws SQLException thrown when the sql query is wrong
	 * @throws NullConnectionException thrown when the connection is null
	 */
	public void updateCustomer(Customer customer) throws SQLException, ClassNotFoundException, InterruptedException, NullConnectionException;

	/**
	 * Receives an id of a customer and returns an instance of the desired customer from the database
	 * @param id the desired customer's id
	 * @return an instance of the desired customer from the database
	 * @throws ClassNotFoundException thrown when the customer class is not available
	 * @throws InterruptedException thrown when the thread is interrupted - might be because the system is shutting down
	 * @throws SQLException thrown when the sql query is wrong
	 * @throws NullConnectionException thrown when the connection is null
	 */
	public Customer getCustomer(int id) throws SQLException, ClassNotFoundException, InterruptedException, NullConnectionException;

	/**
	 * returns an ArrayList of all the customers in the database
	 * @return an ArrayList of all the customers in the database
	 * @throws ClassNotFoundException thrown when the customer class is not available
	 * @throws InterruptedException thrown when the thread is interrupted - might be because the system is shutting down
	 * @throws SQLException thrown when the sql query is wrong
	 * @throws NullConnectionException thrown when the connection is null
	 */
	public Collection<Customer> getAllCustomer() throws SQLException, ClassNotFoundException, InterruptedException, NullConnectionException;
	
	/**
	 * returns an ArrayList of all the coupons from the database
	 * @return an ArrayList of all the coupons from the database
	 * @throws ClassNotFoundException thrown when the coupon class is not available
	 * @throws InterruptedException thrown when the thread is interrupted - might be because the system is shutting down
	 * @throws SQLException thrown when the sql query is wrong
	 * @throws ParseException thrown when the date is not in the correct format
	 * @throws NullConnectionException thrown when the connection is null
	 */
	public Collection<Coupon>  getAllCoupons() throws SQLException, ClassNotFoundException, InterruptedException, NullConnectionException, NullPointerException;

	
	/**
	 * returns an ArrayList of all the coupons purchased by the current customer from the database
	 * @return an ArrayList of all the coupons purchased by the current customer from the database
	 * @throws ClassNotFoundException thrown when the coupon class is not available
	 * @throws InterruptedException thrown when the thread is interrupted - might be because the system is shutting down
	 * @throws SQLException thrown when the sql query is wrong
	 * @throws ParseException thrown when the date is not in the correct format
	 * @throws NullConnectionException thrown when the connection is null
	 */
	public Collection<Coupon>  getCoupons() throws SQLException, ClassNotFoundException, InterruptedException, NullConnectionException, NullPointerException;

	/**
	 * checks the database for a customer with the given name and the given password
	 * @param custName the customer's name
	 * @param password the customer's password
	 * @return true value if there is a match' false if there is no match
	 * @throws ClassNotFoundException thrown when the customer class is not available
	 * @throws InterruptedException thrown when the thread is interrupted - might be because the system is shutting down
	 * @throws SQLException thrown when the sql query is wrong
	 * @throws WrongDataInputException thrown when the input does not match any customer in the database
	 * @throws NullConnectionException thrown when the connection is null
	 */
	public boolean login(String custName, String password) throws SQLException, ClassNotFoundException, InterruptedException, WrongDataInputException, NullConnectionException;
	
	//------------------------------------------interface plus functions----------------------------------------- 

	/**
	 * returns an ArrayList of all the coupons purchased by the current customer from the database for specific customer by id 
	 * @return an ArrayList of all the coupons purchased by the current customer from the database for specific customer by id 
	 * @throws ClassNotFoundException thrown when the coupon class is not available
	 * @throws InterruptedException thrown when the thread is interrupted - might be because the system is shutting down
	 * @throws SQLException thrown when the sql query is wrong
	 * @throws ParseException thrown when the date is not in the correct format
	 * @throws NullConnectionException thrown when the connection is null
	 */
	public Collection<Coupon> getCouponsByCustomerId(int id) throws SQLException, ClassNotFoundException, InterruptedException, NullConnectionException, NullPointerException;

	/**
	 * returns an ArrayList of all the coupons purchased by the current customer from the database by type
	 * @return an ArrayList of all the coupons purchased by the current customer from the database by type
	 * @throws ClassNotFoundException thrown when the coupon class is not available
	 * @throws InterruptedException thrown when the thread is interrupted - might be because the system is shutting down
	 * @throws SQLException thrown when the sql query is wrong
	 * @throws ParseException thrown when the date is not in the correct format
	 * @throws NullConnectionException thrown when the connection is null
	 */
	public Collection<Coupon>  getCouponsByType(CouponType couponType) throws SQLException, ClassNotFoundException, InterruptedException, NullConnectionException;
	
	/**
	 * returns an ArrayList of all the coupons purchased by the current customer from the database by price
	 * @return an ArrayList of all the coupons purchased by the current customer from the database by price
	 * @throws ClassNotFoundException thrown when the coupon class is not available
	 * @throws InterruptedException thrown when the thread is interrupted - might be because the system is shutting down
	 * @throws SQLException thrown when the sql query is wrong
	 * @throws ParseException thrown when the date is not in the correct format
	 * @throws NullConnectionException thrown when the connection is null
	 */
	public Collection<Coupon>  getCouponsByPrice(double price) throws SQLException, ClassNotFoundException, InterruptedException, NullConnectionException;

	/**
	 * connecting coupon to customer
	 * @throws ClassNotFoundException thrown when the coupon class is not available
	 * @throws InterruptedException thrown when the thread is interrupted - might be because the system is shutting down
	 * @throws SQLException thrown when the sql query is wrong
	 * @throws ParseException thrown when the date is not in the correct format
	 * @throws NullConnectionException thrown when the connection is null
	 */
	public void purchaseCoupon(Coupon coupon) throws ClassNotFoundException, InterruptedException, SQLException, NullConnectionException,UnAvailableCouponException;
	
	/**
	 * checking if coupon have amount bigger than 0
	 * @throws ClassNotFoundException thrown when the coupon class is not available
	 * @throws InterruptedException thrown when the thread is interrupted - might be because the system is shutting down
	 * @throws SQLException thrown when the sql query is wrong
	 * @throws ParseException thrown when the date is not in the correct format
	 * @throws NullConnectionException thrown when the connection is null
	 */
	public boolean isValidAmount(Coupon coupon) throws ClassNotFoundException, InterruptedException, SQLException, NullConnectionException,UnAvailableCouponException;
	
	
	/**
	 * checking if coupon is not out of date
	 * @throws ClassNotFoundException thrown when the coupon class is not available
	 * @throws InterruptedException thrown when the thread is interrupted - might be because the system is shutting down
	 * @throws SQLException thrown when the sql query is wrong
	 * @throws ParseException thrown when the date is not in the correct format
	 * @throws NullConnectionException thrown when the connection is null
	 */
	public boolean isValidDate(Coupon coupon) throws ClassNotFoundException, InterruptedException, SQLException, NullConnectionException,UnAvailableCouponException;

	
	/**
	 * checking if the customer has not purchased the coupon before
	 * @throws ClassNotFoundException thrown when the coupon class is not available
	 * @throws InterruptedException thrown when the thread is interrupted - might be because the system is shutting down
	 * @throws SQLException thrown when the sql query is wrong
	 * @throws ParseException thrown when the date is not in the correct format
	 * @throws NullConnectionException thrown when the connection is null
	 */
	public boolean isFirstBuy(Coupon coupon) throws ClassNotFoundException, InterruptedException, SQLException, NullConnectionException,UnAvailableCouponException;

}
