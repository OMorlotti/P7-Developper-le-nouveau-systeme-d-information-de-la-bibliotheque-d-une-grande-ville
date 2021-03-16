package xyz.morlotti.virtualbookcase.webapi.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
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
