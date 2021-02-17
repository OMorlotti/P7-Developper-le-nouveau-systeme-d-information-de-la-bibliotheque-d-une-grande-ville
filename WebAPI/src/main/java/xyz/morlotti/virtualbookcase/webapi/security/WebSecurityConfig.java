package xyz.morlotti.virtualbookcase.webapi.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;

import xyz.morlotti.virtualbookcase.webapi.security.jwt.AuthTokenFilter;
import xyz.morlotti.virtualbookcase.webapi.security.jwt.AuthEntryPointJwt;
import xyz.morlotti.virtualbookcase.webapi.security.service.UserDetailsServiceImpl;

// https://github.com/bezkoder/spring-boot-spring-security-jwt-authentication/

// https://blog.softtek.com/en/token-based-api-authentication-with-spring-and-jwt/

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter
{
	@Autowired
	UserDetailsServiceImpl userDetailsService;

	@Autowired
	private AuthEntryPointJwt unauthorizedHandler;

	@Override
	public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception
	{
		authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception
	{
		http.cors().and().csrf().disable()
			.exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
			.authorizeRequests().antMatchers("/auth/**").permitAll()
			.antMatchers("/books", "/book/**", "/bookDescriptions", "/bookDescription/**").permitAll()
			.anyRequest().authenticated()
		;

		http.addFilterBefore(authJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception
	{
		return super.authenticationManagerBean();
	}

	@Bean
	public AuthTokenFilter authJwtTokenFilter()
	{
		return new AuthTokenFilter();
	}

	@Bean
	public PasswordEncoder passwordEncoder()
	{
		return new BCryptPasswordEncoder();
	}
}
