package com.ssafy.member.model.service;

import java.sql.SQLException;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ssafy.member.model.MemberDto;
import com.ssafy.member.model.MemberLoginDto;
import com.ssafy.member.model.mapper.MemberMapper;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class MemberServiceImpl implements MemberService, UserDetailsService  {
	private MemberMapper memberMapper;

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
	public int deleteMember(String userId) throws SQLException {
		return memberMapper.deleteMember(userId);
	}

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
			return memberMapper.getMember(username);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new UsernameNotFoundException(username);
		}
    }

}
