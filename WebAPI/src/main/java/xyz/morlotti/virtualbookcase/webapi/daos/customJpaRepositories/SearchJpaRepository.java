package xyz.morlotti.virtualbookcase.webapi.daos.customJpaRepositories;

import java.util.List;

import xyz.morlotti.virtualbookcase.webapi.daos.beans.SearchResult;
import xyz.morlotti.virtualbookcase.webapi.models.Book;
import xyz.morlotti.virtualbookcase.webapi.daos.beans.Search;

public interface SearchJpaRepository
{
	public List<SearchResult> search(Search search);
}
