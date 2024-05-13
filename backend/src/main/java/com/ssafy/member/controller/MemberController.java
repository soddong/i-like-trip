package com.ssafy.member.controller;

import java.sql.SQLException;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.member.model.MemberDto;
import com.ssafy.member.model.MemberLoginDto;
import com.ssafy.member.model.service.MemberService;
import jakarta.servlet.http.Cookie;
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

	@PostMapping("")
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
			MemberDto memberDto = new MemberDto();
			memberDto.setUserId(userId);
			memberService.deleteMember(memberDto);
			session.removeAttribute("userinfo");
			session.invalidate();
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		} catch (SQLException e) {
			return exceptionHandling(e);
		}
	}

	@PostMapping("/login")
	public ResponseEntity<?> login(MemberLoginDto loginDto,
			@RequestParam(name = "saveid", required = false) String idsave, HttpServletRequest request,
			HttpServletResponse response) {
		
		String userId = loginDto.getUserId();

		try {  
			MemberDto memberDto = memberService.loginMember(loginDto);

			if (memberDto != null) {
				System.out.println(memberDto.toString());
				HttpSession session = request.getSession();
				session.setAttribute("userinfo", memberDto);

				if ("ok".equals(idsave)) {
					Cookie cookie = new Cookie("ssafy_id", userId);
					cookie.setPath(request.getContextPath());
					cookie.setMaxAge(60 * 60 * 24 * 365 * 40);
					response.addCookie(cookie);
				} else {
					Cookie cookies[] = request.getCookies();
					if (cookies != null) {
						for (Cookie cookie : cookies) {
							if ("ssafy_id".equals(cookie.getName())) {
								cookie.setMaxAge(0);
								response.addCookie(cookie);
								break;
							}
						}
					}
				}
				return new ResponseEntity<>(HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
			}
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}

	@GetMapping("/logout")
	public ResponseEntity<?> logout(HttpSession session) {
		session.removeAttribute("userinfo");
		session.invalidate();
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
