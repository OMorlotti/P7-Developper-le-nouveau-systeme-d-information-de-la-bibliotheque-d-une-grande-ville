package xyz.morlotti.virtualbookcase.userwebsite.controllers;

import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;

import xyz.morlotti.virtualbookcase.userwebsite.MyFeignProxy;
import xyz.morlotti.virtualbookcase.userwebsite.security.TokenUtils;
import xyz.morlotti.virtualbookcase.userwebsite.beans.BookDescription;

@Controller
public class BookDescriptionController
{
	@Autowired
	MyFeignProxy feignProxy;

	@Autowired
	TokenUtils tokenUtils;

	@RequestMapping(value = "/bookDescription/{id}", method = RequestMethod.GET)
	public String book(@CookieValue(TokenUtils.TOKEN_COOKIE_NAME) String token, @PathVariable("id") int id, Model model)
	{
		TokenUtils.UserInfo userInfo = tokenUtils.getUserInfoFromJwtToken(token);

		model.addAttribute("userInfo", userInfo);

		try
		{
			BookDescription bookDescription = feignProxy.getBookDescription(id);

			model.addAttribute("bookDescription", bookDescription);

			return "bookDescription";
		}
		catch(Exception e)
		{
			model.addAttribute("messageType", "danger");
			model.addAttribute("message", "Livre #" + id + " inconnu.");

			return "error";
		}
	}
}
