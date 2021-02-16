package xyz.morlotti.virtualbookcase.webapi.services.interfaces;

import xyz.morlotti.virtualbookcase.webapi.beans.User;

import java.util.Optional;

public interface AuthService
{
	Optional<User> findByEmailOrLogin(String emailOrLogin);
}
