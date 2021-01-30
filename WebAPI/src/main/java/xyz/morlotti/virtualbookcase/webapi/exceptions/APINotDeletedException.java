package xyz.morlotti.virtualbookcase.webapi.exceptions;

public class APINotDeletedException extends RuntimeException
{
	public APINotDeletedException(String message)
	{
		super(message);
	}

	public APINotDeletedException(Throwable cause)
	{
		super(cause);
	}

	public APINotDeletedException(String message, Throwable cause)
	{
		super(message, cause);
	}
}
