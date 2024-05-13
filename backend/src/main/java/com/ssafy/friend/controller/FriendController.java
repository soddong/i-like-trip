package com.ssafy.friend.controller;

import java.sql.SQLException;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.friend.model.FriendDto;
import com.ssafy.friend.model.FriendRelationDto;
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
	
	@GetMapping("/{userId}")
	public ResponseEntity<?> list(@PathVariable("userId") String userId) {
	    try {
	        List<FriendRelationDto> list = friendService.getMembers(userId);
	        return new ResponseEntity<List<FriendRelationDto>>(list,HttpStatus.OK);
	    } catch (Exception e) {
	        return exceptionHandling(e);
	    }
	}
	
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

	@PostMapping("/relation")
	public ResponseEntity<?> add(FriendDto friendDto) {
		try {
			boolean isDuplicated = false;
			if (!isDuplicated) {
				friendService.addFriend(friendDto);
				return new ResponseEntity<FriendDto>(friendDto, HttpStatus.CREATED);
			}
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}
	
	@DeleteMapping("/{userId}/relation/{friendId}")
	public ResponseEntity<?> remove(@PathVariable("userId") String userId,
									@PathVariable("friendId") String friendId)  {
		try {
			friendService.deleteFriend(new FriendDto(userId, friendId));
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		} catch (SQLException e) {
			return exceptionHandling(e);
		}
	}
	
}
