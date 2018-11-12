package com.sample.myspringboot.samplespringbootrestfulwebservice.user.posts;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

@Component
public class PostDaoService {

	private static List<Post> posts = new ArrayList<>();
	private static int postCounter = 4;

	static {
		posts.add(new Post(1, "My name is Adam", new Date(), 1));
		posts.add(new Post(2, "My name is Eve", new Date(), 2));
		posts.add(new Post(3, "My name is Jack", new Date(), 3));
		posts.add(new Post(4, "My name is Jack -2", new Date(), 3));
	}

	public List<Post> findAll() {
		return posts;
	}
	
	public Post AddPost(Post post) {
		if(post.getId() == 0) {
			post.setPostId(++postCounter);
		}
		posts.add(post);
		return post;
	}
	
	
	public Post findPostById(int postId) {
		for (Post post : posts) {
			if (post.getId() == postId) {
				return post;
			}
		}
		return null;
	}

	public List<Post> findPostsByUserId(int userId) {
		List<Post> result = posts.stream().filter(post -> post.getUserId() == userId).collect(Collectors.toList());
		return result;
	}
}
