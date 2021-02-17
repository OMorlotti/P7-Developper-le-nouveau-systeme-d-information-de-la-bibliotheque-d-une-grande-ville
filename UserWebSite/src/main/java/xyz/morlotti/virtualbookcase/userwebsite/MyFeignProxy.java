package xyz.morlotti.virtualbookcase.userwebsite;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import xyz.morlotti.virtualbookcase.userwebsite.beans.Book;
import xyz.morlotti.virtualbookcase.userwebsite.beans.BookDescription;
import xyz.morlotti.virtualbookcase.userwebsite.beans.User;
import xyz.morlotti.virtualbookcase.userwebsite.beans.forms.Credentials;
import xyz.morlotti.virtualbookcase.userwebsite.beans.forms.Search;

@FeignClient(name = "myFeignProxy", url = "localhost:9090")
public interface MyFeignProxy
{
	@GetMapping("/login")
	public ResponseEntity<Void> login(@RequestParam("login") String login, @RequestParam("password") String password);

	@GetMapping("/logout")
	public ResponseEntity<Void> logout();

	@GetMapping("/user")
	public User getUser();

	@PutMapping("/user")
	public ResponseEntity<Void> updateUser(@RequestBody User user);

	@GetMapping("/book/{id}")
	public Book getBook(@PathVariable("id") int id);

	@PostMapping("/bookDescription/search")
	public Iterable<BookDescription> searchBook(@RequestBody Search search);
}
