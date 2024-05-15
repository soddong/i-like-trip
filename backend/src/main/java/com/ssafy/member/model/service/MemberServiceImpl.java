package com.ssafy.member.model.service;

import java.sql.SQLException;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
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

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class MemberServiceImpl implements MemberService, UserDetailsService {
	private final MemberMapper memberMapper;
	private final AuthenticationManagerBuilder authenticationManagerBuilder;
	private final JwtTokenProvider jwtTokenProvider;
	private final BCryptPasswordEncoder passwordEncoder;
	private final JavaMailSender mailSender;

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
		return jwtToken;
	}

	@Override
	public JwtTokenDto refreshToken(String refreshToken) throws Exception {
		if(jwtTokenProvider.validateToken(refreshToken)) {
			String userId = jwtTokenProvider.getUserId(refreshToken);
			MemberDto member = memberMapper.getMember(userId);
			String newAccessToken = jwtTokenProvider.createAccessToken(userId, member.getAuthorities());
			return JwtTokenDto.builder()
	                .grantType("bearer")
	                .accessToken(newAccessToken)
	                .refreshToken(refreshToken)
	                .build();
		}else {
			throw new Exception("유요하지 않은 리프레시 토큰입니다.");
		}
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
			MemberDto memberDto = memberMapper.getMember(username);
			if(memberDto!=null) {
				return memberDto;
			}else {
				throw new UsernameNotFoundException(username);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new UsernameNotFoundException(username);
		}
	}
	
    //mail을 어디서 보내는지, 어디로 보내는지 , 인증 번호를 html 형식으로 어떻게 보내는지 작성합니다.
    public String forgetPassword(String email) {
        String setFrom = "sjwstudy04@gmail.com"; // email-config에 설정한 자신의 이메일 주소를 입력
        String toMail = email;
        String title = "회원 가입 인증 이메일 입니다."; // 이메일 제목
        String content =
                "나의 APP을 방문해주셔서 감사합니다." + 	//html 형식으로 작성 !
                        "<br><br>" +
                        "인증 번호는 " +  "입니다." +
                        "<br>" +
                        "인증번호를 제대로 입력해주세요"; //이메일 내용 삽입
        mailSend(setFrom, toMail, title, content);
        return null;
    }

    //이메일을 전송합니다.
    private void mailSend(String setFrom, String toMail, String title, String content) {
        MimeMessage message = mailSender.createMimeMessage();//JavaMailSender 객체를 사용하여 MimeMessage 객체를 생성
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message,true,"utf-8");//이메일 메시지와 관련된 설정을 수행합니다.
            // true를 전달하여 multipart 형식의 메시지를 지원하고, "utf-8"을 전달하여 문자 인코딩을 설정
            helper.setFrom(setFrom);//이메일의 발신자 주소 설정
            helper.setTo(toMail);//이메일의 수신자 주소 설정
            helper.setSubject(title);//이메일의 제목을 설정
            helper.setText(content,true);//이메일의 내용 설정 두 번째 매개 변수에 true를 설정하여 html 설정으로한다.
            mailSender.send(message);
        } catch (MessagingException e) {//이메일 서버에 연결할 수 없거나, 잘못된 이메일 주소를 사용하거나, 인증 오류가 발생하는 등 오류
            // 이러한 경우 MessagingException이 발생
            e.printStackTrace();//e.printStackTrace()는 예외를 기본 오류 스트림에 출력하는 메서드
        }
        

    }

}
