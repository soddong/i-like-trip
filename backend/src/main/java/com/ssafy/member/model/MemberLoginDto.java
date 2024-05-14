package com.ssafy.member.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class MemberLoginDto {
	
	private String id;
	private String password;
}
