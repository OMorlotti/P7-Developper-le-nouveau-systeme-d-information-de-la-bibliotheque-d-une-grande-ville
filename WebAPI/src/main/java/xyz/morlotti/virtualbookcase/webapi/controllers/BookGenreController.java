package xyz.morlotti.virtualbookcase.webapi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import xyz.morlotti.virtualbookcase.webapi.beans.BookGenre;
import xyz.morlotti.virtualbookcase.webapi.daos.BookGenreDAO;
import xyz.morlotti.virtualbookcase.webapi.exceptions.APINotCreatedException;
import xyz.morlotti.virtualbookcase.webapi.exceptions.APINotDeletedException;
import xyz.morlotti.virtualbookcase.webapi.exceptions.APINotFoundException;

import java.net.URI;
import java.util.Optional;

@RestController
public class BookGenreController
{
	@Autowired
	private BookGenreDAO bookGenreDAO;

	@RequestMapping(value="/bookGenres", method = RequestMethod.GET)
	public Iterable<BookGenre> listBookGenres()
	{
		Iterable<BookGenre> bookGenres = bookGenreDAO.findAll();

		return bookGenres;
	}

	@RequestMapping(value="/bookGenre/{id}", method = RequestMethod.GET)
	public Optional<BookGenre> getBookGenre(@PathVariable int id)
	{
		Optional<BookGenre> optional = bookGenreDAO.findById(id);

		optional.orElseThrow(() -> new APINotFoundException("BookGenre " + id + " not found"));

		return optional;
	}

	@RequestMapping(value = "/bookGenre", method = RequestMethod.POST)
	public ResponseEntity<Void> addBookGenre(@RequestBody BookGenre bookGenre)
	{
		BookGenre newBookGenre = bookGenreDAO.save(bookGenre);

		if(newBookGenre == null)
		{
			throw new APINotCreatedException("BookGenre not inserted");
		}

		URI location = ServletUriComponentsBuilder
           .fromCurrentRequest()
           .path("/{id}")
           .buildAndExpand(newBookGenre.getId())
           .toUri()
		;

		return ResponseEntity.created(location).build();
	}

	@RequestMapping(value="/bookGenre/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deleteBookGenre(@PathVariable int id)
	{
		try
		{
			bookGenreDAO.deleteById(id);

			return ResponseEntity.status(HttpStatus.OK).build();
		}
		catch(EmptyResultDataAccessException e)
		{
			throw new APINotDeletedException("BookGenre " + id + " not deleted: " + e.getMessage());
		}
	}
}
