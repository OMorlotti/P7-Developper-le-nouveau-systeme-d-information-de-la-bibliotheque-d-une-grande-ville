package xyz.morlotti.virtualbookcase.userwebsite.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import xyz.morlotti.virtualbookcase.userwebsite.beans.User;
import xyz.morlotti.virtualbookcase.userwebsite.MyFeignProxy;

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

			model.addAttribute("user", user);

			return "user";
		}
		catch(Exception e)
		{
			model.addAttribute("messageType", "danger");
			model.addAttribute("message", "Utilisateur inconnu.");

			return "error";
		}
	}

	@RequestMapping(value = "/user/{id}", method = RequestMethod.POST)
	public String updateUser(@PathVariable("id") int id, @RequestBody User oldUser, Model model)
	{
		if(true)
		{
			model.addAttribute("messageType", "success");
			model.addAttribute("message", "Impossible de mettre à jour l'utilisateur.");

			return "error";
		}
		/*
		try
		{
			feignProxy.updateUser(id, oldUser);
		}
		catch(Exception e)
		{
			model.addAttribute("messageType", "danger");
			model.addAttribute("message", "Impossible de mettre à jour l'utilisateur.");
		}
		*/
		try
		{
			User newUser = feignProxy.getUser(id);

			model.addAttribute("user", newUser);

			return "user";
		}
		catch(Exception e)
		{
			model.addAttribute("messageType", "danger");
			model.addAttribute("message", "Utilisateur inconnu.");

			return "error";
		}
	}
}
