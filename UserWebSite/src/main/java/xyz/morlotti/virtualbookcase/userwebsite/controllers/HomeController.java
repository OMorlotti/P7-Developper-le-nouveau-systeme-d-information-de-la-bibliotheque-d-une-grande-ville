package xyz.morlotti.virtualbookcase.userwebsite.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import xyz.morlotti.virtualbookcase.userwebsite.security.TokenUtils;
import xyz.morlotti.virtualbookcase.userwebsite.security.UserInfo;

@Controller
public class HomeController
{
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String homePage(@CookieValue(TokenUtils.TOKEN_COOKIE_NAME) String token, Model model)
	{
		UserInfo userInfo = TokenUtils.getUserInfoFromJwtToken(token);

		model.addAttribute("userInfo", userInfo);

		return "home";
	}
}
