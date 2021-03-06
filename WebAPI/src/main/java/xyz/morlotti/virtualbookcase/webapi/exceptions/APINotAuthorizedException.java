package xyz.morlotti.virtualbookcase.webapi.exceptions;

public class APINotAuthorizedException extends RuntimeException
{
	public APINotAuthorizedException(String message)
	{
		super(message);
	}

	public APINotAuthorizedException(Throwable cause)
	{
		super(cause);
	}

	public APINotAuthorizedException(String message, Throwable cause)
	{
		super(message, cause);
	}
}
