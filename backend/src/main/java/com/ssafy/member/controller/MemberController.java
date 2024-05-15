package com.ssafy.member.controller;

import java.sql.SQLException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

	public MemberController(MemberService memberService) {
		this.memberService = memberService;
	}

	private ResponseEntity<String> exceptionHandling(Exception e) {
		e.printStackTrace();
		return new ResponseEntity<String>("Error : " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@PostMapping("/sign-up")
	public ResponseEntity<?> join(MemberDto memberDto) {
		try {
			memberService.joinMember(memberDto);
			return new ResponseEntity<Void>(HttpStatus.CREATED);
		} catch (Exception e) {
			e.printStackTrace();
			return exceptionHandling(e);
		}
	}

	@PutMapping("")
	public ResponseEntity<?> update(MemberDto memberDto) {
		try {
			memberService.updateMember(memberDto);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (SQLException e) {
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
	
	
	@PostMapping("/forget")
	public ResponseEntity<?> forgetPassWord(String email) {
		memberService.forgetPassword(email);
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
}
