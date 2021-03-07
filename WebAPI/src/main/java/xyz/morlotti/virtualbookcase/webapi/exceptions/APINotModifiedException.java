package xyz.morlotti.virtualbookcase.webapi.exceptions;

public class APINotModifiedException extends RuntimeException
{
	public APINotModifiedException(String message)
	{
		super(message);
	}

	public APINotModifiedException(Throwable cause)
	{
		super(cause);
	}

	public APINotModifiedException(String message, Throwable cause)
	{
		super(message, cause);
	}
}
