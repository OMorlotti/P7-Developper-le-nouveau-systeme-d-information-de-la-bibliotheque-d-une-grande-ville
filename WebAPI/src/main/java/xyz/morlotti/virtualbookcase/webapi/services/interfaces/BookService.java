package xyz.morlotti.virtualbookcase.webapi.services.interfaces;

import xyz.morlotti.virtualbookcase.webapi.beans.Book;

import java.util.Optional;

public interface BookService
{
	public Iterable<Book> listBooks();

	public Optional<Book> getBook(int id);

	public Book addBook(Book genre);

	public Book updateBook(int id, Book genre);

	public void deleteBook(int id);
}