package com.CouponSystem.Exceptions;

/**
 * 
 * An exception that provides information on a wrong use in the purchaseCoupon method in the CustomerDBDAO
 *
 */
public class DuplicateEntryException extends Exception
{
	private static final long serialVersionUID = 1L;

	/**
	 * Constructs a new exception with the specified detail message.
	 * @param message message the detail message. The detail message is saved for later retrieval by the Throwable.getMessage() method.
	 */
	public DuplicateEntryException(String message)
	{
		super(message);
	}
}
