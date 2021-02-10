package xyz.morlotti.virtualbookcase.webapi.daos.custom;

import java.util.List;

import xyz.morlotti.virtualbookcase.webapi.beans.BookDescription;

public interface SearchJpaRepository
{
	public List<BookDescription> advancedSearch(AdvancedSearch advancedSearch);
}
