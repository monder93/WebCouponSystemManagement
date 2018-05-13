package com.CouponSystem.Facades;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.CouponSystem.Entities.ClientType;
import com.CouponSystem.Entities.Company;
import com.CouponSystem.Entities.Customer;
import com.CouponSystem.ExceptionHandler.CompanyExceptionHandler;
import com.CouponSystem.ExceptionHandler.CustomerExceptionHandler;
import com.CouponSystem.Exceptions.DuplicateEntryException;
import com.CouponSystem.Exceptions.NullConnectionException;
import com.CouponSystem.Services.CompanyDBDAO;
import com.CouponSystem.Services.CustomerDBDAO;


/**
 * 
 * The AdminFacade class is used by the Admin of the CouponSystem.
 * It grants them access to all of the relevant methods for their uses.
 * create , remove , update , get for all the companies and customers
 *
 */

@Service
public class AdminFacade implements CouponClientFacade
{
	@Autowired
	private CompanyDBDAO companydbdao;

	@Autowired
	private CustomerDBDAO customerdbdao;



	//-----------------------------------------------------------------------------------------------------
	/**
	 * Receives a company instance and register it in the database
	 * @param company a company instance
	 */
	public void createCompany(Company company)  throws Exception
	{
		try
		{
			companydbdao.createCompany(company);
		}
		catch (ClassNotFoundException | InterruptedException | SQLException | DuplicateEntryException
				| NullConnectionException | ParseException e) 
		{
			CompanyExceptionHandler.handle(e);			
			throw e;
		}
	}

	//-----------------------------------------------------------------------------------------------------
	/**
	 * Receives a company instance and removes its entries from the database
	 * @param company a company instance
	 */
	public void removeCompany(Company company)
	{

		try 
		{
			companydbdao.removeCompany(company);
		}
		catch (ClassNotFoundException | InterruptedException | SQLException | NullConnectionException
				| ParseException e)
		{
			CompanyExceptionHandler.handle(e);			
		}

	}
	//-----------------------------------------------------------------------------------------------------
	/**
	 * Receives a company instance and update its entries in the database
	 * @param company a company instance
	 */
	public void updateCompany(Company company)
	{
		try 
		{
			companydbdao.updateCompany(company);
		}
		catch (ClassNotFoundException | InterruptedException | SQLException | NullConnectionException
				| ParseException e) 
		{
			CompanyExceptionHandler.handle(e);			
		}

	}
	//-----------------------------------------------------------------------------------------------------
	public void removeComp(int id)
	{
		Company tempComp = getCompany(id);
		removeCompany(tempComp);
	}
	//-----------------------------------------------------------------------------------------------------
	/**
	 * Receives an id of a company and returns an instance of that company from the database
	 * @param id a company's id
	 * @return an instance of that company from the database
	 */
	public Company getCompany(int id)
	{
		Company tempCompany = new Company();


		try 
		{
			tempCompany =  companydbdao.getCompany(id);
		} 
		catch (ClassNotFoundException | InterruptedException | SQLException | NullConnectionException
				| ParseException e) 
		{
			CompanyExceptionHandler.handle(e);
		}

		return tempCompany;
	}
	//-----------------------------------------------------------------------------------------------------
	/**
	 *getting all the companies from the database
	 * @return an ArrayList of all the companies in the database
	 */
	public Collection<Company> getAllCompanies()
	{
		ArrayList<Company> tempArrayOfCompanies = new ArrayList<>();

		try
		{
			tempArrayOfCompanies = (ArrayList<Company>) companydbdao.getAllCompanies();

			// initializing array if null
			if(tempArrayOfCompanies == null)
				tempArrayOfCompanies = new ArrayList<>();

			//avoiding the null objects in the array 
			Iterator<Company> companyIterator = tempArrayOfCompanies.iterator();
			while(companyIterator.hasNext())
			{
				Company  c =  companyIterator.next();
				if(c == null)
					companyIterator.remove();
			}

		}
		catch (ClassNotFoundException | InterruptedException | SQLException | NullConnectionException
				| ParseException e) 
		{
			CompanyExceptionHandler.handle(e);			

		}
		return tempArrayOfCompanies;
	}
	//-----------------------------------------------------------------------------------------------------
	/**
	 * Receives a customer instance and register it in the database
	 * @param customer a customer instance
	 */
	public void createCustomer(Customer customer) throws Exception
	{
		try 
		{
			customerdbdao.createCustomer(customer);
		}
		catch (ClassNotFoundException | InterruptedException | SQLException | DuplicateEntryException
				| NullConnectionException e) 
		{
			CustomerExceptionHandler.handle(e);
			throw e;
		}
	}

	//-----------------------------------------------------------------------------------------------------
	/**
	 * Receives a customer instance and removes its entries from the database
	 * @param customer a customer instance
	 */
	public void removeCustomer(Customer customer)
	{
		try 
		{
			customerdbdao.removeCustomer(customer);
		}
		catch (ClassNotFoundException | InterruptedException | SQLException | NullConnectionException e) 
		{
			CustomerExceptionHandler.handle(e);
		}
	}
	//-----------------------------------------------------------------------------------------------------
	public void removeCust(int id)
	{
		Customer tempCustomer = getCustomer(id);
		removeCustomer(tempCustomer);
	}
	//-----------------------------------------------------------------------------------------------------
	/**
	 * Receives a customer instance and updates its entries in the database
	 * @param customer a customer instance
	 */
	public void updateCustomer(Customer customer)
	{	
		try
		{
			customerdbdao.updateCustomer(customer);
		}
		catch (ClassNotFoundException | InterruptedException | SQLException | NullConnectionException e) 
		{
			CustomerExceptionHandler.handle(e);
		}

	}
	//-----------------------------------------------------------------------------------------------------
	/**
	 * Receives a customer is and returns an instance of that customer from the database
	 * @param id a customer id
	 * @return an instance of that customer from the database
	 */
	public Customer getCustomer(int id)
	{
		Customer tempCustomer = new Customer();

		try 
		{
			tempCustomer = customerdbdao.getCustomer(id);
		}
		catch (ClassNotFoundException | InterruptedException | SQLException | NullConnectionException e) 
		{
			CustomerExceptionHandler.handle(e);
		}

		return tempCustomer;
	}
	//-----------------------------------------------------------------------------------------------------
	/**
	 * getting all the customers 
	 * @return an ArrayList of all the customers in the database
	 */
	public Collection<Customer> getAllCustomer()
	{
		ArrayList<Customer> tempArrayOfCustomers = new ArrayList<>();

		try 
		{
			tempArrayOfCustomers = (ArrayList<Customer>) customerdbdao.getAllCustomer();

			// initializing array if null
			if(tempArrayOfCustomers == null)
				tempArrayOfCustomers = new ArrayList<>();

			//avoiding the null objects in the array 
			Iterator<Customer> customerIterator = tempArrayOfCustomers.iterator();
			while(customerIterator.hasNext())
			{
				Customer  c =  customerIterator.next();
				if(c == null)
					customerIterator.remove();
			}

		} 
		catch (ClassNotFoundException | InterruptedException | SQLException | NullConnectionException e) 
		{
			CustomerExceptionHandler.handle(e);
		}

		return tempArrayOfCustomers;
	}
	//-----------------------------------------------------------------------------------------------------
	/**
	 * checking the database for an entry of an Admin client type with the matching name and password
	 * returns true if it found one' returns false if there is no matching entry
	 */
	@Override
	public CouponClientFacade login(String name, String password, ClientType clientType) 
	{
		if (name.equals("admin") && password.equals("1234"))
		{
			return this;
		}

		return null;
	}
}
