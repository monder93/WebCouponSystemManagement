package com.CouponSystem.Services;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.CouponSystem.DAO.CompanyDAO;
import com.CouponSystem.Entities.Company;
import com.CouponSystem.Entities.Coupon;
import com.CouponSystem.Entities.CouponType;
import com.CouponSystem.Exceptions.DuplicateEntryException;
import com.CouponSystem.Exceptions.NullConnectionException;
import com.CouponSystem.Exceptions.WrongDataInputException;
import com.CouponSystem.Repositories.CompanyRepository;
import com.CouponSystem.Repositories.CouponRepository;


/**
 * this class implements a Company data access object to perform operations with objects and database 
 * Receives data from the database to the user || write data received from the user to the database 
 * @author monder
 * @version 1.0
 */
@Service
public class CompanyDBDAO implements CompanyDAO
{
	@Autowired
	private CompanyRepository companyRepo;

	@Autowired
	private CouponRepository couponRepo;

	//id = 2 using only for tests 
	//private int companyId=2;
	//private int companyId=4;
	private int companyId;

	//-------------------------------------------------------------------------------------------------------
	/**
	 * getting as a parameter company and adding it to the database if not exist
	 * @param company instance object of a company 
	 * @throws ParseException 
	 */
	@Override
	public void createCompany(Company company) throws ClassNotFoundException, InterruptedException, SQLException, DuplicateEntryException, NullConnectionException, ParseException
	{
		/* check if there is a company exist with the same name 
		 * if not then add company to database 
		 * if exist then throws exception
		 */
		Company c = companyRepo.findBycompName(company.getCompName());

		//System.out.println(c);

		//checking if there is no company exist with the same name
		if(c==null)
		{
			companyRepo.save(company);
		}
		else // company with the same name found
		{
			throw new DuplicateEntryException("the admin tried to create a company with a name that already exist in the database");
		}

	}
	//-------------------------------------------------------------------------------------------------------
	/**
	 * getting as a parameter company and removing it from the database if exist
	 * @param company instance object of a company
	 * @throws ClassNotFoundException
	 * @throws InterruptedException
	 * @throws SQLException
	 * @throws NullConnectionException
	 * @throws ParseException 
	 */
	@Override
	public void removeCompany(Company company) throws ClassNotFoundException, InterruptedException, SQLException, NullConnectionException, ParseException
	{
		// check if company exist by id 
		if(company!=null)
		{
			if(companyRepo.exists(company.getId()))
			{
				companyRepo.delete(company.getId());
				System.out.println("company : " + company.getCompName() + " removerd successfull");		    		
			}
			else
			{
				System.out.println("compnay : " + company.getCompName() + " not exist in the database");
			}
		}
		else
		{
			System.out.println("company is null - cant remove ");
		}
	}
	//-------------------------------------------------------------------------------------------------------
	/**
	 * getting as a parameter company and updating it in the database if exist
	 * @param company instance object of a company
	 * @throws ClassNotFoundException
	 * @throws InterruptedException
	 * @throws SQLException
	 * @throws NullConnectionException
	 * @throws ParseException
	 */
	@Override
	public void updateCompany(Company company) throws ClassNotFoundException, InterruptedException, SQLException, NullConnectionException, ParseException 
	{
		//getting the current info from the DB
		//Company tempCompany = companyRepo.findOne(company.getId());
		Company tempCompany = companyRepo.findBycompName(company.getCompName());
		
		//checking if exist
		//if(companyRepo.exists(company.getId()))
		if(tempCompany != null)
		{
			//setting the id 
			//company.setId(tempCompany.getId());

			// to check and reset the compName if changed
			if(!tempCompany.getCompName().equals(company.getCompName()))
			{
				company.setCompName(tempCompany.getCompName());
			}
			
			//update the password of tempcompany 
			tempCompany.setPassword(company.getPassword());
			
			//updating the company
			companyRepo.save(tempCompany);
			System.out.println("company " + tempCompany.getCompName() + " updated successfully");
		}
		else
		{
			System.out.println("company is not exist in the database");

		}
	}
	//-------------------------------------------------------------------------------------------------------
	/**
	 * getting as a parameter int id  and retrieve company data from  the database if exist
	 * @param id id of a company
	 * @return returns a company object 
	 */
	@Override
	public Company getCompany(int id) throws ClassNotFoundException, InterruptedException, SQLException, NullConnectionException, ParseException 
	{
		Company tempCompany = null;
		//System.out.println(tempCompany); 

		if(companyRepo.exists(id))
		{
			tempCompany = companyRepo.findOne(id);
		}
		else
		{
			System.out.println("there is no company with this id ");
		}

		//returning the company object 
		return tempCompany;
	}
	//-------------------------------------------------------------------------------------------------------
	/**
	 * getting all the companies from the database
	 * @return return a collection<company> with all the companies inside it 
	 */
	@Override
	public Collection<Company> getAllCompanies() throws ClassNotFoundException, InterruptedException, SQLException, NullConnectionException, ParseException 
	{
		ArrayList<Company> tempCompanyArray = new ArrayList<>();
		tempCompanyArray = (ArrayList<Company>) companyRepo.findAll();
		return tempCompanyArray;
	}
	//-------------------------------------------------------------------------------------------------------
	/**
	 * getting all the coupons of this company 
	 * @return returning collection<Coupon> with all the coupons of this company inside it 
	 */
	@Override
	public Collection<Coupon> getCoupons() throws ClassNotFoundException, InterruptedException, SQLException, ParseException, NullConnectionException
	{
		//System.out.println("company id : " + companyId);
		Company c = companyRepo.findOne(companyId);
		//System.out.println("coupon of company id : " + companyId + " is : " + c.getCoupons() + " \n size is : " + c.getCoupons().size());
		Collection<Coupon> arrayOfCompanyCoupons = null;
		if(c!=null)
		{
			//System.out.println(c);
			arrayOfCompanyCoupons = c.getCoupons();
		}
		return arrayOfCompanyCoupons;
	}
	//-------------------------------------------------------------------------------------------------------
	/**
	 * login function to perform login operation
	 * @param compName company name 
	 * @param password password of the company
	 * @return returns true if compName and password is correct and they are in the database , else returns false
	 */
	@Override
	public boolean login(String compName, String password)  throws ClassNotFoundException, InterruptedException, SQLException, WrongDataInputException, NullConnectionException
	{
		Company tempCompany = companyRepo.findBycompNameAndPassword(compName, password); 
		if(tempCompany==null)
		{
			return false;
		}
		setCompanyId(tempCompany.getId());
		return true;

	}
	//-------------------------------------------------------------------------------------------------------
	/**
	 * getting coupons by company ID
	 * @param id id of the company 
	 * @return returning a collection<Coupon> inside it coupons of company with the id in the parameter
	 */
	@Override
	public Collection<Coupon> getCouponsByCompanyId(int id) throws ClassNotFoundException, InterruptedException, SQLException, NullConnectionException, ParseException
	{
		Collection<Coupon> tempCouponsArray ;
		Company comp = companyRepo.findOne(id);
		if(comp == null)
		{
			System.out.println("company is null");
			return null;
		}

		tempCouponsArray = comp.getCoupons();
		return tempCouponsArray;
	}

	//--------------------------------------------------------------------------------------------------------------
	/**
	 * getting company coupons by Type 
	 * @param couponType type of the coupon
	 * @return return collection<Coupon> inside it the company coupons by specific type
	 */
	@Override
	public Collection<Coupon> getCompanyCouponByType(CouponType couponType)  throws ClassNotFoundException, InterruptedException, SQLException, NullConnectionException, ParseException
	{	
		ArrayList<Coupon> tempCouponsArray ;

		//coupons of this company that their id and coupon type as the parameters

		tempCouponsArray =  (ArrayList<Coupon>) companyRepo.findByCouponType(companyId,couponType);
		return tempCouponsArray;

	}
	//--------------------------------------------------------------------------------------------------------------
	/**
	 * getting company coupons by price limited 
	 * @param price limit price
	 * @return return collection<Coupon> inside it the company coupons by price limit
	 */
	@Override
	public Collection<Coupon> getCompanyCouponByPrice(double price)  throws ClassNotFoundException, InterruptedException, SQLException, NullConnectionException, ParseException
	{
		ArrayList<Coupon> tempCouponsArray ;

		//coupons of this company that their id and price as the parameters

		tempCouponsArray =  (ArrayList<Coupon>) companyRepo.findByCouponPrice(companyId,price);
		return tempCouponsArray;

	}
	//--------------------------------------------------------------------------------------------------------------
	/**
	 * getting company coupons by Date 
	 * @param date max date for the coupon to be active
	 * @return return collection<Coupon> inside it the company coupons by date
	 */
	@Override
	public Collection<Coupon> getCompanyCouponByDate(Date date)  throws ClassNotFoundException, InterruptedException, SQLException, NullConnectionException, ParseException 
	{
		ArrayList<Coupon> tempCouponsArray ;

		//coupons of this company that their id and date(before today ) as the parameters

		tempCouponsArray =  (ArrayList<Coupon>) companyRepo.findByCouponDate(companyId, date);
		return tempCouponsArray;

	}

	//-----------------------------------------------------------------------------------------------------------
	/**
	 * 
	 * @return company id 
	 */
	public int getCompanyId() 
	{
		return companyId;
	}
	//-----------------------------------------------------------------------------------------------------------
	/**
	 * setting id to this company
	 * @param companyId company id
	 */
	public void setCompanyId(int companyId)
	{
		this.companyId = companyId;
	}
	public void addCouponToCompany(int id) 
	{
		Coupon tempCoupon = couponRepo.findOne(id);

		System.out.println("company id is :" + companyId);
		
		Company tempCompany = companyRepo.findOne(companyId);
		tempCompany.addCoupon(tempCoupon);
		companyRepo.save(tempCompany);

	}
}