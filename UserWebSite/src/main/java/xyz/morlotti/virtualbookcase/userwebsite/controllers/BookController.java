package xyz.morlotti.virtualbookcase.userwebsite.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import xyz.morlotti.virtualbookcase.userwebsite.beans.Book;
import xyz.morlotti.virtualbookcase.userwebsite.MyFeignProxy;

import java.util.Optional;

@Controller
public class BookController
{
	@Autowired
	MyFeignProxy feignProxy;

	@RequestMapping(value="/book/{id}", method = RequestMethod.GET)
	public String book(@PathVariable("id") int id, Model model)
	{
		try
		{
			Book book = feignProxy.getBook(id);

			model.addAttribute("book", book);

			return "book";
		}
		catch(Exception e)
		{
			model.addAttribute("messageType", "danger");
			model.addAttribute("message", "Livre inconnu : " + e.getMessage());

			return "error";
		}
	}

	@RequestMapping(value="/search", method = RequestMethod.GET)
	public String search()
	{
		return "search";
	}
}
