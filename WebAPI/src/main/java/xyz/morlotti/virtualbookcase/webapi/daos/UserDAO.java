package xyz.morlotti.virtualbookcase.webapi.daos;

import java.util.Optional;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.JpaRepository;

import xyz.morlotti.virtualbookcase.webapi.models.User;

@Repository
public interface UserDAO extends JpaRepository<User, Integer>
{
	@Query("SELECT u FROM USER u WHERE u.login = :login")
	public Optional<User> findByLogin(@Param("login") String login);

	@Query("SELECT u FROM USER u WHERE u.email = :email")
	public Optional<User> findByEmail(@Param("email") String email);
}
