package xyz.morlotti.virtualbookcase.webapi.daos;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import xyz.morlotti.virtualbookcase.webapi.beans.BookDescription;
import xyz.morlotti.virtualbookcase.webapi.daos.custom.SearchJpaRepository;

// https://dev.to/brunodrugowick/four-steps-to-extend-a-spring-data-jpa-repository-with-your-own-code-53b0

@Repository
public interface BookDescriptionDAO extends JpaRepository<BookDescription, Integer>, SearchJpaRepository
{

}
