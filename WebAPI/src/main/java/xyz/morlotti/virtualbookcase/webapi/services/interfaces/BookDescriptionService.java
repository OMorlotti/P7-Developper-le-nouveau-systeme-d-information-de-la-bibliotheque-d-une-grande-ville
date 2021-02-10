package xyz.morlotti.virtualbookcase.webapi.services.interfaces;

import xyz.morlotti.virtualbookcase.webapi.beans.BookDescription;
import xyz.morlotti.virtualbookcase.webapi.daos.custom.AdvancedSearch;

import java.util.Optional;

public interface BookDescriptionService
{
	public Iterable<BookDescription> listBookDescriptions();

	public Optional<BookDescription> getBookDescription(int id);

	public BookDescription addBookDescription(BookDescription genre);

	public BookDescription updateBookDescription(int id, BookDescription genre);

	public void deleteBookDescription(int id);

	public Iterable<BookDescription> searchBookDescriptions(AdvancedSearch advancedSearch);
}