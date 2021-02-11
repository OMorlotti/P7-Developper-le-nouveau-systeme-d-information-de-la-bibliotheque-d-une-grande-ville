package xyz.morlotti.virtualbookcase.userwebsite.beans.forms;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AdvancedSearch
{
	private String title;

	private String author;

	private String editionNumber;

	private String editionYear;

	private String editor;

	private String isbn;
}
