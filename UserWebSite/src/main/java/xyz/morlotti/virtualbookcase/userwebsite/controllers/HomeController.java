package xyz.morlotti.virtualbookcase.userwebsite.controllers;

import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;

import xyz.morlotti.virtualbookcase.userwebsite.security.TokenUtils;

@Controller
public class HomeController
{
	@Autowired
	TokenUtils tokenUtils;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String homePage(@CookieValue(TokenUtils.TOKEN_COOKIE_NAME) String token, Model model)
	{
		TokenUtils.UserInfo userInfo = tokenUtils.getUserInfoFromJwtToken(token);

		model.addAttribute("userInfo", userInfo);

		return "home";
	}
}
