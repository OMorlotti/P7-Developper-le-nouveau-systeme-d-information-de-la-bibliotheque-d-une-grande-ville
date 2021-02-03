package xyz.morlotti.virtualbookcase.webapi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import xyz.morlotti.virtualbookcase.webapi.beans.Genre;
import xyz.morlotti.virtualbookcase.webapi.services.interfaces.GenreService;

import java.net.URI;
import java.util.Optional;

@RestController
public class GenreController
{
	@Autowired
	GenreService genreService;

	@RequestMapping(value="/genres", method = RequestMethod.GET)
	public Iterable<Genre> listGenres()
	{
		return genreService.listGenres();
	}

	@RequestMapping(value="/genre/{id}", method = RequestMethod.GET)
	public Optional<Genre> getGenre(@PathVariable int id)
	{
		return genreService.getGenre(id);
	}

	@RequestMapping(value = "/genre", method = RequestMethod.POST)
	public ResponseEntity<Void> addGenre(@RequestBody Genre genre)
	{
		Genre newGenre = genreService.addGenre(genre);

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
		Genre newGenre = genreService.updateGenre(id, genre);

		URI location = ServletUriComponentsBuilder
           .fromCurrentRequest()
           .path("/{id}")
           .buildAndExpand(newGenre.getId())
           .toUri()
		;

		return ResponseEntity.created(location).build();
	}

	@RequestMapping(value="/genre/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deleteGenre(@PathVariable int id)
	{
		genreService.deleteGenre(id);

		return ResponseEntity.status(HttpStatus.OK).build();
	}
}
