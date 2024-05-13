package com.ssafy.friend.model;

public class FriendRelationDto {
	private String userId;
	private String friendId;
	private int relation;
	
	public FriendRelationDto() {
	}

	public FriendRelationDto(String id, String friend, int relation) {
		super();
		this.userId = id;
		this.friendId = friend;
		this.relation = relation;
	}

	public FriendRelationDto(String id, String friend) {
		this.userId = id;
		this.friendId = friend;
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
	
	public int getRelation() {
		return relation;
	}

	public void setRelation(int relation) {
		this.relation = relation;
	}
	
}
