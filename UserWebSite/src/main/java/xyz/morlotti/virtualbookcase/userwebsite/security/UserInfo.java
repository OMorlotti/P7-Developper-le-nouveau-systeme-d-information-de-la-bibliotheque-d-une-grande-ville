package xyz.morlotti.virtualbookcase.userwebsite.security;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.AllArgsConstructor;

@Setter
@Getter
@AllArgsConstructor
@ToString
public class UserInfo
{
	private String id;
	private String login;
	private String email;
	private String role;
	private String token;
}
