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
import javax.persistence.OneToMany;
import javax.persistence.OrderColumn;


/**
 * company class - holds all the members and constructors of the companies.
 * @author monder
 * @version 1.0
 */
@Entity
@SuppressWarnings("serial")
public class Company implements Serializable
{

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Column(name="comp_name")
	private String compName;

	@Column(name="password")
	private String password;

	@Column(name="email")
	private String email;

	
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name="comp_id")
	@OrderColumn
	private List<Coupon> coupons;

	//-----------------------------------------------------------------------------------------
	public Company()
	{
		//empty constructor  ...  needed to create a new instance via reflection by your persistence framework. (spring)
	}

	//-----------------------------------------------------------------------------------------
	/**
	 *  
	 * @param compName company name 
	 * @param password password of the company
	 * @param email email of the company
	 */
	public Company(String compName, String password, String email) 
	{
		super();
		this.compName = compName;
		this.password = password;
		this.email = email;
	}

	//-----------------------------------------------------------------------------------------
	/**
	 * 
	 * @param id unique identifier number for the company 
	 * @param compName company name 
	 * @param password password of the company
	 * @param email email of the company
	 */
	public Company(int id, String compName, String password, String email) 
	{
		this(compName,password,email);
		this.id = id;
	}

	//-----------------------------------------------------------------------------------------
	/**
	 * 
	 * @param id unique identifier number for the company 
	 * @param compName company name 
	 * @param password password of the company
	 * @param email email of the company
	 * @param coupons  collect<coupon> of the company 
	 */
	public Company(int id, String compName, String password, String email, List<Coupon> coupons) 
	{
		this(id,compName,password,email);
		this.coupons = coupons;
	}

	//-----------------------------------------------------------------------------------------
	/**
	 *  return the company's id
	 * @return the company's id
	 */
	public int getId() 
	{
		return id;
	}

	//-----------------------------------------------------------------------------------------
	/**
	 * sets the company's id
	 * @param id the company's id
	 */
	public void setId(int id) 
	{
		this.id = id;
	}

	//-----------------------------------------------------------------------------------------
	/**
	 * return the company's name
	 * @return the company's name
	 */
	public String getCompName() 
	{
		return compName;
	}

	//-----------------------------------------------------------------------------------------
	/**
	 * sets the company's name
	 * @param compName the company's name
	 */
	public void setCompName(String compName) 
	{
		this.compName = compName;
	}

	//-----------------------------------------------------------------------------------------
	/**
	 * return the company's password
	 * @return the company's password
	 */
	public String getPassword() 
	{
		return password;
	}

	//-----------------------------------------------------------------------------------------
	/**
	 * sets the company's password
	 * @param password the company's password
	 */
	public void setPassword(String password) 
	{
		this.password = password;
	}

	//-----------------------------------------------------------------------------------------
	/**
	 * return the company's email
	 * @return the company's email
	 */
	public String getEmail()
	{
		return email;
	}

	//-----------------------------------------------------------------------------------------
	/**
	 * sets the company's email
	 * @param email the company's email
	 */
	public void setEmail(String email) 
	{
		this.email = email;
	}

	//-----------------------------------------------------------------------------------------
	/**
	 * return the company's list of created coupons
	 * @return the company's list of created coupons
	 */
	public List<Coupon> getCoupons() 
	{
		return coupons;
	}

	//-----------------------------------------------------------------------------------------
	/**
	 * sets the company's list of coupons
	 * @param coupons the company's list of created coupons
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
		return "Company [id=" + id + ", compName=" + compName + ", password=" + password + ", email=" + email
				+ ", coupons=\n" + coupons + "]\n";
	}
	
	//-----------------------------------------------------------------------------------------
	/**
	 * adding coupon to company coupons
	 * @param coupon
	 */
	public void addCoupon(Coupon coupon)
	{
		if(coupons == null)
			coupons = new ArrayList<Coupon>();
		if(coupon!=null)
		coupons.add(coupon);
	}
}
