package com.ssafy.member.model.service;

import java.sql.SQLException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.ssafy.member.model.JwtTokenDto;
import com.ssafy.member.model.MemberDto;
import com.ssafy.member.model.MemberLoginDto;
import com.ssafy.member.model.mapper.MemberMapper;
import com.ssafy.util.JwtTokenProvider;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class MemberServiceImpl implements MemberService, UserDetailsService {
	private final MemberMapper memberMapper;
	private final AuthenticationManagerBuilder authenticationManagerBuilder;
	private final JwtTokenProvider jwtTokenProvider;
	private final BCryptPasswordEncoder passwordEncoder;

	@Override
	public int idCheck(String userId) throws Exception {
		return memberMapper.idCheck(userId);
	}

	@Override
	public int joinMember(MemberDto memberDto) throws Exception {
		memberDto.setPassword(passwordEncoder.encode(memberDto.getPassword()));
		return memberMapper.joinMember(memberDto);
	}

	@Override
	public JwtTokenDto loginMember(MemberLoginDto loginDto) throws Exception {
		// 1. username + password 를 기반으로 Authentication 객체 생성
		// 이때 authentication 은 인증 여부를 확인하는 authenticated 값이 false
		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
				loginDto.getId(), loginDto.getPassword());

		// 2. 실제 검증. authenticate() 메서드를 통해 요청된 Member 에 대한 검증 진행
		// authenticate 메서드가 실행될 때 CustomUserDetailsService 에서 만든 loadUserByUsername 메서드
		Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

		// 3. 인증 정보를 기반으로 JWT 토큰 생성
		JwtTokenDto jwtToken = jwtTokenProvider.generateToken(authentication);

		MemberDto loginResult = memberMapper.getMember(loginDto.getId());
		if (passwordEncoder.matches(loginDto.getPassword(), loginResult.getPassword()))
			return jwtToken;
		else
			return null;
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
