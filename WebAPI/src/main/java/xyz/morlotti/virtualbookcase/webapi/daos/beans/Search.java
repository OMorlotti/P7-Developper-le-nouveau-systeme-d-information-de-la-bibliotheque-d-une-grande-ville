package xyz.morlotti.virtualbookcase.webapi.daos.beans;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Search
{
	private String title;

	private String author;

	private String editionNumber;

	private String editionYear;

	private String editor;

	private String isbn;
}
