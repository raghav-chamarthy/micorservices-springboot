package com.sample.myspringboot.samplespringbootrestfulwebservice.user;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.sample.myspringboot.samplespringbootrestfulwebservice.user.posts.Post;
import com.sample.myspringboot.samplespringbootrestfulwebservice.user.posts.PostDaoService;
import com.sample.myspringboot.samplespringbootrestfulwebservice.user.posts.PostNotFoundException;

@RestController
public class UserResource {
	@Autowired
	private UserDaoService service;
	@Autowired
	private PostDaoService postService;
	
	@GetMapping("/Users")
	public List<User> RetrieveUsers() {
		 return service.findAll();
	}
	@GetMapping("/Users/{userid}")
	public User RetriveUserById(@PathVariable int userid) {
		User returneduser = service.findOne(userid);
		if(returneduser == null) {
			throw new UserNotFoundException("user id does not exist -"+ userid);
		}
		return returneduser;
	}
	@GetMapping("/Users/{userId}/Posts")
	public List<Post> RetrivePostsByUserId(@PathVariable int userId) {
		User returneduser = service.findOne(userId);
		System.out.println("1");
		if(returneduser == null) {
			System.out.println("2");
			throw new UserNotFoundException("user id does not exist - "+ userId);
		}
		return postService.findPostsByUserId(userId);
	}
	
	@PostMapping("/Users/{userId}/Posts")
	public Post CreatePost(@PathVariable int userId,@RequestBody Post post) {
		User returneduser = service.findOne(userId);
		if(returneduser == null) {
			throw new UserNotFoundException("user id does not exist - "+ userId);
		}
		return postService.AddPost(post);
	}
	
	@PostMapping("/Users")
	public ResponseEntity<Object> CreateUser(@RequestBody User user) {
		User saveduser = service.save(user);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(saveduser.getId()).toUri();
		 return ResponseEntity.created(location).build();
	}
	@GetMapping("/Users/Posts")
	public List<Post> RetriveAllPosts(){
		return postService.findAll();
	}
	
	@GetMapping("/Users/Posts/{postId}")
	public Post RetrivePostByPostId(@PathVariable int postId){
		Post post = postService.findPostById(postId);
		if(post == null) {
			throw new PostNotFoundException("post id does not exist - " + postId);
		}
		return post;
	}

}
