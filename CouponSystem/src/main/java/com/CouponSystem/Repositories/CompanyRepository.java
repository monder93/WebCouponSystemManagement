package com.CouponSystem.Repositories;

//----------------------importing the static strings from com.CouponSystem.Queries.companyQueries--------------------------
import static com.CouponSystem.Queries.companyQueries.FIND_COMPANY_COUPONS_BY_COUPONTYPE_QUERY;
import static com.CouponSystem.Queries.companyQueries.FIND_COMPANY_COUPONS_BY_DATE_QUERY;
import static com.CouponSystem.Queries.companyQueries.FIND_COMPANY_COUPONS_BY_PRICE_QUERY;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.CouponSystem.Entities.Company;
import com.CouponSystem.Entities.Coupon;
import com.CouponSystem.Entities.CouponType;

public interface CompanyRepository extends CrudRepository<Company, Integer> 
{	

	//------------------------------------------------query named methods---------------------------------------------------
	public Company findBycompName(String compName);

	public Company findBycompNameAndPassword(String compName , String password);

	//------------------------------------------------using @Query and @Param------------------------------------------------

	@Query(FIND_COMPANY_COUPONS_BY_COUPONTYPE_QUERY)
	public List<Coupon> findByCouponType(@Param("comp_id") int comp_id,@Param("couponType") CouponType couponType);

	@Query(FIND_COMPANY_COUPONS_BY_PRICE_QUERY)
	public List<Coupon> findByCouponPrice(@Param("comp_id") int comp_id,@Param("price") double price);

	@Query(FIND_COMPANY_COUPONS_BY_DATE_QUERY)
	public List<Coupon> findByCouponDate(@Param("comp_id") int comp_id,@Param("endDate") Date endDate);
	
}

