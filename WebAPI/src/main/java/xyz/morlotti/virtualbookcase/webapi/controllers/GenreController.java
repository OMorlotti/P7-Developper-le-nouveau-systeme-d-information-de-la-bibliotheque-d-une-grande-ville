package xyz.morlotti.virtualbookcase.webapi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import xyz.morlotti.virtualbookcase.webapi.beans.Genre;
import xyz.morlotti.virtualbookcase.webapi.daos.GenreDAO;

import java.net.URI;
import java.util.Optional;

@RestController
public class GenreController
{
	@Autowired
	private GenreDAO genreDAO;

	@RequestMapping(value="/genres", method = RequestMethod.GET)
	public Iterable<Genre> listGenres()
	{
		Iterable<Genre> genres = genreDAO.findAll();

		return genres;
	}

	@RequestMapping(value="/genre/{id}", method = RequestMethod.GET)
	public Optional<Genre> getGenre(@PathVariable int id)
	{
		Optional<Genre> genre = genreDAO.findById(id);

		return genre;
	}

	@RequestMapping(value = "/genre", method = RequestMethod.POST)
	public ResponseEntity<Void> addGenre(@RequestBody Genre genre)
	{
		Genre newGenre = genreDAO.save(genre);

		if(newGenre == null)
		{
			return ResponseEntity.noContent().build();
		}

		URI location = ServletUriComponentsBuilder
           .fromCurrentRequest()
           .path("/{id}")
           .buildAndExpand(newGenre.getId())
           .toUri()
		;

		return ResponseEntity.created(location).build();
	}

	@RequestMapping(value="/genre/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> updateGenre(@PathVariable int id, @RequestBody Genre genre)
	{
		Optional<Genre> optional = genreDAO.findById(id);

		if(!optional.isPresent())
		{
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}

		Genre existingGenre = optional.get();

		existingGenre.setName(genre.getName());

		return addGenre(existingGenre);
	}

	@RequestMapping(value="/genre/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deleteGenre(@PathVariable int id)
	{
		try
		{
			genreDAO.deleteById(id);

			return ResponseEntity.status(HttpStatus.OK).build();
		}
		catch(EmptyResultDataAccessException e)
		{
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}

}
