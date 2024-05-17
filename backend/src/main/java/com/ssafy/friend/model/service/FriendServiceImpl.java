package com.ssafy.friend.model.service;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.friend.model.FriendInfoDto;
import org.springframework.stereotype.Service;

import com.ssafy.friend.model.FriendDto;
import com.ssafy.friend.model.mapper.FriendMapper;

import static com.ssafy.friend.model.RelationType.*;

@Service
public class FriendServiceImpl implements FriendService {

	private final FriendMapper friendMapper;
	
	private FriendServiceImpl(FriendMapper friendMapper) {
		this.friendMapper = friendMapper;
	}

	@Override
	public void addFriend(FriendDto dto) throws SQLException {

		FriendInfoDto user = new FriendInfoDto(dto.getUserId(), dto.getFriendId());
		FriendInfoDto friend = new FriendInfoDto(dto.getFriendId(), dto.getUserId());

		Integer relation = friendMapper.getRelation(dto);
		System.out.println(dto);

		if (relation == null) {
			// case1. 서로 친구추가 하지않은 경우, 요청 (새로 추가)
			user.setRelation(REQUEST.toInt());
			friend.setRelation(PENDING.toInt());
			System.out.println("case1");
			friendMapper.addRelation(user);
			friendMapper.addRelation(friend);
		} else if (relation == PENDING.toInt()) {
			// case2. 상대가 나를 친구 추가한 경우, 서로친구 맺음 (관계 업데이트)
			user.setRelation(FRIEND.toInt());
			friend.setRelation(FRIEND.toInt());
			System.out.println("case2");
			friendMapper.updateRelation(user);
			friendMapper.updateRelation(friend);
		}

	}
	
	@Override
	public int getRelation(FriendDto friendDto) throws SQLException {
		Integer userRequest = friendMapper.getRelation(friendDto);
		Integer friendRequest = friendMapper.getRelation(new FriendDto(friendDto.getFriendId(), friendDto.getUserId()));
		return friendRequest == null? userRequest : friendRequest;
	}

	@Override
	public List<FriendInfoDto> getRelations(String userId) throws SQLException {
		return friendMapper.getRelations(userId);
	}

	@Override
	public void deleteRelation(FriendDto dto) throws SQLException {

		FriendInfoDto user = new FriendInfoDto(dto.getUserId(), dto.getFriendId());
		FriendInfoDto friend = new FriendInfoDto(dto.getFriendId(), dto.getUserId());

		Integer relation = friendMapper.getRelation(dto);

		if (relation == FRIEND.toInt()) {
			// case1. 서로친구 한 경우, 상대만 요청한 것으로 업데이트
			user.setRelation(PENDING.toInt());
			friend.setRelation(REQUEST.toInt());
			System.out.println("case1");
			friendMapper.updateRelation(user);
			friendMapper.updateRelation(friend);
		} else if (relation == REQUEST.toInt()) {
			// case2. 나만 요청한 경우, 아무사이도 아닌 것 (삭제)
			System.out.println("case2");
			friendMapper.deleteRelation(new FriendDto(dto.getUserId(), dto.getFriendId()));
			friendMapper.deleteRelation(new FriendDto(dto.getFriendId(), dto.getUserId()));
		}

	}

}
