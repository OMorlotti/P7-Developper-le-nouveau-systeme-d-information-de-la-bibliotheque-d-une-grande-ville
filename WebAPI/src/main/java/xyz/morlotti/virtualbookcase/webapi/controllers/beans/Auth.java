package xyz.morlotti.virtualbookcase.webapi.controllers.beans;

import lombok.*;

import xyz.morlotti.virtualbookcase.webapi.models.User;

@Getter // génère automatiquement les getters
@Setter // génère automatiquement les setters
@AllArgsConstructor
@ToString
public class Auth
{
	private Integer id;

	private String login;

	private String email;

	private User.Role role;

	private String token;
}
