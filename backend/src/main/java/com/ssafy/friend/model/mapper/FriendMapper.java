package com.ssafy.friend.model.mapper;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.friend.model.FriendInfoDto;
import org.apache.ibatis.annotations.Mapper;

import com.ssafy.friend.model.FriendDto;

@Mapper
public interface FriendMapper {

	void addRelation(FriendInfoDto relationDto) throws SQLException;
	
	Integer getRelation(FriendDto friendDto) throws SQLException;

	List<FriendInfoDto> getRelations(String userId) throws SQLException;

	void deleteRelation(FriendDto friendDto) throws SQLException;

	void updateRelation(FriendInfoDto relationDto) throws SQLException;

}
