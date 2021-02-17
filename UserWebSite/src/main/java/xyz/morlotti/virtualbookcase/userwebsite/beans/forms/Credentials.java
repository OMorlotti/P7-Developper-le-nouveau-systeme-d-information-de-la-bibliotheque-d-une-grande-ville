package xyz.morlotti.virtualbookcase.userwebsite.beans.forms;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Credentials
{
	private String login;

	private String password;
}
