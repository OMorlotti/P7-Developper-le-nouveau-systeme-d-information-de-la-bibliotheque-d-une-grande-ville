package xyz.morlotti.virtualbookcase.webapi.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import xyz.morlotti.virtualbookcase.webapi.beans.Genre;
import xyz.morlotti.virtualbookcase.webapi.daos.GenreDAO;
import xyz.morlotti.virtualbookcase.webapi.exceptions.APINotCreatedException;
import xyz.morlotti.virtualbookcase.webapi.exceptions.APINotDeletedException;
import xyz.morlotti.virtualbookcase.webapi.exceptions.APINotFoundException;
import xyz.morlotti.virtualbookcase.webapi.services.interfaces.GenreService;

import java.util.Optional;

@Service
public class GenreServiceImpl implements GenreService
{
	@Autowired
	private GenreDAO genreDAO;

	public Iterable<Genre> listGenres()
	{
		Iterable<Genre> genres = genreDAO.findAll();

		return genres;
	}

	public Optional<Genre> getGenre(int id)
	{
		Optional<Genre> optional = genreDAO.findById(id);

		optional.orElseThrow(() -> new APINotFoundException("Genre " + id + " not found"));

		return optional;
	}

	public Genre addGenre(Genre genre)
	{
		Genre newGenre = genreDAO.save(genre);

		if(newGenre == null)
		{
			throw new APINotCreatedException("Genre not inserted");
		}

		return newGenre;
	}

	public Genre updateGenre(int id, Genre genre)
	{
		Genre existingGenre = getGenre(id).get();

		existingGenre.setName(genre.getName());

		return addGenre(existingGenre);
	}

	public void deleteGenre(int id)
	{
		try
		{
			genreDAO.deleteById(id);
		}
		catch(EmptyResultDataAccessException e)
		{
			throw new APINotDeletedException("Genre " + id + " not deleted: " + e.getMessage());
		}
	}
}
