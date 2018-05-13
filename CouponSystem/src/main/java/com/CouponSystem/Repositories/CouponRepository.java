package com.CouponSystem.Repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.CouponSystem.Entities.Coupon;
import com.CouponSystem.Entities.CouponType;

public interface CouponRepository extends CrudRepository<Coupon, Integer>
{
	//------------------------------------------------query named methods---------------------------------------------------
	public Coupon findByTitle(String title);
		
	public List<Coupon> findByType(CouponType type);

	public List<Coupon> findByEndDateBefore(Date endDate);
	
	public Coupon findByIdAndAmountGreaterThan(int id , int amount);
	
	public Coupon findByIdAndEndDateAfter(int id , Date endDate);
	//public Coupon findByIdAndEndDateGreaterThanEqual(int id , Date endDate);

	
}
	