package com.CouponSystem.RestControllers;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.CouponSystem.Entities.Coupon;
import com.CouponSystem.Entities.CouponType;
import com.CouponSystem.Facades.CustomerFacade;

@RestController
@RequestMapping("/customer")
public class CustomerController 
{

	@Autowired
	private CustomerFacade customerFacade;

	@Autowired
	HttpSession session;	
	//----------------------------------------------------------------------------------------
	private boolean getFacade()
	{	
		// check if facade and type not null and type==customer
		// then return true 
		if(session.getAttribute("facade")!= null && session.getAttribute("type") != null)
		{
			// AUTHORIZED , logged in as customer and session is found

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

	@PostMapping("/purchasecoupon")
	public ResponseEntity<String> purchaseCoupon(@RequestBody Coupon coupon)
	{
		if(getFacade())
		{
			try 
			{
				customerFacade.purchaseCoupon(coupon);
			} 
			catch (Exception e)
			{
				return new ResponseEntity<String>(e.getMessage().toString(),HttpStatus.CONFLICT);
			}
			//return new ResponseEntity<String>("aaa",HttpStatus.CONFLICT);
			return new ResponseEntity<String>(HttpStatus.OK);
		}
		else
		{
			//returning responseEntity with UNAUTHORIZED HttpStatus 401
			return new ResponseEntity<String>(HttpStatus.UNAUTHORIZED);			
		}
	}
	//------------------------------------------------------------------------------------------------------
	@GetMapping("/getallcoupons")
	public ResponseEntity<List<Coupon>> getAllCoupons()
	{
		if(getFacade())
		{
			List<Coupon> allCoupons = (List<Coupon>) customerFacade.getAllCoupons();

			if(allCoupons == null)
				return new ResponseEntity<List<Coupon>>(HttpStatus.NO_CONTENT);

			System.out.println("all coupons size : " + allCoupons.size());
			return new ResponseEntity<List<Coupon>>(allCoupons , HttpStatus.OK);
		}
		else
		{
			//returning responseEntity with UNAUTHORIZED HttpStatus 401
			return new ResponseEntity<List<Coupon>>(HttpStatus.UNAUTHORIZED);	
		}
	}
	//------------------------------------------------------------------------------------------------------
	@GetMapping("/getallpurchasedcoupons")
	public ResponseEntity<List<Coupon>> getAllPurchasedCoupons()
	{

		if(getFacade())
		{
			List<Coupon> allCustomerCoupons = (List<Coupon>) customerFacade.getAllPurchasedCoupons();

			if(allCustomerCoupons == null)
				return new ResponseEntity<List<Coupon>>(HttpStatus.NO_CONTENT);

			System.out.println("all purchased coupons size : " + allCustomerCoupons.size());
			return new ResponseEntity<List<Coupon>>(allCustomerCoupons , HttpStatus.OK);
		}
		else
		{
			//returning responseEntity with UNAUTHORIZED HttpStatus 401
			return new ResponseEntity<List<Coupon>>(HttpStatus.UNAUTHORIZED);
		}
	}
	//------------------------------------------------------------------------------------------------------
	@GetMapping(value="getallpurchasedcouponsbytype/{couponType}")
	public ResponseEntity<List<Coupon>> getAllCouponsByType(@PathVariable CouponType couponType)
	{
		if(getFacade())
		{
			List<Coupon> allCustomerCoupons = (List<Coupon>) customerFacade.getAllPurchasedCouponsByType(couponType);

			if(allCustomerCoupons == null)
				return new ResponseEntity<List<Coupon>>(HttpStatus.NO_CONTENT);

			System.out.println("all purchased coupons by type size : " + allCustomerCoupons.size());
			return new ResponseEntity<List<Coupon>>(allCustomerCoupons , HttpStatus.OK);
		}
		else
		{
			//returning responseEntity with UNAUTHORIZED HttpStatus 401
			return new ResponseEntity<List<Coupon>>(HttpStatus.UNAUTHORIZED);
		}
	}
	//------------------------------------------------------------------------------------------------------
	@GetMapping(value="getallpurchasedcouponsbyprice/{price}")
	public ResponseEntity<List<Coupon>> getAllCouponsByPrice(@PathVariable int price)
	{
		if(getFacade())
		{
			List<Coupon> allCoupons = (List<Coupon>) customerFacade.getAllPurchasedCouponsByPrice(price);

			if(allCoupons == null)
				return new ResponseEntity<List<Coupon>>(HttpStatus.NO_CONTENT);

			System.out.println("all purchased coupons by price size : " + allCoupons.size());
			return new ResponseEntity<List<Coupon>>(allCoupons , HttpStatus.OK);
		}
		else
		{
			//returning responseEntity with UNAUTHORIZED HttpStatus 401
			return new ResponseEntity<List<Coupon>>(HttpStatus.UNAUTHORIZED);
		}
	}
}
