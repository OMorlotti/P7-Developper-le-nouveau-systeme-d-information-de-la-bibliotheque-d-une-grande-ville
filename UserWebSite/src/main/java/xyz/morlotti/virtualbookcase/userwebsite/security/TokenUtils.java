package xyz.morlotti.virtualbookcase.userwebsite.security;

import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

public class TokenUtils
{
	public static final String TOKEN_COOKIE_NAME = "token";

	//@Value("${virtualbookcase.app.jwtSecret}")
	private static String jwtSecret = "asdfSFS34wfsdfsdfSDSD32dfsddDDerQSNCK34SOWEK5354fdgdf4";

	public static UserInfo getUserInfoFromJwtToken(String token)
	{
		if(token != null && token.startsWith("Token:"))
		{
			Claims body;

			try
			{
				body = Jwts.parserBuilder().setSigningKey(jwtSecret).build().parseClaimsJws(token.substring(6)).getBody();

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
			catch(Exception e)
			{
				/* to nothing */
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
