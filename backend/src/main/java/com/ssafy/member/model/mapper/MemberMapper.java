package com.ssafy.member.model.mapper;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.member.model.MemberUpdateDto;
import org.apache.ibatis.annotations.Mapper;

import com.ssafy.member.model.MemberDto;
import com.ssafy.member.model.MemberLoginDto;

@Mapper
public interface MemberMapper {

	int idCheck(String userId) throws SQLException;
	
	int joinMember(MemberDto memberDto) throws SQLException;
	
	MemberDto getMember(String userId) throws SQLException;
	
	int updateMember(String userId, MemberUpdateDto memberDto) throws SQLException;
	
	int deleteMember(String userId) throws SQLException;

	List<MemberDto> searchMembers(String id);
}
