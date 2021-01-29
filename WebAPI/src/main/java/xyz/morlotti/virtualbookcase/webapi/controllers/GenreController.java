package xyz.morlotti.virtualbookcase.webapi.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import xyz.morlotti.virtualbookcase.webapi.beans.Genre;

@RestController
public class GenreController {
		@RequestMapping(value="/genres", method= RequestMethod.GET)
		public Genre listeGenres() {
			Genre genre = new Genre();
			genre.setName("bd");
			return genre;
		}
}
