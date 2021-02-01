package xyz.morlotti.virtualbookcase.webapi.daos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import xyz.morlotti.virtualbookcase.webapi.beans.BookGenre;

@Repository
public interface BookGenreDAO extends JpaRepository<BookGenre, Integer>
{

}
