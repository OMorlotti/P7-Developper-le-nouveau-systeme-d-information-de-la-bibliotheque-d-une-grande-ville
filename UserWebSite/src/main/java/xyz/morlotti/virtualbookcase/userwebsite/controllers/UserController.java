package xyz.morlotti.virtualbookcase.userwebsite.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import xyz.morlotti.virtualbookcase.userwebsite.beans.User;
import xyz.morlotti.virtualbookcase.userwebsite.MyFeignProxy;
import xyz.morlotti.virtualbookcase.userwebsite.beans.forms.UserInfo;

@Controller
public class UserController
{
	@Autowired
	MyFeignProxy feignProxy;

	@RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
	public String showUser(@PathVariable("id") int id, Model model)
	{
		try
		{
			User user = feignProxy.getUser(id);

			System.out.println(user);

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

	@RequestMapping(value = "/user/{id}", method = RequestMethod.POST, consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
	public String updateUser(@PathVariable("id") int id, UserInfo userInfo, Model model)
	{
		try
		{
			User user = feignProxy.getUser(id);

			user.setPassword(userInfo.getPassword());
			user.setFirstname(userInfo.getFirstname());
			user.setLastname(userInfo.getLastname());
			user.setStreetNb(userInfo.getStreetNb());
			user.setStreetName(userInfo.getStreetName());
			user.setPostalCode(userInfo.getPostalCode());
			user.setCity(userInfo.getCity());
			user.setCountry(userInfo.getCountry());
			user.setEmail(userInfo.getEmail());
			user.setSex(userInfo.getSex());

			model.addAttribute("user", user);
			model.addAttribute("show", "show");

			try
			{
				feignProxy.updateUser(id, user);

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
