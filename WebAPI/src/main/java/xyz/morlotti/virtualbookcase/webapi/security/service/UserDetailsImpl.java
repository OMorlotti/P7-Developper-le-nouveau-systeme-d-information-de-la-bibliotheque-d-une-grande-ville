package xyz.morlotti.virtualbookcase.webapi.security.service;

import java.util.List;
import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import xyz.morlotti.virtualbookcase.webapi.beans.User;

public class UserDetailsImpl implements UserDetails
{
	private Integer id;

	private String login;

	private String password;

	private String email;

	private Collection<? extends GrantedAuthority> authorities;

	public UserDetailsImpl(Integer id, String login, String password, String email, Collection<? extends GrantedAuthority> authorities)
	{
		this.id = id;
		this.login = login;
		this.password = password;
		this.email = email;
		this.authorities = authorities;
	}

	public static UserDetailsImpl build(User user)
	{
		List<GrantedAuthority> authorities = Collections.singletonList(
			new SimpleGrantedAuthority(user.getRole().toString())
		);

		return new UserDetailsImpl(
			user.getId(),
			user.getLogin(),
			user.getPassword(),
			user.getEmail(),
			authorities
		);
	}

	@Override
	public String getUsername()
	{
		return this.login;
	}

	@Override
	public String getPassword()
	{
		return this.password;
	}

	/////////
	public String getEmail()
	{
		return this.email;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities()
	{
		return this.authorities;
	}

	@Override
	public boolean isEnabled()
	{
		return true;
	}

	@Override
	public boolean isAccountNonExpired()
	{
		return true;
	}

	@Override
	public boolean isAccountNonLocked()
	{
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired()
	{
		return true;
	}

	@Override
	public boolean equals(Object o)
	{
		if(this == o)
		{
			return true;
		}

		if(o == null || this.getClass() != o.getClass())
		{
			return false;
		}

		return this.id == ((UserDetailsImpl) o).id;
	}
}
