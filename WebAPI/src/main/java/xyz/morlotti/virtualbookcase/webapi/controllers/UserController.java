package xyz.morlotti.virtualbookcase.webapi.controllers;

import java.net.URI;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import xyz.morlotti.virtualbookcase.webapi.beans.User;
import xyz.morlotti.virtualbookcase.webapi.exceptions.APINotFoundException;
import xyz.morlotti.virtualbookcase.webapi.services.interfaces.UserService;

@RestController
public class UserController
{
	@Autowired
	UserService userService;

	@RequestMapping(value = "/users", method = RequestMethod.GET)
	public Iterable<User> listUsers()
	{
		return userService.listUsers();
	}

	@RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
	public User getUser(@PathVariable int id)
	{
		Optional<User> optional = userService.getUser(id);

		optional.orElseThrow(() -> new APINotFoundException("User " + id + " not found"));

		return optional.get();
	}

	@RequestMapping(value = "/user", method = RequestMethod.POST)
	public ResponseEntity<Void> addUser(@RequestBody User user)
	{
		User newUser = userService.addUser(user);

		URI location = ServletUriComponentsBuilder
           .fromCurrentRequest()
           .path("/{id}")
           .buildAndExpand(newUser.getId())
           .toUri()
		;

		return ResponseEntity.created(location).build();
	}

	@RequestMapping(value = "/user/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> updateUser(@PathVariable int id, @RequestBody User user)
	{
		User newUser = userService.updateUser(id, user);

		URI location = ServletUriComponentsBuilder
           .fromCurrentRequest()
           .path("/{id}")
           .buildAndExpand(newUser.getId())
           .toUri()
		;

		return ResponseEntity.created(location).build();
	}

	@RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deleteUser(@PathVariable int id)
	{
		userService.deleteUser(id);

		return ResponseEntity.status(HttpStatus.OK).build();
	}
}
