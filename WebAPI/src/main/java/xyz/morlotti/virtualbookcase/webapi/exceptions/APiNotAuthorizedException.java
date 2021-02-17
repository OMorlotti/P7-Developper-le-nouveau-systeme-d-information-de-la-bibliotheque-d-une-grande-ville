package xyz.morlotti.virtualbookcase.webapi.exceptions;

public class APiNotAuthorizedException extends RuntimeException
{
	public APiNotAuthorizedException(String message)
	{
		super(message);
	}

	public APiNotAuthorizedException(Throwable cause)
	{
		super(cause);
	}

	public APiNotAuthorizedException(String message, Throwable cause)
	{
		super(message, cause);
	}
}
