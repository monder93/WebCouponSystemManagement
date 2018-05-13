package com.CouponSystem.Queries;

public class companyQueries 
{
	
    public final static String FIND_COMPANY_COUPONS_BY_COUPONTYPE_QUERY = "SELECT coup " + 
            "FROM Company c LEFT JOIN c.coupons coup " +
           "WHERE c.id = :comp_id AND coup.type = :couponType ";
    
    public final static String FIND_COMPANY_COUPONS_BY_PRICE_QUERY = "SELECT coup " + 
            "FROM Company c LEFT JOIN c.coupons coup " +
           "WHERE c.id = :comp_id AND coup.price <= :price ";
    
    public final static String FIND_COMPANY_COUPONS_BY_DATE_QUERY = "SELECT coup " + 
            "FROM Company c LEFT JOIN c.coupons coup " +
           "WHERE c.id = :comp_id AND coup.endDate <= :endDate ";

}
