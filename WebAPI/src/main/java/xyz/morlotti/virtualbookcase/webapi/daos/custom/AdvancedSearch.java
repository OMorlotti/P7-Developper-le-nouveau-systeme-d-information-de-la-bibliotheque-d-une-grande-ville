package xyz.morlotti.virtualbookcase.webapi.daos.custom;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class AdvancedSearch
{
	private String format;

	private String titre;

	private String author;

	private String editionNumber;

	private String editionYear;

	private String editor;

	private String isbn;
}
