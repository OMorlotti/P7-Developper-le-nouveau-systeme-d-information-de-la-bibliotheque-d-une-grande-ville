package xyz.morlotti.virtualbookcase.webapi.services.interfaces;

import xyz.morlotti.virtualbookcase.webapi.daos.beans.Search;
import xyz.morlotti.virtualbookcase.webapi.daos.beans.SearchResult;

public interface SearchService
{
	public Iterable<SearchResult> search(Search search);
}
