package com.CouponSystem.RestControllers;

import java.util.Date;
import java.util.List;

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

import com.CouponSystem.Entities.Coupon;
import com.CouponSystem.Entities.CouponType;
import com.CouponSystem.Facades.CompanyFacade;
import com.CouponSystem.Utilities.converter;

@RestController
@RequestMapping("/company")
public class CompanyController 
{

	@Autowired
	private CompanyFacade companyFacade;

	@Autowired
	HttpSession session;	
	//----------------------------------------------------------------------------------------
	private boolean getFacade()
	{	
		// check if facade and type not null and type==company
		// then return true 
		if(session.getAttribute("facade")!= null && session.getAttribute("type") != null && session.getAttribute("type").equals("COMPANY"))
		{
			// AUTHORIZED , logged in as company and session is found

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

	@PostMapping(value="createcoupon" )
	public ResponseEntity<String> createCoupon(@RequestBody Coupon coupon) throws Exception
	{

		if(getFacade())
		{
			System.out.println("logged in to createCoupon");

			//calling the facade to create the company
			try
			{
				companyFacade.createCoupon(coupon);
			}
			catch(Exception ex)
			{
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
	//----------------------------------------------------------------------------------------
	@DeleteMapping(value="deletecoupon/{id}")
	public ResponseEntity<Coupon> deleteCoupon(@PathVariable int id)
	{
		if(getFacade())
		{
			System.out.println("logged in to deletecoupon");
			//calling the facade to delete the coupon
			companyFacade.removeCoup(id);

			//returning responseEntity with Created HttpStatus
			return new ResponseEntity<Coupon>(HttpStatus.OK);	
		}
		else
		{
			//returning responseEntity with UNAUTHORIZED HttpStatus 401
			return new ResponseEntity<Coupon>(HttpStatus.UNAUTHORIZED);	
		}
	}
	//----------------------------------------------------------------------------------------
	@PutMapping(value="updateCoupon" )
	public ResponseEntity<Coupon> updateCoupon(@RequestBody Coupon coupon)
	{

		if(getFacade())
		{
			//calling the facade to delete the company
			companyFacade.updateCoupon(coupon);

			//returning responseEntity with Created HttpStatus
			return new ResponseEntity<Coupon>(HttpStatus.OK);
		}
		else
		{
			//returning responseEntity with UNAUTHORIZED HttpStatus 401
			return new ResponseEntity<Coupon>(HttpStatus.UNAUTHORIZED);	
		}
	}
	//----------------------------------------------------------------------------------------
	@GetMapping(value="getCoupon/{id}")
	public ResponseEntity<Coupon> getCoupon(@PathVariable int id)
	{
		if(getFacade())
		{
			Coupon tempCoupon = companyFacade.getCoupon(id);

			if(tempCoupon == null)
				return new ResponseEntity<Coupon>(HttpStatus.NO_CONTENT);
			return new ResponseEntity<Coupon>(tempCoupon , HttpStatus.OK);
		}
		else
		{
			//returning responseEntity with UNAUTHORIZED HttpStatus 401
			return new ResponseEntity<Coupon>(HttpStatus.UNAUTHORIZED);			
		}

	}
	//----------------------------------------------------------------------------------------
	@GetMapping(value="getallcoupons")
	public ResponseEntity<List<Coupon>> getAllCoupons()
	{
		if(getFacade())
		{

			List<Coupon> allCoupons =  (List<Coupon>) companyFacade.getAllCoupon();

			if(allCoupons == null)
				return new ResponseEntity<List<Coupon>>(HttpStatus.NO_CONTENT);

			System.out.println("coupons size : " + allCoupons.size());
			return new ResponseEntity<List<Coupon>>(allCoupons , HttpStatus.OK);
		}
		else
		{
			//returning responseEntity with UNAUTHORIZED HttpStatus 401
			return new ResponseEntity<List<Coupon>>(HttpStatus.UNAUTHORIZED);	
		}
	}
	//----------------------------------------------------------------------------------------
	@GetMapping(value="getallcouponsbycoupontype/{couponType}")
	public ResponseEntity<List<Coupon>> getAllCouponsByType(@PathVariable CouponType couponType)
	{
		if(getFacade())
		{
			List<Coupon> allCoupons =  (List<Coupon>) companyFacade.getCouponByType(couponType);

			if(allCoupons == null)
			{
				return new ResponseEntity<List<Coupon>>(HttpStatus.NO_CONTENT);
			}
			System.out.println(allCoupons);

			System.out.println("coupons by type size : " + allCoupons.size());
			return new ResponseEntity<List<Coupon>>(allCoupons , HttpStatus.OK);	
		}
		else
		{
			//returning responseEntity with UNAUTHORIZED HttpStatus 401
			return new ResponseEntity<List<Coupon>>(HttpStatus.UNAUTHORIZED);	
		}
	}
	//----------------------------------------------------------------------------------------
	@GetMapping(value="getallcouponsbyprice/{price}")
	public ResponseEntity<List<Coupon>> getAllCouponsByPrice(@PathVariable int price)
	{
		if(getFacade())
		{
			List<Coupon> allCoupons = (List<Coupon>) companyFacade.getCouponByPrice(price);

			if(allCoupons == null)
				return new ResponseEntity<List<Coupon>>(HttpStatus.NO_CONTENT);

			System.out.println("coupons by price size : " + allCoupons.size());
			return new ResponseEntity<List<Coupon>>(allCoupons , HttpStatus.OK);
		}
		else
		{
			//returning responseEntity with UNAUTHORIZED HttpStatus 401
			return new ResponseEntity<List<Coupon>>(HttpStatus.UNAUTHORIZED);	
		}

	}	
	//----------------------------------------------------------------------------------------
	@GetMapping(value="getallcouponsbydate/{month}/{day}/{year}")
	public ResponseEntity<List<Coupon>> getAllCouponsByDate(@PathVariable String day , @PathVariable String month , @PathVariable String year )
	{
		if(getFacade())
		{
			String date = year+ "-" + month+  "-" + day;
			System.out.println(date);
			Date tempDate = converter.stringToDate(date);
			List<Coupon> allCoupons = (List<Coupon>) companyFacade.getCouponByDate(tempDate);

			//ArrayList<Coupon> allCoupons = (ArrayList<Coupon>) companyFacade.getCouponByDate(date);

			if(allCoupons == null)
				return new ResponseEntity<List<Coupon>>(HttpStatus.NO_CONTENT);

			System.out.println("coupons size : " + allCoupons.size());
			return new ResponseEntity<List<Coupon>>(allCoupons , HttpStatus.OK);
		}
		else
		{
			//returning responseEntity with UNAUTHORIZED HttpStatus 401
			return new ResponseEntity<List<Coupon>>(HttpStatus.UNAUTHORIZED);	
		}
	}
}
