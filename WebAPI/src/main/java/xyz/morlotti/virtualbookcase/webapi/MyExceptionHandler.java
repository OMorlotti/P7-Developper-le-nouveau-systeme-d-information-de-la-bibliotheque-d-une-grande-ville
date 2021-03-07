package xyz.morlotti.virtualbookcase.webapi;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import xyz.morlotti.virtualbookcase.webapi.exceptions.APINotFoundException;
import xyz.morlotti.virtualbookcase.webapi.exceptions.APINotModifiedException;
import xyz.morlotti.virtualbookcase.webapi.exceptions.APIInvalidValueException;
import xyz.morlotti.virtualbookcase.webapi.exceptions.APINotAuthorizedException;

@ControllerAdvice
public class MyExceptionHandler extends ResponseEntityExceptionHandler
{
	@ExceptionHandler(UsernameNotFoundException.class)
	public void springHandleUsernameNotFound(HttpServletResponse response) throws IOException
	{
		response.sendError(HttpStatus.UNAUTHORIZED.value());
	}

	@ExceptionHandler(APINotAuthorizedException.class)
	public void springHandleNotAuthorized(HttpServletResponse response) throws IOException
	{
		response.sendError(HttpStatus.UNAUTHORIZED.value());
	}

	@ExceptionHandler(APINotFoundException.class)
	public void springHandleNotFound(HttpServletResponse response) throws IOException
	{
		response.sendError(HttpStatus.NOT_FOUND.value());
	}

	@ExceptionHandler(APINotModifiedException.class)
	public void springHandleNotModified(HttpServletResponse response) throws IOException
	{
		response.sendError(HttpStatus.NOT_MODIFIED.value());
	}

	@ExceptionHandler(APIInvalidValueException.class)
	public void springHandleInvalidValue(HttpServletResponse response) throws IOException
	{
		response.sendError(HttpStatus.BAD_REQUEST.value());
	}
}
