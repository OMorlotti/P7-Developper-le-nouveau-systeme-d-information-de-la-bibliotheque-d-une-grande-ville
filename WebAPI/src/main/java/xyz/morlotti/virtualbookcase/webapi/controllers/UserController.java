package xyz.morlotti.virtualbookcase.webapi.controllers;

import java.net.URI;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import xyz.morlotti.virtualbookcase.webapi.models.User;
import xyz.morlotti.virtualbookcase.webapi.exceptions.APINotFoundException;
import xyz.morlotti.virtualbookcase.webapi.services.interfaces.UserService;
import xyz.morlotti.virtualbookcase.webapi.security.services.UserDetailsImpl;

@RestController
public class UserController
{
	@Autowired
	UserService userService;

	@PreAuthorize("hasAuthority('ADMIN') or hasAuthority('EMPLOYEE')")
	@RequestMapping(value = "/users", method = RequestMethod.GET)
	public Iterable<User> listUsers()
	{
		return userService.listUsers();
	}

	@PreAuthorize("hasAuthority('ADMIN') or hasAuthority('EMPLOYEE') or hasAuthority('USER')")
	@RequestMapping(value = "/user", method = RequestMethod.GET)
	public User getCurrentUser(Authentication authentication)
	{
		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

		return getUser(userDetails.getId());
	}

	@PreAuthorize("hasAuthority('ADMIN') or hasAuthority('EMPLOYEE')")
	@RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
	public User getUser(@PathVariable int id)
	{
		Optional<User> optional = userService.getUser(id);

		if(!optional.isPresent())
		{
			throw new APINotFoundException("User " + id + " not found");
		}

		return optional.get();
	}

	@PreAuthorize("hasAuthority('ADMIN') or hasAuthority('EMPLOYEE')")
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

	@PreAuthorize("hasAuthority('ADMIN') or hasAuthority('EMPLOYEE') or hasAuthority('USER')")
	@RequestMapping(value = "/user", method = RequestMethod.PUT)
	public ResponseEntity<Void> updateCurrentUser(@RequestBody User user, Authentication authentication)
	{
		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

		User newUser = userService.updateUser(userDetails.getId(), user);

		URI location = ServletUriComponentsBuilder
			.fromCurrentRequest()
			.path("/{id}")
			.buildAndExpand(newUser.getId())
			.toUri()
		;

		return ResponseEntity.created(location).build();
	}

	@PreAuthorize("hasAuthority('ADMIN') or hasAuthority('EMPLOYEE')")
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

	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deleteUser(@PathVariable int id)
	{
		userService.deleteUser(id);

		return ResponseEntity.status(HttpStatus.OK).build();
	}
}
