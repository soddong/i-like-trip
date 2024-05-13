package com.ssafy.member.model;

public class MemberDto {

	private String userId;
	private String userName;
	private String userPassword;
	private String tel;
	private String location;
	private String joinDate;

	public MemberDto() {
		
	}
	
	public MemberDto(String userId, String userName, String userPassword, String tel, String location, String joinDate) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.userPassword = userPassword;
		this.tel = tel;
		this.location = location;
		this.joinDate = joinDate;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}
	
	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}


	public String getJoinDate() {
		return joinDate;
	}

	public void setJoinDate(String joinDate) {
		this.joinDate = joinDate;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "[" + userName + ", " + tel + ", " + location + ", " + joinDate + "]";
	}
	
	

}
