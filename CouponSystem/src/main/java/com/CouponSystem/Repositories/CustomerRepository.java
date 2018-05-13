package com.CouponSystem.Repositories;

//----------------------importing the static strings from com.CouponSystem.Queries.companyQueries--------------------------
import static com.CouponSystem.Queries.customerQueries.FIND_CUSTOMER_COUPONS_BY_COUPONTYPE_QUERY;
import static com.CouponSystem.Queries.customerQueries.FIND_CUSTOMER_COUPONS_BY_DATE_QUERY;
import static com.CouponSystem.Queries.customerQueries.FIND_CUSTOMER_COUPONS_BY_PRICE_QUERY;
import static com.CouponSystem.Queries.customerQueries.FIND_CUSTOMER_COUPON_BY_TITLE_QUERY;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.CouponSystem.Entities.Coupon;
import com.CouponSystem.Entities.CouponType;
import com.CouponSystem.Entities.Customer;

public interface CustomerRepository extends CrudRepository<Customer, Integer>
{

	//--------------------------------findBy----------------query named methods---------------------------------------------------

	public Customer findByCouponsAmount(int x);
	
	public Customer findByCouponsTitleContaining(String couponTitle);
	
	public Customer findByCouponsContaining(Coupon coupon);
	
	public Customer findByCustName(String custName);
	
	public Customer findByCustNameAndPassword(String custName , String password);
		
	//------------------------------------------------using @Query and @Param------------------------------------------------
	
		@Query(FIND_CUSTOMER_COUPONS_BY_COUPONTYPE_QUERY)
		public List<Coupon> findByCouponType(@Param("cust_id") int cust_id,@Param("couponType") CouponType couponType);

		@Query(FIND_CUSTOMER_COUPONS_BY_PRICE_QUERY)
		public List<Coupon> findByCouponPrice(@Param("cust_id") int cust_id,@Param("price") double price);

		@Query(FIND_CUSTOMER_COUPONS_BY_DATE_QUERY)
		public List<Coupon> findByCouponDate(@Param("cust_id") int cust_id,@Param("endDate") Date endDate);
		
		@Query(FIND_CUSTOMER_COUPON_BY_TITLE_QUERY)
		public Coupon findByIdAndTitle(@Param("cust_id") int cust_id,@Param("title") String title );
		
		
}
