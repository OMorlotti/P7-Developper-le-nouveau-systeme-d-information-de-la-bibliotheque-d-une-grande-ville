package xyz.morlotti.virtualbookcase.webapi.controllers;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import xyz.morlotti.virtualbookcase.webapi.daos.beans.Search;
import xyz.morlotti.virtualbookcase.webapi.daos.beans.SearchResult;
import xyz.morlotti.virtualbookcase.webapi.services.interfaces.SearchService;

@RestController
public class SearchController
{
	@Autowired
	SearchService searchService;

	@RequestMapping(value = "/book/search", method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE})
	public Iterable<SearchResult> searchBook(@RequestBody Search search)
	{
		return searchService.search(search);
	}
}
