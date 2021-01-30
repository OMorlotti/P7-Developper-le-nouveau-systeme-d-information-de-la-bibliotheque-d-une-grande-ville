package xyz.morlotti.virtualbookcase.webapi.exceptions;

public class APINotCreatedException extends RuntimeException
{
	public APINotCreatedException(String message)
	{
		super(message);
	}

	public APINotCreatedException(Throwable cause)
	{
		super(cause);
	}

	public APINotCreatedException(String message, Throwable cause)
	{
		super(message, cause);
	}
}
