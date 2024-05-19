package com.ssafy.friend.controller;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.friend.model.FriendRelationDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ssafy.friend.model.FriendDto;
import com.ssafy.friend.model.FriendInfoDto;
import com.ssafy.friend.model.service.FriendService;

@RestController
@RequestMapping("/friend")
public class FriendController {
	
	private final FriendService friendService;
	
	public FriendController(FriendService friendService) {
		this.friendService = friendService;
	}
	
	private ResponseEntity<String> exceptionHandling(Exception e) {
		e.printStackTrace();
		return new ResponseEntity<String>("Error : " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}

	/**
	 * userId의 모든 친구관계를 가져옴
	 * @param userId
	 * @return
	 */
	@GetMapping("/{userId}")
	public ResponseEntity<?> list(@PathVariable("userId") String userId) {
	    try {
	        List<FriendInfoDto> list = friendService.getRelations(userId);
	        return new ResponseEntity<List<FriendInfoDto>>(list,HttpStatus.OK);
	    } catch (Exception e) {
	        return exceptionHandling(e);
	    }
	}

	/**
	 * user와 friend간의 친구관계를 가져옴
	 * @param userId
	 * @param friendId
	 * @return
	 */
	@GetMapping("/{userId}/relation/{friendId}")
	public ResponseEntity<?> relation(@PathVariable("userId") String userId,
									  @PathVariable("friendId") String friendId) {
	    try {
	        int relation = friendService.getRelation(new FriendDto(userId, friendId));
	        return new ResponseEntity<Integer>(relation, HttpStatus.OK);
	    } catch (Exception e) {
	        return exceptionHandling(e);
	    }
	}

	/**
	 * user가 friend와의 관계 생성 (혹은 관계 업데이트)
	 * @param friendDto
	 * @return
	 */
	@PostMapping("/relation")
	public ResponseEntity<?> add(@RequestBody FriendDto friendDto) {
		try {
			friendService.addFriend(friendDto);
			return new ResponseEntity<Void>(HttpStatus.CREATED);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}

	/**
	 * user가 friend와의 관계 끊음 (혹은 관계 업데이트)
	 * @return
	 */
	@DeleteMapping("/relation")
	public ResponseEntity<?> remove(@RequestBody FriendDto friendDto)  {
		System.out.println("hi");
		try {
			friendService.deleteRelation(friendDto);
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		} catch (SQLException e) {
			return exceptionHandling(e);
		}
	}
	
}
