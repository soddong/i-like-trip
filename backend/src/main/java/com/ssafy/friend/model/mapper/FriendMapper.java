package com.ssafy.friend.model.mapper;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.friend.model.FriendDto;
import com.ssafy.friend.model.FriendRelationDto;

@Mapper
public interface FriendMapper {

	void addFriend(FriendRelationDto relationDto) throws SQLException;
	
	Integer getRelation(FriendDto friendDto) throws SQLException;

	List<FriendRelationDto> getMembers(String userId) throws SQLException;

	void deleteFriend(FriendDto friendDto) throws SQLException;

	void updateRelation(FriendRelationDto relationDto) throws SQLException;

}
