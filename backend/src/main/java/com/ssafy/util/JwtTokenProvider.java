package com.ssafy.util;

import java.security.Key;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;


import com.ssafy.member.model.JwtTokenDto;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class JwtTokenProvider {
    private final Key key;
    @Value("${jwt.expire-ms}")
    private int expireTime;

    // application.yml에서 secret 값 가져와서 key에 저장
    public JwtTokenProvider(@Value("${jwt.secret}") String secretKey) {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        this.key = Keys.hmacShaKeyFor(keyBytes);
    }

    // Member 정보를 가지고 AccessToken, RefreshToken을 생성하는 메서드
    public JwtTokenDto generateToken(Authentication authentication) {
        long now = (new Date()).getTime();

        // Access Token 생성
        String accessToken = createAccessToken(authentication.getName(), authentication.getAuthorities());
        // Refresh Token 생성
        String refreshToken = Jwts.builder().issuer("좋아요행")
        		.subject("refresh")
        		.audience().add(authentication.getName()).and()
                .expiration(new Date(now + expireTime*7))
                .signWith(key)
                .compact();

        return JwtTokenDto.builder()
                .grantType("bearer")
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }
    
    public String getUserId(String token) {
    	Claims claims = parseClaims(token);
    	return claims.getAudience().iterator().next();
    }
    
    public String createAccessToken(String userId, Collection<? extends GrantedAuthority> authorities) {
        long now = (new Date()).getTime();
        // Access Token 생성
        Date accessTokenExpiresIn = new Date(now + expireTime);
        String authoritiesString = authorities.stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));
        String accessToken = Jwts.builder().issuer("좋아요행")
        		.subject("access")
        		.audience().add(userId).and()
        		.expiration(accessTokenExpiresIn)
                .claim("auth", authoritiesString)
                .signWith(key)
                .compact();
        return accessToken;
    }

    // Jwt 토큰을 복호화하여 토큰에 들어있는 정보를 꺼내는 메서드
    public Authentication getAuthentication(String accessToken) {
        // Jwt 토큰 복호화
        Claims claims = parseClaims(accessToken);

        //auth가 없다면 guest 권한 부여
        if (claims.get("auth") == null) {
        	Collection<SimpleGrantedAuthority> guestAuth = new ArrayList<>();
        	guestAuth.add(new SimpleGrantedAuthority("GUEST"));
            return new UsernamePasswordAuthenticationToken("", "", guestAuth);
        }

        // 클레임에서 권한 정보 가져오기
        Collection<? extends GrantedAuthority> authorities = Arrays.stream(claims.get("auth").toString().split(","))
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());

        // UserDetails 객체를 만들어서 Authentication return
        // UserDetails: interface, User: UserDetails를 구현한 class
        if(claims.getAudience().isEmpty()) {
        	throw new RuntimeException("대상이 없는 토근입니다.");
        }
        UserDetails principal = new User(claims.getAudience().iterator().next(), "", authorities);
        return new UsernamePasswordAuthenticationToken(principal, "", authorities);
    }

    // 토큰 정보를 검증하는 메서드
    public boolean validateToken(String token) {
        try {
        	Jwts.parser()
        			.verifyWith((SecretKey)key)
                    .build()
                    .parseSignedClaims(token);
            return true;
        } catch (SecurityException | MalformedJwtException e) {
            log.info("Invalid JWT Token", e);
        } catch (IllegalArgumentException e) {
            log.info("JWT claims string is empty.", e);
        }
        return false;
    }

    private Claims parseClaims(String token) {
    	return Jwts.parser()
    			.verifyWith((SecretKey)key)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

}