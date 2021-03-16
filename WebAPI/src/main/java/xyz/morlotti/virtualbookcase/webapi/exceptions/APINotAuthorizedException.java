package xyz.morlotti.virtualbookcase.webapi.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNAUTHORIZED)
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
