package xyz.morlotti.virtualbookcase.webapi.services.interfaces;

import xyz.morlotti.virtualbookcase.webapi.beans.BookGenre;

import java.util.Optional;

public interface BookGenreService
{
	public Iterable<BookGenre> listBookGenres();

	public Optional<BookGenre> getBookGenre(int id);

	public BookGenre addBookGenre(BookGenre genre);

	public BookGenre updateBookGenre(int id, BookGenre genre);

	public void deleteBookGenre(int id);
}