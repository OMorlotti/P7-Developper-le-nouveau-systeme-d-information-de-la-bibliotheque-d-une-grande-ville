package xyz.morlotti.virtualbookcase.webapi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import xyz.morlotti.virtualbookcase.webapi.beans.Book;
import xyz.morlotti.virtualbookcase.webapi.daos.BookDAO;
import xyz.morlotti.virtualbookcase.webapi.exceptions.APINotCreatedException;
import xyz.morlotti.virtualbookcase.webapi.exceptions.APINotDeletedException;
import xyz.morlotti.virtualbookcase.webapi.exceptions.APINotFoundException;

import java.net.URI;
import java.util.Optional;

@RestController
public class BookController
{
	@Autowired
	private BookDAO bookDAO;

	@RequestMapping(value="/books", method = RequestMethod.GET)
	public Iterable<Book> listBooks()
	{
		Iterable<Book> books = bookDAO.findAll();

		return books;
	}

	@RequestMapping(value="/book/{id}", method = RequestMethod.GET)
	public Optional<Book> getBook(@PathVariable int id) // optional : si la variable 'book' ne retourne finalement rien, "optional" évite de gérer l'exception levée.
	{
		Optional<Book> optional = bookDAO.findById(id);

		optional.orElseThrow(() -> new APINotFoundException("Book " + id + " not found"));

		return optional;
	}

	@RequestMapping(value = "/book", method = RequestMethod.POST)
	public ResponseEntity<Void> addBook(@RequestBody Book book)
	{
		Book newBook = bookDAO.save(book);

		if(newBook == null)
		{
			throw new APINotCreatedException("Book not inserted");
		}

		URI location = ServletUriComponentsBuilder
           .fromCurrentRequest()
           .path("/{id}")
           .buildAndExpand(newBook.getId())
           .toUri()
		;

		return ResponseEntity.created(location).build();
	}

	@RequestMapping(value="/book/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> updateBook(@PathVariable int id, @RequestBody Book book)
	{
		Book existingBook = getBook(id).get();

		existingBook.setAvailable(book.isAvailable());

		existingBook.setCondition(book.getCondition());

		return addBook(existingBook);
	}

	@RequestMapping(value="/book/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deleteBook(@PathVariable int id)
	{
		try
		{
			bookDAO.deleteById(id);

			return ResponseEntity.status(HttpStatus.OK).build();
		}
		catch(EmptyResultDataAccessException e)
		{
			throw new APINotDeletedException("Book " + id + " not deleted: " + e.getMessage());
		}
	}
}
