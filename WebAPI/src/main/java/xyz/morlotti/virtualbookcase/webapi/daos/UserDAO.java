package xyz.morlotti.virtualbookcase.webapi.daos;

import java.util.Optional;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.JpaRepository;

import xyz.morlotti.virtualbookcase.webapi.beans.User;

@Repository
public interface UserDAO extends JpaRepository<User, Integer>
{
	@Query("SELECT u FROM USER u WHERE u.login = :login AND u.password = :password")
	public Optional<User> findByLoginPassword(@Param("login") String login, @Param("password") String password);

	@Query("SELECT u FROM USER u WHERE u.email = :email")
	public Optional<User> findByEmail(@Param("email") String email);
}
