package com.ssafy.attraction.model;

public class AddressDto {
	private String sidoName;
	private int sidoCode;
	private String gugunName;
	private int gugunCode;

	public AddressDto() {
	}

	public AddressDto(String sidoName, int sidoCode, String gugunName, int gugunCode) {
		super();
		this.sidoName = sidoName;
		this.sidoCode = sidoCode;
		this.gugunName = gugunName;
		this.gugunCode = gugunCode;
	}

	public String getSidoName() {
		return sidoName;
	}

	public void setSidoName(String sidoNmae) {
		this.sidoName = sidoNmae;
	}

	public int getSidoCode() {
		return sidoCode;
	}

	public void setSidoCode(int sidoCode) {
		this.sidoCode = sidoCode;
	}

	public String getGugunName() {
		return gugunName;
	}

	public void setGugunName(String gugunName) {
		this.gugunName = gugunName;
	}

	public int getGugunCode() {
		return gugunCode;
	}

	public void setGugunCode(int gugunCode) {
		this.gugunCode = gugunCode;
	}

	@Override
	public String toString() {
		return "AddressDto [sidoNmae=" + sidoName + ", sideCode=" + sidoCode + ", gugunName=" + gugunName
				+ ", gugunCode=" + gugunCode + "]";
	}

}
