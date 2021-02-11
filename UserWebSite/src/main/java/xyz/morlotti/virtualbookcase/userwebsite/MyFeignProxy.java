package xyz.morlotti.virtualbookcase.userwebsite;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import xyz.morlotti.virtualbookcase.userwebsite.beans.Book;
import xyz.morlotti.virtualbookcase.userwebsite.beans.BookDescription;
import xyz.morlotti.virtualbookcase.userwebsite.beans.User;
import xyz.morlotti.virtualbookcase.userwebsite.beans.forms.AdvancedSearch;

import java.util.Optional;

@FeignClient(name = "myFeignProxy", url = "localhost:9090")
public interface MyFeignProxy
{
	@GetMapping("/user/{id}")
	public User getUser(@PathVariable("id") int id);

	@PutMapping("/user/{id}")
	public ResponseEntity<Void> updateUser(@PathVariable int id, @RequestBody User user);

	@GetMapping("/book/{id}")
	public Book getBook(@PathVariable("id") int id);

	@PostMapping("/bookDescription/search")
	public Iterable<BookDescription> searchBook(@RequestBody AdvancedSearch advancedSearch);
}
