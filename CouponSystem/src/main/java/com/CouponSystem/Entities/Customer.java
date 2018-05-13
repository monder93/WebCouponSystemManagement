package com.CouponSystem.Entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;


/**
 * customer class - holds all the members and constructors of the companies.
 * @author monder
 * @version 1.0
 */
@Entity
@SuppressWarnings("serial")
public class Customer implements Serializable
{
	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Column(name="cust_name")
	private String custName;

	@Column(name="password")
	private String password;


	@ManyToMany(fetch=FetchType.EAGER,cascade={CascadeType.DETACH , CascadeType.MERGE,CascadeType.REFRESH})
	@JoinTable(
			name = "customer_coupon",
			joinColumns=@JoinColumn(name="customer_id"),
			inverseJoinColumns=@JoinColumn(name="coupon_id")
			)
	private List<Coupon> coupons;

	//-----------------------------------------------------------------------------------------
	public Customer()
	{
		//empty constructor  ...  needed to create a new instance via reflection by your persistence framework. (spring)
	}
	//-----------------------------------------------------------------------------------------
	/**
	 * the constructor of the customer class
	 * @param custName the name of the customer
	 * @param password the password of this customer - used for logging in
	 */
	public Customer(String custName, String password) 
	{
		super();
		this.custName = custName;
		this.password = password;
	}

	//-----------------------------------------------------------------------------------------
	/**
	 * the constructor of the customer class
	 * @param id the id of the customer in the database
	 * @param custName the name of the customer
	 * @param password the password of this customer - used for logging in
	 */
	//-----------------------------------------------------------------------------------------
	public Customer(int id, String custName, String password) 
	{
		this(custName,password);
		this.id = id;
	}

	//-----------------------------------------------------------------------------------------
	/**
	 * the constructor of the customer class
	 * @param id the id of the customer in the database
	 * @param custName the name of the customer
	 * @param password the password of this customer - used for logging in
	 * @param coupons collection<coupon> of the customer
	 */
	public Customer(int id, String custName, String password, List<Coupon> coupons) 
	{
		this(id,custName,password);
		this.coupons = coupons;
	}

	//-----------------------------------------------------------------------------------------
	/**
	 * return the customer's id
	 * @return the customer's id
	 */
	public int getId() 
	{
		return id;
	}

	//-----------------------------------------------------------------------------------------
	/**
	 * sets the customer's id
	 * @param id the customer's id
	 */
	public void setId(int id) 
	{
		this.id = id;
	}

	//-----------------------------------------------------------------------------------------
	/**
	 * return the customer's name
	 * @return the customer's name
	 */
	public String getCustName() 
	{
		return custName;
	}

	//-----------------------------------------------------------------------------------------
	/**
	 * sets the customer's name
	 * @param custName the customer's name
	 */
	public void setCustName(String custName) 
	{
		this.custName = custName;
	}

	//-----------------------------------------------------------------------------------------
	/**
	 * return the customer's password
	 * @return the customer's password
	 */
	public String getPassword() 
	{
		return password;
	}

	//-----------------------------------------------------------------------------------------
	/**
	 * sets the customer's password
	 * @param password the customer's password, used for logging in
	 */
	public void setPassword(String password)
	{
		this.password = password;
	}

	//-----------------------------------------------------------------------------------------
	/**
	 * return the customer list of created coupons
	 * @return the customer's list of created coupons
	 */
	public List<Coupon> getCoupons() 
	{
		return coupons;
	}

	//-----------------------------------------------------------------------------------------
	/**
	 * sets the customer list of coupons
	 * @param coupons the customer list of created coupons
	 */
	public void setCoupons(List<Coupon> coupons) 
	{
		this.coupons = coupons;
	}

	//-----------------------------------------------------------------------------------------
	/**
	 * a to string method for the company
	 */
	@Override
	public String toString() 
	{
		return "Customer [id=" + id + ", custName=" + custName + ", password=" + password + ", coupons=\n" + coupons
				+ "]\n";
	}
	//-----------------------------------------------------------------------------------------
	/**
	 * adding coupon to customer coupons
	 * @param coupon
	 */
	public void addCoupon(Coupon coupon)
	{
		if(coupons == null)
			coupons = new ArrayList<Coupon>();
		coupons.add(coupon);
	}

}
