package xyz.morlotti.virtualbookcase.userwebsite.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import xyz.morlotti.virtualbookcase.userwebsite.beans.User;
import xyz.morlotti.virtualbookcase.userwebsite.MyFeignProxy;
import xyz.morlotti.virtualbookcase.userwebsite.beans.forms.FullUserInfo;
import xyz.morlotti.virtualbookcase.userwebsite.security.TokenUtils;
import xyz.morlotti.virtualbookcase.userwebsite.security.UserInfo;

@Controller
public class UserController
{
	@Autowired
	MyFeignProxy feignProxy;

	@RequestMapping(value = "/user", method = RequestMethod.GET)
	public String showUser(@CookieValue(TokenUtils.TOKEN_COOKIE_NAME) String token, Model model)
	{
		UserInfo userInfo = TokenUtils.getUserInfoFromJwtToken(token);

		model.addAttribute("userInfo", userInfo);

		try
		{
			User user = feignProxy.getUser(token);

			model.addAttribute("user", user);
			model.addAttribute("show", "xxxx");

			return "user";
		}
		catch(Exception e)
		{
			model.addAttribute("messageType", "danger");
			model.addAttribute("message", "Utilisateur inconnu : " + e.getMessage());

			return "error";
		}
	}

	@RequestMapping(value = "/user", method = RequestMethod.POST, consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
	public String updateUser(@CookieValue(TokenUtils.TOKEN_COOKIE_NAME) String token, FullUserInfo fullUserInfo, Model model)
	{
		UserInfo userInfo = TokenUtils.getUserInfoFromJwtToken(token);

		model.addAttribute("userInfo", userInfo);

		try
		{
			User user = feignProxy.getUser(token);

			user.setPassword(fullUserInfo.getPassword());
			user.setFirstname(fullUserInfo.getFirstname());
			user.setLastname(fullUserInfo.getLastname());
			user.setStreetNb(fullUserInfo.getStreetNb());
			user.setStreetName(fullUserInfo.getStreetName());
			user.setPostalCode(fullUserInfo.getPostalCode());
			user.setCity(fullUserInfo.getCity());
			user.setCountry(fullUserInfo.getCountry());
			user.setEmail(fullUserInfo.getEmail());
			user.setSex(fullUserInfo.getSex());

			model.addAttribute("user", user);
			model.addAttribute("show", "show");

			try
			{
				feignProxy.updateUser(token, user);

				model.addAttribute("messageType", "success");
				model.addAttribute("message", "Utilisateur mis à jour avec succès.");
			}
			catch(Exception e)
			{
				model.addAttribute("messageType", "danger");
				model.addAttribute("message", "Impossible de mettre à jour l'utilisateur : " + e.getMessage());
			}

			return "user";
		}
		catch(Exception e)
		{
			model.addAttribute("messageType", "danger");
			model.addAttribute("message", "Utilisateur inconnu : " + e.getMessage());

			return "error";
		}
	}
}
