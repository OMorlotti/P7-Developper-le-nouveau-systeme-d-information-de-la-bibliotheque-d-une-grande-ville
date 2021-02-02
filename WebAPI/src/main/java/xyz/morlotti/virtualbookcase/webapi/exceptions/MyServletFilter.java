package xyz.morlotti.virtualbookcase.webapi.exceptions;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import xyz.morlotti.virtualbookcase.webapi.beans.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Component
@Order(1)
public class MyServletFilter implements Filter
{
	@Override
	public void doFilter(
		ServletRequest request,
		ServletResponse response,
		FilterChain chain
	) throws IOException, ServletException
	{
		HttpServletRequest req = (HttpServletRequest) request;

		HttpSession session = req.getSession(true); // Le paramètre True indique qu'il faut créer la session si elle n'existe pas. La session est comme un dictionnaire (map).

		if(session.getAttribute("currentUser") == null) // S'il n'y a pas d'entrée currentUser dans la session, elle est créee.
		{
			User user = new User();

			user.initGuest();

			session.setAttribute("currentUser", user);
		}

		// Passez à l'élément suivant (filtre ou cible) en chaîne.
		chain.doFilter(request, response);
	}
}