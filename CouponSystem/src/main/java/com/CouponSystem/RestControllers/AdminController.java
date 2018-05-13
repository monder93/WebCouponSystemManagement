package com.CouponSystem.RestControllers;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.CouponSystem.Entities.Company;
import com.CouponSystem.Entities.Customer;
import com.CouponSystem.Facades.AdminFacade;

@RestController
@RequestMapping("/admin")
public class AdminController
{
	@Autowired
	private AdminFacade adminFacade;

	@Autowired
	HttpSession session;	
	//-----------------------------------------------------------------------------------------------------
	private boolean getFacade()
	{	
		// check if facade and type not null and type==admin
		// then return true 
		if(session.getAttribute("facade")!= null && session.getAttribute("type") != null && session.getAttribute("type").equals("ADMIN"))
		{
			// AUTHORIZED , logged in as admin and session is found

			System.out.println("--------getFacade--------");
			System.out.println(session.getAttribute("type"));
			System.out.println(session.getAttribute("facade"));
			System.out.println("--end------getFacade--------");
			System.out.println(session.getId());
			return true;
		}
		else
		{
			// NOT AUTHORIZED
			System.out.println(session.getId());
			return false;
		}

	}
	//-------------------------------------------------------------------------------------------------------------

	//@RequestMapping(value="createCompany" , method = RequestMethod.POST)
	@PostMapping(value="createcompany" )
	public ResponseEntity<String> createCompany(@RequestBody Company company)
	{
		//if authorized
		if(getFacade())
		{
			//calling the facade to create the company
			try
			{
				adminFacade.createCompany(company);
			}
			catch(Exception ex)
			{
				//System.out.println(ex.getMessage().toString());
				return new ResponseEntity<String>(ex.getMessage().toString(),HttpStatus.CONFLICT);	

			}
			//returning responseEntity with Created HttpStatus
			return new ResponseEntity<String>(HttpStatus.CREATED);	
		}
		else
		{
			//returning responseEntity with UNAUTHORIZED HttpStatus 401
			return new ResponseEntity<String>(HttpStatus.UNAUTHORIZED);	
		}


	}
	//-------------------------------------------------------------------------------------------------------------
	@DeleteMapping(value="deletecompany/{id}" )
	public ResponseEntity<String> deleteCompany(@PathVariable int id)
	{
		if(getFacade())
		{
			//calling the facade to delete the company
			adminFacade.removeComp(id);

			//returning responseEntity with Created HttpStatus
			return new ResponseEntity<String>(HttpStatus.OK);	
		}
		else
		{
			//returning responseEntity with UNAUTHORIZED HttpStatus 401
			return new ResponseEntity<String>(HttpStatus.UNAUTHORIZED);	
		}


	}
	//-------------------------------------------------------------------------------------------------------------
	@PutMapping(value="updatecompany" )
	public ResponseEntity<String> updateCompany(@RequestBody Company company)
	{

		if(getFacade())
		{
			System.out.println("company is : " + company);
			//calling the facade to delete the company
			adminFacade.updateCompany(company);

			//returning responseEntity with Created HttpStatus
			return new ResponseEntity<String>(HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<String>(HttpStatus.UNAUTHORIZED);	

		}

	}
	//-------------------------------------------------------------------------------------------------------------
	@GetMapping(value="getcompany/{id}")
	public ResponseEntity<Company> getCompany(@PathVariable int id)
	{

		if(getFacade())
		{
			Company tempCompany = adminFacade.getCompany(id);

			if(tempCompany == null)
				return new ResponseEntity<Company>(HttpStatus.NO_CONTENT);

			//deleting the null coupons from the list to prevent json having them 
			tempCompany.getCoupons().remove(null);
			return new ResponseEntity<Company>(tempCompany , HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<Company>(HttpStatus.UNAUTHORIZED);	

		}

	}
	//-------------------------------------------------------------------------------------------------------------
	@GetMapping(value="getAllCompanies")
	public ResponseEntity<ArrayList<Company>> getAllCompanies(HttpServletRequest request , HttpServletResponse response)
	{
		if(getFacade())
		{
			//return adminFacade.getCompany(id);
			ArrayList<Company> allCompanies = (ArrayList<Company>) adminFacade.getAllCompanies();

			if(allCompanies == null)
				return new ResponseEntity<ArrayList<Company>>(HttpStatus.NO_CONTENT);

			System.out.println("companies size : " + allCompanies.size());
			return new ResponseEntity<ArrayList<Company>>(allCompanies , HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<ArrayList<Company>>(HttpStatus.UNAUTHORIZED);
		}
	
	}
	//**************************************************************************************************************
	@PostMapping(value="createcustomer" )
	public ResponseEntity<String> createCustomer(@RequestBody Customer customer)
	{
		if(getFacade())
		{
			//calling the facade to create the customer
			try
			{
				adminFacade.createCustomer(customer);
			}
			catch(Exception ex)
			{
				//System.out.println(ex.getMessage().toString());
				return new ResponseEntity<String>(ex.getMessage().toString(),HttpStatus.CONFLICT);	

			}
			//returning responseEntity with Created HttpStatus
			return new ResponseEntity<String>(HttpStatus.CREATED);	
		}
		else
		{
			return new ResponseEntity<String>(HttpStatus.UNAUTHORIZED);

		}
	}
	//-------------------------------------------------------------------------------------------------------------
	@DeleteMapping(value="deletecustomer/{id}" )
	public ResponseEntity<String> deleteCustomer(@PathVariable int id)
	{
		if(getFacade())
		{
			//calling the facade to delete the company
			adminFacade.removeCust(id);

			//returning responseEntity with Created HttpStatus
			return new ResponseEntity<String>(HttpStatus.OK);	
		}
		else
		{
			return new ResponseEntity<String>(HttpStatus.UNAUTHORIZED);
		}
	}
	//-------------------------------------------------------------------------------------------------------------
	@PutMapping(value="updatecustomer" )
	public ResponseEntity<String> updateCustomer(@RequestBody Customer customer)
	{
		if(getFacade())
		{
			//calling the facade to delete the company
			adminFacade.updateCustomer(customer);

			//returning responseEntity with Created HttpStatus
			return new ResponseEntity<String>(HttpStatus.OK);	
		}
		else
		{
			return new ResponseEntity<String>(HttpStatus.UNAUTHORIZED);

		}
	}
	//-------------------------------------------------------------------------------------------------------------
	@GetMapping(value="getCustomer/{id}")
	public ResponseEntity<Customer> getCustomer(@PathVariable int id)
	{

		if(getFacade())
		{
			//return adminFacade.getCompany(id);

			Customer tempCustomer = adminFacade.getCustomer(id);

			if(tempCustomer == null)
				return new ResponseEntity<Customer>(HttpStatus.NO_CONTENT);
			return new ResponseEntity<Customer>(tempCustomer , HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<Customer>(HttpStatus.UNAUTHORIZED);

		}

	}
	//-------------------------------------------------------------------------------------------------------------
	@GetMapping(value="getAllCustomers")
	public ResponseEntity<ArrayList<Customer>> getAllCustomer()
	{

		if(getFacade())
		{
			//return adminFacade.getCompany(id);
			ArrayList<Customer> allCustomer = (ArrayList<Customer>) adminFacade.getAllCustomer();

			if(allCustomer == null)
				return new ResponseEntity<ArrayList<Customer>>(HttpStatus.NO_CONTENT);
			System.out.println("customers size : " + allCustomer.size());
			return new ResponseEntity<ArrayList<Customer>>(allCustomer , HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<ArrayList<Customer>>(HttpStatus.UNAUTHORIZED);

		}
	}

}
