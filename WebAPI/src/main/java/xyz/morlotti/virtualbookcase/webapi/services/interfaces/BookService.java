package xyz.morlotti.virtualbookcase.webapi.services.interfaces;

import java.util.Optional;

import xyz.morlotti.virtualbookcase.webapi.daos.beans.Search;
import xyz.morlotti.virtualbookcase.webapi.models.Book;
import xyz.morlotti.virtualbookcase.webapi.models.BookDescription;

public interface BookService
{
	public Iterable<Book> listBooks();

	public Optional<Book> getBook(int id);

	public Book addBook(Book genre);

	public Book updateBook(int id, Book genre);

	public void deleteBook(int id);

	public Iterable<Book> searchBookDescriptions(Search search);
}