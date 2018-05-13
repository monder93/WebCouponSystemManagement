package com.CouponSystem.Servlets;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import com.CouponSystem.Entities.ClientType;
import com.CouponSystem.Facades.CouponClientFacade;
import com.CouponSystem.Facades.CouponSystem;

@Controller
public class loginServlet 
{
	
	//getting instance of CouponSystem
	@Autowired
	CouponSystem cs;
	
	@PostMapping(value="/loginServlet")
	public void doPost(HttpServletRequest request , HttpServletResponse response ) throws IOException
	{		
		//response.sendRedirect("http://localhost:4200/viewAllCompanies");
		
		//getting the values from the parameters 

		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String type = request.getParameter("type");
		
		System.out.println("username is : " + username);
		System.out.println("password is : " + password);
		System.out.println("type is : " + type);		
		
		ClientType clientType;
		if(type.equalsIgnoreCase("admin"))
		{
			clientType = ClientType.ADMIN;
		}
		else if(type.equalsIgnoreCase("company"))
		{
			clientType = ClientType.COMPANY;
		}
		else
		{
			clientType = ClientType.CUSTOMER;
		}
		System.out.println("after client type");

		CouponClientFacade facade = cs.login(username, password, clientType);
		
		//redirecting to the correct page 
		if(facade == null)
		{
			//login failed
			System.out.println("logged in to failed");
			response.sendRedirect("http://localhost:4200/login");
		}
		else
		{
			System.out.println("logged in to success");
			System.out.println(facade.toString());
			//login success
			response.sendRedirect("http://localhost:4200/viewAllCompanies");
		}
		
		System.out.println("end of  loginServlet");
		
		
		
	}
	
	@GetMapping(value="/loginServlet")
	public void doGet(HttpServletRequest request , HttpServletResponse response ) throws IOException
	{
		doPost(request, response);
	}
	

}
