package xyz.morlotti.virtualbookcase.webapi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import xyz.morlotti.virtualbookcase.webapi.beans.User;
import xyz.morlotti.virtualbookcase.webapi.daos.UserDAO;
import xyz.morlotti.virtualbookcase.webapi.exceptions.APINotCreatedException;
import xyz.morlotti.virtualbookcase.webapi.exceptions.APINotDeletedException;
import xyz.morlotti.virtualbookcase.webapi.exceptions.APINotFoundException;

import java.net.URI;
import java.util.Optional;

@RestController
public class UserController
{
	@Autowired
	private UserDAO userDAO;

	@RequestMapping(value="/users", method = RequestMethod.GET)
	public Iterable<User> listUsers()
	{
		Iterable<User> users = userDAO.findAll();

		return users;
	}

	@RequestMapping(value="/user/{id}", method = RequestMethod.GET)
	public Optional<User> getUser(@PathVariable int id)
	{
		Optional<User> optional = userDAO.findById(id);

		optional.orElseThrow(() -> new APINotFoundException("User " + id + " not found"));

		return optional;
	}

	@RequestMapping(value = "/user", method = RequestMethod.POST)
	public ResponseEntity<Void> addUser(@RequestBody User user)
	{
		User newUser = userDAO.save(user);

		if(newUser == null)
		{
			throw new APINotCreatedException("User not inserted");
		}

		URI location = ServletUriComponentsBuilder
           .fromCurrentRequest()
           .path("/{id}")
           .buildAndExpand(newUser.getId())
           .toUri()
		;

		return ResponseEntity.created(location).build();
	}

	@RequestMapping(value="/user/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> updateUser(@PathVariable int id, @RequestBody User user)
	{
		User existingUser = getUser(id).get();

		existingUser.setCity(user.getCity());

		existingUser.setCountry(user.getCountry());

		existingUser.setEmail(user.getEmail());

		existingUser.setFirstname(user.getFirstname());

		existingUser.setLastname(user.getLastname());

		existingUser.setLogin(user.getLogin());

		existingUser.setMembership(user.getMembership());

		existingUser.setPassword(user.getPassword());

		existingUser.setPostalCode(user.getPostalCode());

		existingUser.setSex(user.getSex());

		existingUser.setStreetNb(user.getStreetNb());

		existingUser.setStreetName(user.getStreetName());

		return addUser(existingUser);
	}

	@RequestMapping(value="/user/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deleteUser(@PathVariable int id)
	{
		try
		{
			userDAO.deleteById(id);

			return ResponseEntity.status(HttpStatus.OK).build();
		}
		catch(EmptyResultDataAccessException e)
		{
			throw new APINotDeletedException("User " + id + " not deleted: " + e.getMessage());
		}
	}
}
