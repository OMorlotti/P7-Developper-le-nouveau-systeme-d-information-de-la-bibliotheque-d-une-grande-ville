package xyz.morlotti.virtualbookcase.webapi.services.interfaces;

import xyz.morlotti.virtualbookcase.webapi.beans.User;

import java.util.Optional;

public interface UserService
{
	public Iterable<User> listUsers();

	public Optional<User> getUser(int id);

	public User addUser(User genre);

	public User updateUser(int id, User genre);

	public void deleteUser(int id);
}