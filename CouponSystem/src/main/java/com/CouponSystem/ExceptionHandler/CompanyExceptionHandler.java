package com.CouponSystem.ExceptionHandler;

import com.CouponSystem.Exceptions.ExceptionType;

/**
 * 
 * the CompanyExceptionHandler class identify the exception been thrown by a method in the CompanyDBDAO class
 * and resolves it accordingly.
 *
 */
public class CompanyExceptionHandler 
{

	/**
	 *  runs the input exception thrown by the company DBDAO in a switch case method and according to the type of
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
			System.out.println("company class does not exist");
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
		case DuplicateEntryException :
			System.out.println(e.getMessage());
			System.out.println("can't create company, databease already contains this company's name");
			System.out.println("-----------------------------------------------------------------------------------------------");
			break;
		case WrongDataInputException :
			System.out.println(e.getMessage());
			System.out.println("either the company name or the password is wrong - can't login!");
			System.out.println("-----------------------------------------------------------------------------------------------");
			break;
		case NullConnectionException :
			System.out.println(e.getMessage());
			System.out.println("your connection is null - the system might be shutting down!");
			System.out.println("-----------------------------------------------------------------------------------------------");
			break;
		case NullPointerException :
			System.out.println(e.getMessage());
			System.out.println("company is not found -null-");
			break;
		default:
			System.out.println(e.getMessage());
			System.out.println("Company error happened");
			System.out.println("-----------------------------------------------------------------------------------------------");
			break;
		}
	}
}