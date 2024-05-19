package com.ssafy.friend.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class FriendInfoDto {
	private String userId;
	private String friendId;
	private String friendName;
	private String friendProfile;
	private int relation;

	public FriendInfoDto(String id, String friend) {
		this.userId = id;
		this.friendId = friend;
	}

}
