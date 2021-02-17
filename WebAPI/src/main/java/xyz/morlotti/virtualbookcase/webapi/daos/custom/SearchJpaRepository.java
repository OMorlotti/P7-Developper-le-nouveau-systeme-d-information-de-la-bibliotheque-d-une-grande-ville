package xyz.morlotti.virtualbookcase.webapi.daos.custom;

import java.util.List;

import xyz.morlotti.virtualbookcase.webapi.beans.BookDescription;
import xyz.morlotti.virtualbookcase.webapi.daos.beans.Search;

public interface SearchJpaRepository
{
	public List<BookDescription> advancedSearch(Search search);
}
