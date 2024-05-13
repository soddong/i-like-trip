package com.ssafy.member.model.service;

import java.sql.SQLException;

import com.ssafy.member.model.MemberDto;
import com.ssafy.member.model.MemberLoginDto;

public interface MemberService {

	int idCheck(String userId) throws Exception;
	
	int joinMember(MemberDto memberDto) throws Exception;
	
	int updateMember(MemberDto memberDto) throws SQLException;
	
	int deleteMember(MemberDto memberDto) throws SQLException;

	MemberDto loginMember(MemberLoginDto loginDto) throws Exception;
	
//	MemberDto findPassword(MemberDto memberDto) throws SQLException;
	
}
