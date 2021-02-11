package xyz.morlotti.virtualbookcase.webapi.daos.custom;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.TypedQuery;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import xyz.morlotti.virtualbookcase.webapi.beans.BookDescription;

@Repository
public class SearchJpaRepositoryImpl implements SearchJpaRepository
{
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<BookDescription> advancedSearch(AdvancedSearch advancedSearch)
	{
		System.out.println(advancedSearch);

		Set<String> conds = new LinkedHashSet<>();

		conds.add("1 = 1");

		if(advancedSearch.getTitle() != null && !advancedSearch.getTitle().isEmpty())
		{
			conds.add("title LIKE :title");
		}

		if(advancedSearch.getAuthor() != null && !advancedSearch.getAuthor().isEmpty())
		{
			conds.add("author LIKE :author");
		}

		if(advancedSearch.getEditionNumber() != null && !advancedSearch.getEditionNumber().isEmpty())
		{
			conds.add("editionNumber LIKE :editionNumber");
		}

		if(advancedSearch.getEditionYear() != null && !advancedSearch.getEditionYear().isEmpty())
		{
			conds.add("editionYear LIKE :editionYear");
		}

		if(advancedSearch.getEditor() != null && !advancedSearch.getEditor().isEmpty())
		{
			conds.add("editor LIKE :editor");
		}

		if(advancedSearch.getIsbn() != null && !advancedSearch.getIsbn().isEmpty())
		{
			conds.add("isbn LIKE :isbn");
		}

		String sql = "SELECT bd FROM BOOKDESCRIPTION bd WHERE " + String.join(" AND ", conds);

		TypedQuery<BookDescription> query = entityManager.createQuery(sql, BookDescription.class);

		if(advancedSearch.getTitle() != null && !advancedSearch.getTitle().isEmpty())
		{
			query.setParameter("title", "%" + advancedSearch.getTitle() + "%");
		}

		if(advancedSearch.getAuthor() != null && !advancedSearch.getAuthor().isEmpty())
		{
			query.setParameter("author", "%" + advancedSearch.getAuthor() + "%");
		}

		if(advancedSearch.getEditionNumber() != null && !advancedSearch.getEditionNumber().isEmpty())
		{
			query.setParameter("editionNumber", "%" + advancedSearch.getEditionNumber() + "%");
		}

		if(advancedSearch.getEditionYear() != null && !advancedSearch.getEditionYear().isEmpty())
		{
			query.setParameter("editionYear", "%" + advancedSearch.getEditionYear() + "%");
		}

		if(advancedSearch.getEditor() != null && !advancedSearch.getEditor().isEmpty())
		{
			query.setParameter("editor", "%" + advancedSearch.getEditor() + "%");
		}

		if(advancedSearch.getIsbn() != null && !advancedSearch.getIsbn().isEmpty())
		{
			query.setParameter("isbn", "%" + advancedSearch.getIsbn() + "%");
		}

		return query.getResultList();
	}
}
