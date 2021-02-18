package xyz.morlotti.virtualbookcase.userwebsite.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Value;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

public class TokenUtils
{
	public static final String TOKEN_COOKIE_NAME = "token";

	//@Value("${virtualbookcase.app.jwtSecret}")
	private static String jwtSecret = "1357";

	public static UserInfo getUserInfoFromJwtToken(String token)
	{
		if(token != null && token.startsWith("Token:"))
		{
			System.out.println(token);
			System.out.println(token.substring(6));
			Claims body = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token.substring(6)).getBody();

			String login = body.getSubject();

			String[] parts = body.getIssuer().split("\\|");

			if(parts.length == 3)
			{
				return new UserInfo(
					parts[0],   // id
					login,
					parts[1],   // email
					parts[2],   // role
					token
				);
			}
		}

		return null;
	}

	public static void createTokenCookie(HttpServletResponse httpServletResponse, String token)
	{
		Cookie cookie = new Cookie(TOKEN_COOKIE_NAME, token);
		cookie.setMaxAge(60 * 60 * 24 * 30);
		cookie.setPath("/");

		httpServletResponse.addCookie(cookie);
	}

	public static void deleteTokenCookie(HttpServletResponse httpServletResponse)
	{
		Cookie cookie = new Cookie(TOKEN_COOKIE_NAME, null);
		cookie.setMaxAge(60 * 60 * 24 * 30);
		cookie.setPath("/");

		httpServletResponse.addCookie(cookie);
	}
}
