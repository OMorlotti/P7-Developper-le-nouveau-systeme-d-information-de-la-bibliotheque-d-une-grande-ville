package xyz.morlotti.virtualbookcase.webapi.daos.beans;

import lombok.*;

import xyz.morlotti.virtualbookcase.webapi.models.BookDescription;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class SearchResult
{
	public BookDescription bookDescription;

	public long nbAvailable;

	public long nbNotAvailable;
}
