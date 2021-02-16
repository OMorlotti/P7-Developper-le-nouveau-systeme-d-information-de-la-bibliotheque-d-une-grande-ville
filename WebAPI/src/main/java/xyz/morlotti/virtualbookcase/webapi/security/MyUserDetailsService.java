package xyz.morlotti.virtualbookcase.webapi.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import xyz.morlotti.virtualbookcase.webapi.beans.User;
import xyz.morlotti.virtualbookcase.webapi.services.interfaces.AuthService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MyUserDetailsService implements UserDetailsService
{
	@Autowired
	private AuthService authService;

	@Override
	public UserDetails loadUserByUsername(String emailOrLogin) throws UsernameNotFoundException
	{
		Optional<User> optional = authService.findByEmailOrLogin(emailOrLogin);

		if(!optional.isPresent())
		{
			throw new UsernameNotFoundException("Cannot find user '" + emailOrLogin + "'");
		}

		User user = optional.get();

		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

		authorities.add(new SimpleGrantedAuthority(user.getRole().toString()));

		return new org.springframework.security.core.userdetails.User(user.getLogin(), user.getPassword(), authorities);
	}
}
