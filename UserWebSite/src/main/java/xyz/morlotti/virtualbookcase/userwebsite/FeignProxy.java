package xyz.morlotti.virtualbookcase.userwebsite;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import xyz.morlotti.virtualbookcase.userwebsite.beans.Book;

import java.util.Optional;

@FeignClient(name = "genre-proxy", url = "localhost:9090")
public interface FeignProxy
{
	@GetMapping("/genres")
	public Iterable<Genre> listGenres();

	@GetMapping("/book/{id}")
	public Optional<Book> getBook(@PathVariable("id") int id);
}
