package xyz.morlotti.virtualbookcase.webapi.daos;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import xyz.morlotti.virtualbookcase.webapi.models.Book;

@Repository
public interface BookDAO extends JpaRepository<Book, Integer>
{

}
