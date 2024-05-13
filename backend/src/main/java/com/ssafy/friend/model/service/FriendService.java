package com.ssafy.friend.model.service;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.friend.model.FriendDto;
import com.ssafy.friend.model.FriendRelationDto;

public interface FriendService {

	public void addFriend(FriendDto friendDto) throws SQLException ;

	public int getRelation(FriendDto friendDto) throws SQLException ;
	
	public List<FriendRelationDto> getMembers(String userId) throws SQLException ;
	
	public void deleteFriend(FriendDto friendDto) throws SQLException;

}
