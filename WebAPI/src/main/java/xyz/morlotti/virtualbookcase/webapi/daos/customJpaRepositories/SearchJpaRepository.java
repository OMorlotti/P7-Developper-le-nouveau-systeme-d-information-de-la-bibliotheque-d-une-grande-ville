package xyz.morlotti.virtualbookcase.webapi.daos.customJpaRepositories;

import java.util.List;

import xyz.morlotti.virtualbookcase.webapi.daos.beans.Search;
import xyz.morlotti.virtualbookcase.webapi.daos.beans.SearchResult;

public interface SearchJpaRepository
{
	public List<SearchResult> search(Search search);
}
