package xyz.morlotti.virtualbookcase.userwebsite.beans.forms;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Auth
{
	private Integer id;

	private String login;

	private String email;

	private String role;

	private String token;
}
