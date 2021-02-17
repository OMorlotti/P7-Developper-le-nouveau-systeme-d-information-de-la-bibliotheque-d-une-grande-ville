package xyz.morlotti.virtualbookcase.webapi.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import xyz.morlotti.virtualbookcase.webapi.beans.User;
import xyz.morlotti.virtualbookcase.webapi.daos.UserDAO;

import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService
{
	@Autowired
	UserDAO userDAO;

	@Override
	public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException
	{
		Optional<User> optional = userDAO.findByLogin(login);

		if(!optional.isPresent())
		{
			throw new UsernameNotFoundException("Cannot find user " + login);
		}

		return UserDetailsImpl.build(optional.get());
	}

}