package com.ssafy.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.ssafy.filter.JwtRequestFilter;
import com.ssafy.util.JwtTokenProvider;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {
	private final JwtTokenProvider jwtTokenProvider;

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.csrf((csrf) -> csrf.disable());
		http.cors(Customizer.withDefaults());

		http.sessionManagement(
				sessionManagement -> sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

		http.formLogin(form -> form.disable());
		http.httpBasic(AbstractHttpConfigurer::disable);

		http.authorizeHttpRequests(auth -> auth
				.requestMatchers("/ws/chat/**").permitAll()
				.requestMatchers("/chat/**").permitAll()
				.requestMatchers("/swagger-ui/**","/v3/api-docs/**").permitAll()//스웨거
				.requestMatchers("/member/sign-in", "/member/refresh", "/member/sign-up","/member/forget").permitAll()//인증 인가
				.requestMatchers(HttpMethod.GET,"/board/**","/plans/**","/upload/**","/gpt","/attraction","/address/**").permitAll()
				.anyRequest().authenticated()
				);

		http.addFilterBefore(new JwtRequestFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter.class);

		return http.build();
	}

	// 암호화 기능
	@Bean
	public static BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}