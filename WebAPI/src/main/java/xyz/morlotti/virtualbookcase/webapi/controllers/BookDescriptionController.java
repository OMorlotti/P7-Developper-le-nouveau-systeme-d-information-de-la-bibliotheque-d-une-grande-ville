package xyz.morlotti.virtualbookcase.webapi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import xyz.morlotti.virtualbookcase.webapi.beans.BookDescription;
import xyz.morlotti.virtualbookcase.webapi.daos.BookDescriptionDAO;
import xyz.morlotti.virtualbookcase.webapi.exceptions.APINotCreatedException;
import xyz.morlotti.virtualbookcase.webapi.exceptions.APINotDeletedException;
import xyz.morlotti.virtualbookcase.webapi.exceptions.APINotFoundException;

import java.net.URI;
import java.util.Optional;

@RestController
public class BookDescriptionController
{
	@Autowired
	private BookDescriptionDAO bookDescriptionDAO;

	@RequestMapping(value="/bookdescription", method = RequestMethod.GET)
	public Iterable<BookDescription> listBookDescription
		()
	{
		Iterable<BookDescription> bookDescriptions = bookDescriptionDAO.findAll();

		return bookDescriptions;
	}

	@RequestMapping(value="/bookDescription/{id}", method = RequestMethod.GET)
	public Optional<BookDescription> getBookDescription(@PathVariable int id)
	{
		Optional<BookDescription> optional = bookDescriptionDAO.findById(id);

		optional.orElseThrow(() -> new APINotFoundException("BookDescription " + id + " not found"));

		return optional;
	}

	@RequestMapping(value = "/book", method = RequestMethod.POST)
	public ResponseEntity<Void> addBookDescription(@RequestBody BookDescription book)
	{
		BookDescription newBookDescription = bookDescriptionDAO.save(book);

		if(newBookDescription == null)
		{
			throw new APINotCreatedException("BookDescription not inserted");
		}

		URI location = ServletUriComponentsBuilder
           .fromCurrentRequest()
           .path("/{id}")
           .buildAndExpand(newBookDescription.getId())
           .toUri()
		;

		return ResponseEntity.created(location).build();
	}

	@RequestMapping(value="/bookdescription/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> updateBookDescription(@PathVariable int id, @RequestBody BookDescription book)
	{
		BookDescription existingBookDescription = getBookDescription(id).get();

		existingBookDescription.setAuthorFirstname(book.getAuthorFirstname());

		existingBookDescription.setAuthorLastname(book.getAuthorLastname());

		existingBookDescription.setComment(book.getComment());

		existingBookDescription.setEdition(book.getEdition());

		existingBookDescription.setEditionYear(book.getEditionYear());

		existingBookDescription.setEditor(book.getEditor());

		existingBookDescription.setFormat(book.getFormat());

		existingBookDescription.setIsbn(book.getIsbn());

		existingBookDescription.setTitle(book.getTitle());

		return addBookDescription(existingBookDescription);
	}

	@RequestMapping(value="/book/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deleteBookDescription(@PathVariable int id)
	{
		try
		{
			bookDescriptionDAO.deleteById(id);

			return ResponseEntity.status(HttpStatus.OK).build();
		}
		catch(EmptyResultDataAccessException e)
		{
			throw new APINotDeletedException("BookDescription " + id + " not deleted: " + e.getMessage());
		}
	}
}
