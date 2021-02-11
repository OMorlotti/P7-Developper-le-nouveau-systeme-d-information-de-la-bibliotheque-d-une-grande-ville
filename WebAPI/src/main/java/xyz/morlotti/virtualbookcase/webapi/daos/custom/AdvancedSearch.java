package xyz.morlotti.virtualbookcase.webapi.daos.custom;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Data
@Getter
@Setter
@ToString
public class AdvancedSearch
{
	private String titre;

	private String author;

	private String editionNumber;

	private String editionYear;

	private String editor;

	private String isbn;
}
