package com.ssafy.member.model.service;

import java.sql.SQLException;

import com.ssafy.member.model.JwtTokenDto;
import com.ssafy.member.model.MemberDto;
import com.ssafy.member.model.MemberLoginDto;
import com.ssafy.member.model.MemberUpdateDto;

public interface MemberService {

	int idCheck(String userId) throws Exception;
	
	int joinMember(MemberDto memberDto) throws Exception;
	
	int updateMember(String userId, MemberUpdateDto memberDto) throws SQLException;
	
	int deleteMember(String memberDto) throws SQLException;
	
	MemberDto getMemberInfo(String userId) throws SQLException;
	
	JwtTokenDto refreshToken(String refreshToken) throws Exception;

	JwtTokenDto loginMember(MemberLoginDto loginDto) throws Exception;
	
	public String forgetPassword(String email);
}
