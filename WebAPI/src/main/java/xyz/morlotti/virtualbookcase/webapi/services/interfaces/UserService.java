package xyz.morlotti.virtualbookcase.webapi.services.interfaces;

import java.util.Optional;

import xyz.morlotti.virtualbookcase.webapi.models.User;

public interface UserService
{
	public Iterable<User> listUsers();

	public Optional<User> getUser(int id);

	public User addUser(User genre);

	public User updateUser(int id, User user);

	public void deleteUser(int id);
}
