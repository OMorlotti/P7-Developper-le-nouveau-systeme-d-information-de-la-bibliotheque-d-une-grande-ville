package xyz.morlotti.virtualbookcase.userwebsite;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import xyz.morlotti.virtualbookcase.userwebsite.beans.Genre;

@FeignClient(name = "genre-proxy", url = "localhost:9090")
public interface FeignProxy
{
	@GetMapping("/genres")
	public Iterable<Genre> listGenres();
}
