package xyz.morlotti.virtualbookcase.webapi.controllers;

import java.net.URI;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import xyz.morlotti.virtualbookcase.webapi.beans.BookDescription;
import xyz.morlotti.virtualbookcase.webapi.daos.custom.AdvancedSearch;
import xyz.morlotti.virtualbookcase.webapi.exceptions.APINotFoundException;
import xyz.morlotti.virtualbookcase.webapi.services.interfaces.BookDescriptionService;

@RestController
public class BookDescriptionController
{
	@Autowired
	BookDescriptionService bookDescriptionService;

	@RequestMapping(value = "/bookDescriptions", method = RequestMethod.GET)
	public Iterable<BookDescription> listBookDescriptions()
	{
		return bookDescriptionService.listBookDescriptions();
	}

	@RequestMapping(value = "/bookDescription/{id}", method = RequestMethod.GET)
	public BookDescription getBookDescription(@PathVariable int id)
	{
		Optional<BookDescription> optional = bookDescriptionService.getBookDescription(id);

		if(!optional.isPresent())
		{
			throw new APINotFoundException("Book description " + id + " not found");
		}

		return optional.get();
	}

	@RequestMapping(value = "/bookDescription", method = RequestMethod.POST)
	public ResponseEntity<Void> addBookDescription(@RequestBody BookDescription bookDescription)
	{
		BookDescription newBookDescription = bookDescriptionService.addBookDescription(bookDescription);

		URI location = ServletUriComponentsBuilder
		   .fromCurrentRequest()
		   .path("/{id}")
		   .buildAndExpand(newBookDescription.getId())
		   .toUri()
		;

		return ResponseEntity.created(location).build();
	}

	@RequestMapping(value = "/bookDescription/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> updateBookDescription(@PathVariable int id, @RequestBody BookDescription bookDescription)
	{
		BookDescription newBookDescription = bookDescriptionService.updateBookDescription(id, bookDescription);

		URI location = ServletUriComponentsBuilder
		   .fromCurrentRequest()
		   .path("/{id}")
		   .buildAndExpand(newBookDescription.getId())
		   .toUri()
		;

		return ResponseEntity.created(location).build();
	}

	@RequestMapping(value = "/bookDescription/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deleteBookDescription(@PathVariable int id)
	{
		bookDescriptionService.deleteBookDescription(id);

		return ResponseEntity.status(HttpStatus.OK).build();
	}

	@RequestMapping(value = "/bookDescription/search", method = RequestMethod.GET)
	public Iterable<BookDescription> searchBook(@ModelAttribute AdvancedSearch advancedSearch)
	{
		return bookDescriptionService.searchBookDescriptions(advancedSearch);
	}
}
