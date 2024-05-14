package com.ssafy.member.model.service;

import java.sql.SQLException;

import com.ssafy.member.model.JwtTokenDto;
import com.ssafy.member.model.MemberDto;
import com.ssafy.member.model.MemberLoginDto;

public interface MemberService {

	int idCheck(String userId) throws Exception;
	
	int joinMember(MemberDto memberDto) throws Exception;
	
	int updateMember(MemberDto memberDto) throws SQLException;
	
	int deleteMember(String memberDto) throws SQLException;

	JwtTokenDto loginMember(MemberLoginDto loginDto) throws Exception;
	
	public String forgetPassword(String email);
}
