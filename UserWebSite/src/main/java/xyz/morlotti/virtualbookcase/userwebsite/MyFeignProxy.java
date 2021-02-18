package xyz.morlotti.virtualbookcase.userwebsite;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.cloud.openfeign.FeignClient;

import xyz.morlotti.virtualbookcase.userwebsite.beans.Book;
import xyz.morlotti.virtualbookcase.userwebsite.beans.User;
import xyz.morlotti.virtualbookcase.userwebsite.beans.forms.Auth;
import xyz.morlotti.virtualbookcase.userwebsite.beans.forms.Search;
import xyz.morlotti.virtualbookcase.userwebsite.beans.BookDescription;

@FeignClient(name = "myFeignProxy", url = "localhost:9090")
public interface MyFeignProxy
{
	@GetMapping("/auth/login")
	public String login(@RequestParam("login") String login, @RequestParam("password") String password);

	@GetMapping("/user")
	public User getUser(@RequestHeader("Authorization") String token);

	@PutMapping("/user")
	public ResponseEntity<Void> updateUser(@RequestHeader("Authorization") String token, @RequestBody User user);

	@GetMapping("/book/{id}")
	public Book getBook(@PathVariable("id") int id);

	@PostMapping("/book/search")
	public Iterable<Book> searchBook(@RequestBody Search search);
}
