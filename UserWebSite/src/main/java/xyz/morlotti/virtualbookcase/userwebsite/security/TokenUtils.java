package xyz.morlotti.virtualbookcase.userwebsite.security;

import java.util.Properties;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import io.jsonwebtoken.*;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

public class TokenUtils
{
	public static final String TOKEN_COOKIE_NAME = "token";

	public static UserInfo getUserInfoFromJwtToken(String token)
	{
		if(token != null && token.startsWith("Token:"))
		{
			Claims body;

			try
			{
				Properties properties = PropertiesLoaderUtils.loadProperties(new ClassPathResource("/application.properties"));

				String jwtSecret = properties.getProperty("virtualbookcase.app.jwtSecret");

				/**/

				body = Jwts.parserBuilder().setSigningKey(jwtSecret).build().parseClaimsJws(token.substring(6)).getBody();

				/**/

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
				/* do nothing */
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
