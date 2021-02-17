package xyz.morlotti.virtualbookcase.webapi.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import xyz.morlotti.virtualbookcase.webapi.beans.User;
import xyz.morlotti.virtualbookcase.webapi.daos.UserDAO;
import xyz.morlotti.virtualbookcase.webapi.exceptions.APINotCreatedException;
import xyz.morlotti.virtualbookcase.webapi.exceptions.APINotDeletedException;
import xyz.morlotti.virtualbookcase.webapi.exceptions.APINotFoundException;
import xyz.morlotti.virtualbookcase.webapi.services.interfaces.UserService;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService
{
	@Autowired
	private UserDAO userDAO;

	public Iterable<User> listUsers()
	{
		Iterable<User> users = userDAO.findAll();

		return users;
	}

	public Optional<User> getUser(int id)
	{
		Optional<User> optional = userDAO.findById(id);

		return optional;
	}

	public User addUser(User user)
	{
		User newUser = userDAO.save(user);

		if(newUser == null)
		{
			throw new APINotCreatedException("User not inserted");
		}

		return newUser;
	}

	public User updateUser(int id, User user)
	{
		User existingUser = getUser(id).get();

		existingUser.setCity(user.getCity());

		existingUser.setCountry(user.getCountry());

		existingUser.setEmail(user.getEmail());

		existingUser.setFirstname(user.getFirstname());

		existingUser.setLastname(user.getLastname());

		existingUser.setLogin(user.getLogin());

		existingUser.setMembership(user.getMembership());

		if(!user.getPassword().isEmpty())
		{
			existingUser.setPassword(user.getPassword());
		}

		existingUser.setPostalCode(user.getPostalCode());

		existingUser.setRole(user.getRole());

		existingUser.setSex(user.getSex());

		existingUser.setStreetNb(user.getStreetNb());

		existingUser.setStreetName(user.getStreetName());

		return addUser(existingUser);
	}

	public void deleteUser(int id)
	{
		try
		{
			userDAO.deleteById(id);
		}
		catch(EmptyResultDataAccessException e)
		{
			throw new APINotDeletedException("User " + id + " not deleted: " + e.getMessage());
		}
	}
}
