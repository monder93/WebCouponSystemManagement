package com.CouponSystem.Queries;

public class customerQueries 
{
	
    public final static String FIND_CUSTOMER_COUPONS_BY_COUPONTYPE_QUERY = "SELECT coup " + 
            "FROM Customer c LEFT JOIN c.coupons coup " +
           "WHERE c.id = :cust_id AND coup.type = :couponType ";
    
    public final static String FIND_CUSTOMER_COUPONS_BY_PRICE_QUERY = "SELECT coup " + 
            "FROM Customer c LEFT JOIN c.coupons coup " +
           "WHERE c.id = :cust_id AND coup.price <= :price ";
    
    public final static String FIND_CUSTOMER_COUPONS_BY_DATE_QUERY = "SELECT coup " + 
            "FROM Customer c LEFT JOIN c.coupons coup " +
           "WHERE c.id = :cust_id AND coup.endDate <= :endDate ";
    
    public final static String FIND_CUSTOMER_COUPON_BY_TITLE_QUERY = "SELECT coup " +
    		"FROM Customer c LEFT JOIN c.coupons coup " +
    		"WHERE c.id = :cust_id AND coup.title = :title ";

}
