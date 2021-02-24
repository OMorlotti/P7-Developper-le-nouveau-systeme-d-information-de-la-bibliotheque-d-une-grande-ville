package xyz.morlotti.virtualbookcase.webapi.security.jwt;

import java.util.Date;
import java.util.Base64;
import java.security.Key;
import javax.crypto.spec.SecretKeySpec;

import io.jsonwebtoken.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Value;

import xyz.morlotti.virtualbookcase.webapi.security.services.UserDetailsImpl;

@Component
public class JwtUtils
{
	private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);

	@Value("${virtualbookcase.app.jwt.secret}")
	private String jwtSecret;

	@Value("${virtualbookcase.app.jwt.expirationMs}")
	private int jwtExpirationMs;

	public String generateJwtToken(Authentication authentication)
	{
		UserDetailsImpl userPrincipal = (UserDetailsImpl) authentication.getPrincipal();

		Key hmacKey = new SecretKeySpec(Base64.getDecoder().decode(jwtSecret), SignatureAlgorithm.HS256.getJcaName());

		return Jwts.builder()
			.setSubject(userPrincipal.getUsername())
			.setIssuer(userPrincipal.getId() + "|" + userPrincipal.getEmail() + "|" + userPrincipal.getAuthority())
			.setIssuedAt(new Date())
			.setExpiration(new Date(new Date().getTime() + jwtExpirationMs))
			.signWith(hmacKey)
			.compact()
		;
	}

	public String getUserNameFromJwtToken(String token)
	{
		return Jwts.parserBuilder().setSigningKey(jwtSecret).build().parseClaimsJws(token).getBody().getSubject();
	}

	public boolean validateJwtToken(String token)
	{
		try
		{
			Jwts.parserBuilder().setSigningKey(jwtSecret).build().parseClaimsJws(token);

			return true;
		}
		catch(MalformedJwtException e)
		{
			logger.error("Invalid JWT token: {}", e.getMessage());
		}
		catch(ExpiredJwtException e)
		{
			logger.error("JWT token is expired: {}", e.getMessage());
		}
		catch(UnsupportedJwtException e)
		{
			logger.error("JWT token is unsupported: {}", e.getMessage());
		}
		catch(IllegalArgumentException e)
		{
			logger.error("JWT claims string is empty: {}", e.getMessage());
		}

		return false;
	}
}
