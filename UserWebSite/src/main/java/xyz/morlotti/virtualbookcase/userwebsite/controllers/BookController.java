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
		Optional<Book> optional = feignProxy.getBook(id);

		if(optional.isPresent())
		{
			model.addAttribute("book", optional.get());
		}

		return "book";
	}

	@RequestMapping(value="/search", method = RequestMethod.GET)
	public String search()
	{
		return "search";
	}
}
