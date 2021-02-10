package xyz.morlotti.virtualbookcase.webapi.daos.custom;

import java.util.List;

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
		TypedQuery<BookDescription> query = entityManager.createQuery("SELECT bd FROM BOOKDESCRIPTION bd", BookDescription.class);

		return query.getResultList();
	}
}
