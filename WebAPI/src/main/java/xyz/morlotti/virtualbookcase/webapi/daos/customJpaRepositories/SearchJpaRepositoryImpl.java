package xyz.morlotti.virtualbookcase.webapi.daos.customJpaRepositories;

import java.util.Set;
import java.util.List;
import java.util.LinkedHashSet;

import javax.persistence.TypedQuery;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import xyz.morlotti.virtualbookcase.webapi.daos.beans.Search;
import xyz.morlotti.virtualbookcase.webapi.daos.beans.SearchResult;

@Repository
public class SearchJpaRepositoryImpl implements SearchJpaRepository
{
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<SearchResult> search(Search search)
	{
		Set<String> conds = new LinkedHashSet<>();

		conds.add("1 = 1");

		if(search.getTitle() != null && !search.getTitle().isEmpty())
		{
			conds.add("bd.title LIKE :title");
		}

		if(search.getAuthor() != null && !search.getAuthor().isEmpty())
		{
			conds.add("bd.authorFirstname LIKE :author OR bd.authorLastname LIKE :author");
		}

		if(search.getEditionNumber() != null && !search.getEditionNumber().isEmpty())
		{
			conds.add("bd.editionNumber LIKE :editionNumber");
		}

		if(search.getEditionYear() != null && !search.getEditionYear().isEmpty())
		{
			conds.add("bd.editionYear LIKE :editionYear");
		}

		if(search.getEditor() != null && !search.getEditor().isEmpty())
		{
			conds.add("bd.editor LIKE :editor");
		}

		if(search.getIsbn() != null && !search.getIsbn().isEmpty())
		{
			conds.add("bd.isbn LIKE :isbn");
		}

		String sql = "SELECT NEW xyz.morlotti.virtualbookcase.webapi.daos.beans.SearchResult(bd, (SELECT COUNT(*) FROM BOOK b WHERE b.available = 1 AND b.bookDescription.id = bd.id), (SELECT COUNT(*) FROM BOOK b WHERE b.available = 0 AND b.bookDescription.id = bd.id)) FROM BOOKDESCRIPTION bd WHERE " + String.join(" AND ", conds) + " ORDER BY bd.title ASC";

		TypedQuery<SearchResult> query = entityManager.createQuery(sql, SearchResult.class);

		if(search.getTitle() != null && !search.getTitle().isEmpty())
		{
			query.setParameter("title", "%" + search.getTitle() + "%");
		}

		if(search.getAuthor() != null && !search.getAuthor().isEmpty())
		{
			query.setParameter("author", "%" + search.getAuthor() + "%");
		}

		if(search.getEditionNumber() != null && !search.getEditionNumber().isEmpty())
		{
			query.setParameter("editionNumber", "%" + search.getEditionNumber() + "%");
		}

		if(search.getEditionYear() != null && !search.getEditionYear().isEmpty())
		{
			query.setParameter("editionYear", "%" + search.getEditionYear() + "%");
		}

		if(search.getEditor() != null && !search.getEditor().isEmpty())
		{
			query.setParameter("editor", "%" + search.getEditor() + "%");
		}

		if(search.getIsbn() != null && !search.getIsbn().isEmpty())
		{
			query.setParameter("isbn", "%" + search.getIsbn() + "%");
		}

		return query.getResultList();
	}
}
