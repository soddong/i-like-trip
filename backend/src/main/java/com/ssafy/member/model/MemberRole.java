package com.ssafy.member.model;

public enum MemberRole {
	ADMIN,USER;
	
	static public MemberRole getRole(int id) {
		switch (id) {
		case 1:
			return MemberRole.ADMIN;
		case 2:
			return MemberRole.USER;
		default:
			return MemberRole.USER;
		}
	}
}
