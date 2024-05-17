package com.ssafy.friend.model;

import lombok.ToString;

@ToString
public class FriendDto {
	
	private String userId;
	private String friendId;
	
	public FriendDto(String userId, String friendId) {
		super();
		this.userId = userId;
		this.friendId = friendId;
	}
	
	public String getUserId() {
		return userId;
	}
	
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public String getFriendId() {
		return friendId;
	}
	
	public void setFriendId(String friendId) {
		this.friendId = friendId;
	}
	
}
