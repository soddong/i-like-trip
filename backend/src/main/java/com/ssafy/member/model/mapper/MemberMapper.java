package com.ssafy.member.model.mapper;

import java.sql.SQLException;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.member.model.MemberDto;
import com.ssafy.member.model.MemberLoginDto;

@Mapper
public interface MemberMapper {

	int idCheck(String userId) throws SQLException;
	
	int joinMember(MemberDto memberDto) throws SQLException;
	
	MemberDto getMember(String userId) throws SQLException;
	
	int updateMember(MemberDto memberDto) throws SQLException;
	
	int deleteMember(String userId) throws SQLException;
}
