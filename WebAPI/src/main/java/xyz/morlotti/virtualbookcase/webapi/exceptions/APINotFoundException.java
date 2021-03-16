package xyz.morlotti.virtualbookcase.webapi.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
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
