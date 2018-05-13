package com.CouponSystem.Facades;

import java.sql.SQLException;

import com.CouponSystem.Entities.ClientType;
import com.CouponSystem.Exceptions.NullConnectionException;
import com.CouponSystem.Exceptions.WrongDataInputException;

public interface CouponClientFacade 
{
	public CouponClientFacade login(String name , String password , ClientType clientType) throws ClassNotFoundException , InterruptedException ,SQLException ,WrongDataInputException
	, NullConnectionException , NullPointerException;
}
