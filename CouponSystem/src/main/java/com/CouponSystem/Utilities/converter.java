package com.CouponSystem.Utilities;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.CouponSystem.ExceptionHandler.GeneralExceptionHandler;

public class converter 
{
	
	// this class have 2 static functions to convert between Date && String 
	
	private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
	
	//--------------------------------------------------------------------------------------------------------
	public static String dateToString(Date date)
	{
		String tempDate = simpleDateFormat.format(date);
		return tempDate;
	}
	
	//--------------------------------------------------------------------------------------------------------
	
	public static Date stringToDate(String date)
	{
		Date tempDate=null;
		try
		{
			tempDate = simpleDateFormat.parse(date);
		}
		catch (ParseException e) 
		{
			GeneralExceptionHandler.handle(e);
		}
		return tempDate;
	}
}
