package xyz.morlotti.virtualbookcase.webapi.daos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import xyz.morlotti.virtualbookcase.webapi.beans.User;

import java.util.Optional;

@Repository
public interface UserDAO extends JpaRepository<User, Integer>
{
	@Query("SELECT u FROM USER u WHERE u.login = :login AND u.password = :password")
	public Optional<User> findByLoginPassword(@Param("login") String login, @Param("password") String password);

	@Query("SELECT u FROM USER u WHERE u.email = :email")
	public Optional<User> findByEmail(@Param("email") String email);
}
