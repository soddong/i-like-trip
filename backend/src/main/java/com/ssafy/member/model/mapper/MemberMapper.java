package com.ssafy.member.model.mapper;

import java.sql.SQLException;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.member.model.MemberDto;
import com.ssafy.member.model.MemberLoginDto;

@Mapper
public interface MemberMapper {

	int idCheck(String userId) throws SQLException;
	
	int joinMember(MemberDto memberDto) throws SQLException;
	
	MemberDto loginMember(MemberLoginDto loginDto) throws SQLException;
	
	int updateMember(MemberDto memberDto) throws SQLException;
	
	int deleteMember(MemberDto memberDto) throws SQLException;
	
	MemberDto findPassword(MemberDto memberDto) throws SQLException;
	
}
