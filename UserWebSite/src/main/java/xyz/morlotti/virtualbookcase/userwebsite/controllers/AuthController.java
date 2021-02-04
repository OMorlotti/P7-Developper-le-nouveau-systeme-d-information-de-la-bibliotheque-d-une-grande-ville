package xyz.morlotti.virtualbookcase.userwebsite.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AuthController
{
	@RequestMapping(value="/login", method = RequestMethod.GET)
	public String login()
	{
		return "login";
	}

	@RequestMapping(value="/remind-password", method = RequestMethod.GET)
	public String remindPassword()
	{
		return "remind-password";
	}
}
