package com.ssafy.member.controller;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.image.model.ImageInfoDto;
import com.ssafy.image.model.ImageType;
import com.ssafy.image.model.service.ImageService;
import com.ssafy.member.model.MemberUpdateDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.parameters.P;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import com.ssafy.member.model.JwtTokenDto;
import com.ssafy.member.model.MemberDto;
import com.ssafy.member.model.MemberLoginDto;
import com.ssafy.member.model.service.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/member")
public class MemberController {

	private final MemberService memberService;
	private final ImageService imageService;

	public MemberController(MemberService memberService, ImageService imageService) {
		this.memberService = memberService;
		this.imageService = imageService;
	}

	private ResponseEntity<String> exceptionHandling(Exception e) {
		e.printStackTrace();
		return new ResponseEntity<String>("Error : " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@PostMapping("/sign-up")
	public ResponseEntity<?> join(@RequestBody MemberDto memberDto) {
		try {
			memberService.joinMember(memberDto);
			return new ResponseEntity<Void>(HttpStatus.CREATED);
		} catch (Exception e) {
			e.printStackTrace();
			return exceptionHandling(e);
		}
	}

	/**
	 * User 정보 를 업데이트하기 위한 함수
	 * @param userId
	 * @param memberDto
	 * @return
	 */
	@PutMapping("/{userId}")
	public ResponseEntity<?> update(@PathVariable("userId") String userId, @RequestBody MemberUpdateDto memberDto) {
		try {
			System.out.println(memberDto.getProfileImg());
			if (memberDto.getProfileImg() != null) {
				// 1. tmp image db에서 받아온 정보를 실제 file_info db에 저장
				ImageInfoDto imageInfoDto = new ImageInfoDto();
				String saveFile = imageService.extractImageName(memberDto.getProfileImg());
				imageInfoDto.setSaveFile(saveFile);
				System.out.println(saveFile);
				imageInfoDto.setType(ImageType.PROFILE_IMAGE_TYPE.toInt());
				int file_no = imageService.saveImage(imageInfoDto);
				// 2. file_info no를 받아 member db에 저장
				memberDto.setProfileNo(file_no);
			}
			memberService.updateMember(userId, memberDto);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
    }

	@DeleteMapping("/{userId}")
	public ResponseEntity<?> delete(@PathVariable("userId") String userId, HttpSession session) {
		try {
			memberService.deleteMember(userId);
			session.removeAttribute("userinfo");
			session.invalidate();
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		} catch (SQLException e) {
			return exceptionHandling(e);
		}
	}

	@PostMapping("/sign-in")
	public ResponseEntity<?> login(@RequestBody MemberLoginDto loginDto,
			@RequestParam(name = "saveid", required = false) String idsave, HttpServletRequest request,
			HttpServletResponse response) {

		try { 
			JwtTokenDto jwtTokenDto = memberService.loginMember(loginDto);
			if (jwtTokenDto != null) {
				return new ResponseEntity<JwtTokenDto>(jwtTokenDto,HttpStatus.CREATED);
			} else {
				return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
			}
		}catch (BadCredentialsException e) {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
		catch (Exception e) {
			return exceptionHandling(e);
		}
	}
	
	@PostMapping("/refresh")
	public ResponseEntity<?> refreshAccessToken(HttpServletRequest request) {
		String bearerToken = request.getHeader("Authorization");
		String refreshToken = "";
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer")) {
        	refreshToken=bearerToken.substring(7);
        }
        JwtTokenDto jwtTokenDto;
		try {
			jwtTokenDto = memberService.refreshToken(refreshToken);
			return new ResponseEntity<JwtTokenDto>(jwtTokenDto,HttpStatus.CREATED);
		} catch (Exception e) {
			e.printStackTrace();
			return exceptionHandling(e);
		}
	}
	
	@GetMapping("/{user-id}")
	public ResponseEntity<?> getUserInfo(HttpServletRequest request,@PathVariable(name = "user-id") String userId) {
		System.out.println("유저 한명");
		
		try {
			MemberDto memberDto=memberService.getMemberInfo(userId);
			return new ResponseEntity<MemberDto>(memberDto,HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return exceptionHandling(e);
		}
	}
	
	@PostMapping("/forget")
	public ResponseEntity<?> forgetPassWord(String email) {
		memberService.forgetPassword(email);
		
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@GetMapping
	public ResponseEntity<?> searchMembers(@RequestParam(name = "search", required = false) String id) {
		try {
			List<MemberDto> members = memberService.searchMembers(id);
			return new ResponseEntity<>(members, HttpStatus.OK);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}
	
}
