package xyz.morlotti.virtualbookcase.webapi;

public class BeanException extends Exception
{
	public BeanException(String message)
	{
		super(message);
	}

	public BeanException(Throwable cause)
	{
		super(cause);
	}

	public BeanException(String message, Throwable cause)
	{
		super(message, cause);
	}
}
