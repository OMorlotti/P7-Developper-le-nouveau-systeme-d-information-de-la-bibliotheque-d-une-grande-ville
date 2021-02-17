package xyz.morlotti.virtualbookcase.webapi.controllers.beans;

import lombok.*;

@Getter // génère automatiquement les getters
@Setter // génère automatiquement les setters
@AllArgsConstructor
@ToString
public class Auth
{
	private Integer id;

	private String login;

	private String email;

	private String role;

	private String token;
}
