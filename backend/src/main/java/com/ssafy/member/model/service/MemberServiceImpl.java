package com.ssafy.member.model.service;

import java.sql.SQLException;

import org.springframework.stereotype.Service;

import com.ssafy.member.model.MemberDto;
import com.ssafy.member.model.MemberLoginDto;
import com.ssafy.member.model.mapper.MemberMapper;

@Service
public class MemberServiceImpl implements MemberService {
	
	private static MemberService memberService;

	private MemberMapper memberMapper;
	
	public MemberServiceImpl(MemberMapper memberMapper) {
		this.memberMapper = memberMapper;
	}

	public static MemberService getMemberService() {
		return memberService;
	}

	@Override
	public int idCheck(String userId) throws Exception {
		return memberMapper.idCheck(userId);
	}

	@Override
	public int joinMember(MemberDto memberDto) throws Exception {
		return memberMapper.joinMember(memberDto);
	}

	@Override
	public MemberDto loginMember(MemberLoginDto loginDto) throws Exception {
		MemberDto loginResult= memberMapper.loginMember(loginDto);
		return loginResult;
	}

	@Override
	public int updateMember(MemberDto memberDto) throws SQLException {
		return memberMapper.updateMember(memberDto);
	}

	@Override
	public int deleteMember(MemberDto memberDto) throws SQLException {
		return memberMapper.deleteMember(memberDto);
	}

}
