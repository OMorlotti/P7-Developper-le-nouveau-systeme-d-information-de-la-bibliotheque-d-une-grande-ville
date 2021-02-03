package xyz.morlotti.virtualbookcase.webapi.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import xyz.morlotti.virtualbookcase.webapi.beans.BookGenre;
import xyz.morlotti.virtualbookcase.webapi.daos.BookGenreDAO;
import xyz.morlotti.virtualbookcase.webapi.exceptions.APINotCreatedException;
import xyz.morlotti.virtualbookcase.webapi.exceptions.APINotDeletedException;
import xyz.morlotti.virtualbookcase.webapi.exceptions.APINotFoundException;
import xyz.morlotti.virtualbookcase.webapi.services.interfaces.BookGenreService;

import java.util.Optional;

@Service
public class BookGenreServiceImpl implements BookGenreService
{
	@Autowired
	private BookGenreDAO bookGenreDAO;

	public Iterable<BookGenre> listBookGenres()
	{
		Iterable<BookGenre> bookGenres = bookGenreDAO.findAll();

		return bookGenres;
	}

	public Optional<BookGenre> getBookGenre(int id)
	{
		Optional<BookGenre> optional = bookGenreDAO.findById(id);

		optional.orElseThrow(() -> new APINotFoundException("BookGenre " + id + " not found"));

		return optional;
	}

	public BookGenre addBookGenre(BookGenre bookGenre)
	{
		BookGenre newBookGenre = bookGenreDAO.save(bookGenre);

		if(newBookGenre == null)
		{
			throw new APINotCreatedException("BookGenre not inserted");
		}

		return newBookGenre;
	}

	public void deleteBookGenre(int id)
	{
		try
		{
			bookGenreDAO.deleteById(id);
		}
		catch(EmptyResultDataAccessException e)
		{
			throw new APINotDeletedException("BookGenre " + id + " not deleted: " + e.getMessage());
		}
	}
}
