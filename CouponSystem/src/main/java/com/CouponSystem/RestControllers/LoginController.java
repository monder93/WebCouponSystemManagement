package com.CouponSystem.RestControllers;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.CouponSystem.Entities.ClientType;
import com.CouponSystem.Entities.User;
import com.CouponSystem.Facades.CouponClientFacade;
import com.CouponSystem.Facades.CouponSystem;

@RestController
public class LoginController
{
	//getting instance of CouponSystem
	@Autowired
	CouponSystem cs;

	@Autowired
	HttpSession session;

	@PostMapping(value="loginService" )
	public ResponseEntity<String> login(@RequestBody User user )
	{
		String username = user.getUsername();
		String password = user.getPassword();
		ClientType clientType = ClientType.valueOf(user.getType().toUpperCase());

//		String type = user.getType();

		System.out.println("clientType is : " + clientType.toString());
		
		
//		if(type.equalsIgnoreCase("admin"))
//		{
//			clientType = ClientType.ADMIN;
//		}
//		else if(type.equalsIgnoreCase("company"))
//		{
//			clientType = ClientType.COMPANY;
//		}
//		else
//		{
//			clientType = ClientType.CUSTOMER;
//		}
		System.out.println("after client type");

		CouponClientFacade facade = cs.login(username, password, clientType);

		//returning the response Accepted
		if(facade != null)
		{
			//saving facade in session 
			session.setAttribute("facade", facade);
			session.setAttribute("type", clientType.toString());

			//System.out.println(session.getId());
			return new ResponseEntity<String>(HttpStatus.ACCEPTED);
		}
		//UNAUTHORIZED 401 Request
		return new ResponseEntity<String>(HttpStatus.UNAUTHORIZED);

	}
	
	@GetMapping(value="logoutService" )
	public ResponseEntity<String> logout()
	{
		System.out.println("logged in to logoutService");
		if(!session.isNew())
		{
			System.out.println("logged in to session to remove it ");
			
			session.removeAttribute("facade");
			session.removeAttribute("type");
		}
		System.out.println("logged out f the session 'if' ");

		return new ResponseEntity<String>(HttpStatus.ACCEPTED);
	}

}
