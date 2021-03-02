package xyz.morlotti.virtualbookcase.userwebsite.beans.forms;

import lombok.*;

import xyz.morlotti.virtualbookcase.userwebsite.beans.BookDescription;

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
