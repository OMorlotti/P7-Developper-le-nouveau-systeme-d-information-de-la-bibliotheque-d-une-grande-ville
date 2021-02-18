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
import xyz.morlotti.virtualbookcase.userwebsite.security.TokenUtils;
import xyz.morlotti.virtualbookcase.userwebsite.security.UserInfo;

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

			UserInfo userInfo = TokenUtils.getUserInfoFromJwtToken(token);

			TokenUtils.createTokenCookie(httpServletResponse, token);

			model.addAttribute("userInfo", userInfo);

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
		TokenUtils.deleteTokenCookie(httpServletResponse);

		return "home";
	}

	@RequestMapping(value="/remind-password", method = RequestMethod.GET)
	public String remindPassword()
	{
		return "remind-password";
	}
}
