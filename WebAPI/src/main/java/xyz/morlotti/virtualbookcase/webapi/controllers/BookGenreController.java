package xyz.morlotti.virtualbookcase.webapi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import xyz.morlotti.virtualbookcase.webapi.beans.BookGenre;
import xyz.morlotti.virtualbookcase.webapi.services.interfaces.BookGenreService;

import java.net.URI;
import java.util.Optional;

@RestController
public class BookGenreController
{
	@Autowired
	BookGenreService bookGenreService;

	@RequestMapping(value="/bookGenres", method = RequestMethod.GET)
	public Iterable<BookGenre> listBookGenres()
	{
		return bookGenreService.listBookGenres();
	}

	@RequestMapping(value="/bookGenre/{id}", method = RequestMethod.GET)
	public Optional<BookGenre> getBookGenre(@PathVariable int id)
	{
		return bookGenreService.getBookGenre(id);
	}

	@RequestMapping(value = "/bookGenre", method = RequestMethod.POST)
	public ResponseEntity<Void> addBookGenre(@RequestBody BookGenre bookGenre)
	{
		BookGenre newBookGenre = bookGenreService.addBookGenre(bookGenre);

		URI location = ServletUriComponentsBuilder
			               .fromCurrentRequest()
			               .path("/{id}")
			               .buildAndExpand(newBookGenre.getId())
			               .toUri()
			;

		return ResponseEntity.created(location).build();
	}

	@RequestMapping(value="/bookGenre/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> updateBookGenre(@PathVariable int id, @RequestBody BookGenre bookGenre)
	{
		BookGenre newBookGenre = bookGenreService.updateBookGenre(id, bookGenre);

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
		bookGenreService.deleteBookGenre(id);

		return ResponseEntity.status(HttpStatus.OK).build();
	}
}
