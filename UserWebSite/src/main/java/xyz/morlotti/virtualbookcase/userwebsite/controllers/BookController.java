package xyz.morlotti.virtualbookcase.userwebsite.controllers;

import org.springframework.ui.Model;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;

import xyz.morlotti.virtualbookcase.userwebsite.beans.Book;
import xyz.morlotti.virtualbookcase.userwebsite.MyFeignProxy;
import xyz.morlotti.virtualbookcase.userwebsite.beans.forms.Search;
import xyz.morlotti.virtualbookcase.userwebsite.security.TokenUtils;

@Controller
public class BookController
{
	@Autowired
	MyFeignProxy feignProxy;

	@Autowired
	TokenUtils tokenUtils;

	@RequestMapping(value="/book/{id}", method = RequestMethod.GET)
	public String book(@CookieValue(TokenUtils.TOKEN_COOKIE_NAME) String token, @PathVariable("id") int id, Model model)
	{
		TokenUtils.UserInfo userInfo = tokenUtils.getUserInfoFromJwtToken(token);

		model.addAttribute("userInfo", userInfo);

		try
		{
			Book book = feignProxy.getBook(id);

			model.addAttribute("book", book);

			return "book";
		}
		catch(Exception e)
		{
			model.addAttribute("messageType", "danger");
			model.addAttribute("message", "Livre #" + id + " inconnu.");

			return "error";
		}
	}

	@RequestMapping(value="/search", method = RequestMethod.GET)
	public String search1(@CookieValue(TokenUtils.TOKEN_COOKIE_NAME) String token, Model model)
	{
		TokenUtils.UserInfo userInfo = tokenUtils.getUserInfoFromJwtToken(token);

		model.addAttribute("userInfo", userInfo);
		model.addAttribute("showResult", false);

		return "search";
	}

	@RequestMapping(value="/search", method = RequestMethod.POST, consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
	public String search2(@CookieValue(TokenUtils.TOKEN_COOKIE_NAME) String token, Search search, Model model)
	{
		TokenUtils.UserInfo userInfo = tokenUtils.getUserInfoFromJwtToken(token);

		model.addAttribute("userInfo", userInfo);
		model.addAttribute("showResult", true);

		try
		{
			Iterable<Book> books = feignProxy.searchBook(search);

			model.addAttribute("books", books);
		}
		catch(Exception e)
		{
			model.addAttribute("messageType", "danger");
			model.addAttribute("message", "Erreur interne : " + e.getMessage() + ".");
		}

		return "search";
	}
}
