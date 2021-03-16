package xyz.morlotti.virtualbookcase.webapi.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_MODIFIED)
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
