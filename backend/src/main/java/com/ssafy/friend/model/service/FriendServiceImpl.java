package com.ssafy.friend.model.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ssafy.exception.TripExeption;
import com.ssafy.friend.model.FriendDto;
import com.ssafy.friend.model.FriendRelationDto;
import com.ssafy.friend.model.mapper.FriendMapper;

@Service
public class FriendServiceImpl implements FriendService {
	
	private static FriendService friendService;
	private final FriendMapper friendMapper;
	
	private FriendServiceImpl(FriendMapper friendMapper) {
		this.friendMapper = friendMapper;
	}
	
	public static FriendService getFriendService() {
		return friendService;
	}

	@Override
	public void addFriend(FriendDto friendDto) throws SQLException {
		FriendRelationDto relationDto = new FriendRelationDto(friendDto.getUserId(), friendDto.getFriendId());

		Integer userRequest = friendMapper.getRelation(friendDto);
		Integer friendRequest = friendMapper.getRelation(new FriendDto(friendDto.getFriendId(), friendDto.getUserId()));
		if (userRequest == null && friendRequest == null) {
			// case1. 서로 친구추가 하지않은 경우, 새로 추가
			relationDto.setRelation(1);
			friendMapper.addFriend(relationDto);	
		} else if (friendRequest != null && friendRequest == 1) {
			// case2. 상대가 나를 먼저 친구 추가한 경우, 서로친구 맺음
			relationDto.setRelation(2);
			relationDto.setUserId(friendDto.getFriendId());
			relationDto.setFriendId(friendDto.getUserId());
			friendMapper.updateRelation(relationDto);
		}  else if (userRequest != null && userRequest == 1) {
			throw new TripExeption("이미 친구 신청을 한 상대입니다. 상대의 응답을 기다리고 있는 중 입니다.");
		} else {
			throw new TripExeption("허가되지 않은 친구 관계를 시도하였습니다.");
		}
	}
	
	@Override
	public int getRelation(FriendDto friendDto) throws SQLException {
		Integer userRequest = friendMapper.getRelation(friendDto);
		Integer friendRequest = friendMapper.getRelation(new FriendDto(friendDto.getFriendId(), friendDto.getUserId()));
		return friendRequest == null? userRequest : friendRequest;
	}

	@Override
	public List<FriendRelationDto> getMembers(String userId) throws SQLException {
		return friendMapper.getMembers(userId);
	}

	@Override
	public void deleteFriend(FriendDto friendDto) throws SQLException {
		friendMapper.deleteFriend(friendDto);
	}

}
