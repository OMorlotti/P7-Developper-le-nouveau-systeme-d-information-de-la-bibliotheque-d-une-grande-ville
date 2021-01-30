package xyz.morlotti.virtualbookcase.webapi.exceptions;

public class APINotFoundException extends RuntimeException
{
	public APINotFoundException(String message)
	{
		super(message);
	}

	public APINotFoundException(Throwable cause)
	{
		super(cause);
	}

	public APINotFoundException(String message, Throwable cause)
	{
		super(message, cause);
	}
}
