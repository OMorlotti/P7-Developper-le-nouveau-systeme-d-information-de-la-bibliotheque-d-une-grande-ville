package xyz.morlotti.virtualbookcase.webapi.daos.customJpaRepositories;

import java.util.List;

import xyz.morlotti.virtualbookcase.webapi.models.BookDescription;
import xyz.morlotti.virtualbookcase.webapi.daos.beans.Search;

public interface SearchJpaRepository
{
	public List<BookDescription> search(Search search);
}
