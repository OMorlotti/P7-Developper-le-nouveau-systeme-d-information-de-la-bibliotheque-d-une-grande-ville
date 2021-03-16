package xyz.morlotti.virtualbookcase.webapi.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
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
