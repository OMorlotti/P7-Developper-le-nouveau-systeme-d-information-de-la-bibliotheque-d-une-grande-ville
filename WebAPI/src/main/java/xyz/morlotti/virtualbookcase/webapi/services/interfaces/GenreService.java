package xyz.morlotti.virtualbookcase.webapi.services.interfaces;

import xyz.morlotti.virtualbookcase.webapi.beans.Genre;

import java.util.Optional;

public interface GenreService
{
	public Iterable<Genre> listGenres();

	public Optional<Genre> getGenre(int id);

	public Genre addGenre(Genre genre);

	public Genre updateGenre(int id, Genre genre);

	public void deleteGenre(int id);
}
