package com.CouponSystem.ExceptionHandler;

import com.CouponSystem.Exceptions.ExceptionType;

/**
 * 
 * the GeneralExceptionHandler class identify the exception been thrown by a method in any of the DBDAO classes
 * and resolves it accordingly.
 * it is used for methods been called by a non-user class (i.e automated class/threads). 
 *
 */
public class GeneralExceptionHandler
{
	/**
	 * runs the input exception thrown in a switch case method and according to the type of
	 * exception it handles it accordingly
	 * @param e a given exception
	 */
	public static void handle(Exception e)
	{
		String exceptions[] = e.getClass().toString().split( "\\." );
		String exceptionClass = exceptions[exceptions.length - 1];
		ExceptionType exceptionType = ExceptionType.valueOf(exceptionClass);

		switch(exceptionType)
		{
		case ClassNotFoundException :
			System.out.println(e.getCause());
			System.out.println("class does not exist");
			System.out.println("-----------------------------------------------------------------------------------------------");
			break;
		case SQLException :
			System.out.println(e.getCause());
			System.out.println("cannot connect to mysql");
			System.out.println("-----------------------------------------------------------------------------------------------");
			break;
		case InterruptedException :
			System.out.println(e.getCause());
			System.out.println("the thread has been interrupted - the system might be shutting down");
			System.out.println("-----------------------------------------------------------------------------------------------");
			break;
		case ParseException :
			System.out.println(e.getCause());
			System.out.println("the date has been entered in the wrong format");
			System.out.println("enter the date in the yy-mm-dd format");
			System.out.println("-----------------------------------------------------------------------------------------------");
			break;
		case NullConnectionException :
			System.out.println(e.getMessage());
			System.out.println("your connection is null - the system might be shutting down!");
			System.out.println("-----------------------------------------------------------------------------------------------");
			break;
		case NullPointerException :
			System.out.println(e.getMessage());
			System.out.println("pointer to object is not found -null-");
			break;
		default:
			System.out.println(e.getMessage());
			System.out.println("general error happened");
			System.out.println("-----------------------------------------------------------------------------------------------");
			break;
		}
	}
}