package xyz.morlotti.virtualbookcase.webapi.services.interfaces;

import java.util.Optional;

import xyz.morlotti.virtualbookcase.webapi.beans.BookDescription;
import xyz.morlotti.virtualbookcase.webapi.daos.beans.Search;

public interface BookDescriptionService
{
	public Iterable<BookDescription> listBookDescriptions();

	public Optional<BookDescription> getBookDescription(int id);

	public BookDescription addBookDescription(BookDescription genre);

	public BookDescription updateBookDescription(int id, BookDescription genre);

	public void deleteBookDescription(int id);

	public Iterable<BookDescription> searchBookDescriptions(Search search);
}