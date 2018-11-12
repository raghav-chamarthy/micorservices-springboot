package com.sample.myspringboot.samplespringbootrestfulwebservice.user.posts;

import java.util.Date;

public class Post {
	private int postId;
	private String message;
	private Date timeOfPost;
	private int UserId;
	
	public Post(int postId, String message, Date timeOfPost,int UserId) {
		super();
		this.postId = postId;
		this.message = message;
		this.timeOfPost = timeOfPost;
		this.UserId=UserId;
	}
	
	public int getPostId() {
		return postId;
	}

	public void setPostId(int postId) {
		this.postId = postId;
	}

	public int getUserId() {
		return UserId;
	}
	
	public int getId() {
		return postId;
	}
	
	public String getMessage() {
		return message;
	}
	
	public Date getTimeOfPost() {
		return timeOfPost;
	}

}
