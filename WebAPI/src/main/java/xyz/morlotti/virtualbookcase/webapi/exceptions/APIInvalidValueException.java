package xyz.morlotti.virtualbookcase.webapi.exceptions;

public class APIInvalidValueException extends RuntimeException
{
	public APIInvalidValueException(String message)
	{
		super(message);
	}

	public APIInvalidValueException(Throwable cause)
	{
		super(cause);
	}

	public APIInvalidValueException(String message, Throwable cause)
	{
		super(message, cause);
	}
}
