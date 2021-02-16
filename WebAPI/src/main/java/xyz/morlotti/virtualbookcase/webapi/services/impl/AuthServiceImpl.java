package xyz.morlotti.virtualbookcase.webapi.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.morlotti.virtualbookcase.webapi.beans.User;
import xyz.morlotti.virtualbookcase.webapi.daos.UserDAO;
import xyz.morlotti.virtualbookcase.webapi.services.interfaces.AuthService;

import java.util.Optional;

@Service
public class AuthServiceImpl implements AuthService
{
	@Autowired
	private UserDAO userDAO;

	public Optional<User> findByEmailOrLogin(String emailOrLogin)
	{
		return userDAO.findByEmailOrLogin(emailOrLogin);
	}
}
