package xyz.morlotti.virtualbookcase.webapi.daos.customJpaRepositories;

import java.util.Set;
import java.util.List;
import java.util.LinkedHashSet;

import javax.persistence.TypedQuery;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import xyz.morlotti.virtualbookcase.webapi.models.BookDescription;
import xyz.morlotti.virtualbookcase.webapi.daos.beans.Search;

@Repository
public class SearchJpaRepositoryImpl implements SearchJpaRepository
{
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<BookDescription> search(Search search)
	{
		System.out.println(search);

		Set<String> conds = new LinkedHashSet<>();

		conds.add("1 = 1");

		if(search.getTitle() != null && !search.getTitle().isEmpty())
		{
			conds.add("title LIKE :title");
		}

		if(search.getAuthor() != null && !search.getAuthor().isEmpty())
		{
			conds.add("author LIKE :author");
		}

		if(search.getEditionNumber() != null && !search.getEditionNumber().isEmpty())
		{
			conds.add("editionNumber LIKE :editionNumber");
		}

		if(search.getEditionYear() != null && !search.getEditionYear().isEmpty())
		{
			conds.add("editionYear LIKE :editionYear");
		}

		if(search.getEditor() != null && !search.getEditor().isEmpty())
		{
			conds.add("editor LIKE :editor");
		}

		if(search.getIsbn() != null && !search.getIsbn().isEmpty())
		{
			conds.add("isbn LIKE :isbn");
		}

		String sql = "SELECT bd FROM BOOKDESCRIPTION bd WHERE " + String.join(" AND ", conds);

		TypedQuery<BookDescription> query = entityManager.createQuery(sql, BookDescription.class);

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
