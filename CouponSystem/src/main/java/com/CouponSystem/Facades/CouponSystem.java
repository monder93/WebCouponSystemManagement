package com.CouponSystem.Facades;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.CouponSystem.Entities.ClientType;

/**
 * 
 * The CouponSystem class is a singleton class that is used for logging in to the CouponSystem.
 * It is in charge of returning the right facade according to the user type, and it holds the
 * method that shuts down the CouponSystem.
 *
 */
@Service
@Scope(value="singleton")
public class CouponSystem 
{	
	
	@Autowired
    AdminFacade adminfacade;

	@Autowired
    CompanyFacade companyfacade;
    
	@Autowired
	CustomerFacade customerfacade;	
	
	//-----------------------------------------------------------------------------------------------

	/**
	 * a factory pattern that returns the correct facade according to the login input
	 * 
	 * 
	 * @param name the name of the client
	 * @param password the password of the client
	 * @param clientType the type of client
	 * @return a facade according to the login input if true, if false returns null
	 */
	public CouponClientFacade login(String name,String password,ClientType clientType)
	{
		CouponClientFacade login = null;
		//ApplicationContext context = new AnnotationConfigApplicationContext();

		switch(clientType)
		{
		case CUSTOMER :
			login = customerfacade.login(name, password, clientType);
			if (login!=null)
			{
				System.out.println("welcome \nname : " + name + "\ntype : customer");
				System.out.println("-------------");
				return customerfacade;
			}
			break;

		case COMPANY : 
			login = companyfacade.login(name, password, clientType);
			if (login!=null)
			{
				System.out.println("welcome \nname : " + name + "\ntype : company");
				System.out.println("-------------");
				companyfacade.setUserId();
				return companyfacade;
			}
			break;

		case ADMIN : 
			login = adminfacade.login(name, password, clientType);
			if (login!=null)
			{
				System.out.println("welcome admin");
				System.out.println("-------------");
				return adminfacade;
			}
		}

		return null;
	}
}