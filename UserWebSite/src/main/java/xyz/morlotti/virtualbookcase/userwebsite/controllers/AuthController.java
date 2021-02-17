package xyz.morlotti.virtualbookcase.userwebsite.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import xyz.morlotti.virtualbookcase.userwebsite.MyFeignProxy;
import xyz.morlotti.virtualbookcase.userwebsite.beans.forms.Auth;
import xyz.morlotti.virtualbookcase.userwebsite.beans.forms.Credentials;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
public class AuthController
{
	@Autowired
	MyFeignProxy feignProxy;

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login()
	{
		return "login";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST, consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
	public String login(Credentials credentials, Model model, HttpServletResponse httpServletResponse)
	{
		try
		{
			String token = feignProxy.login(credentials.getLogin(), credentials.getPassword());

			Cookie cookie = new Cookie("token", token);
			cookie.setMaxAge(60 * 60 * 24 * 30);

			httpServletResponse.addCookie(cookie);

			return "home";
		}
		catch(Exception e)
		{
			model.addAttribute("messageType", "danger");
			model.addAttribute("message", e.getMessage());

			return "login";
		}
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpServletResponse httpServletResponse)
	{
		Cookie cookie = new Cookie("auth", null);
		cookie.setMaxAge(60 * 60 * 24 * 30);

		httpServletResponse.addCookie(cookie);

		return "home";
	}

	@RequestMapping(value="/remind-password", method = RequestMethod.GET)
	public String remindPassword()
	{
		return "remind-password";
	}
}
