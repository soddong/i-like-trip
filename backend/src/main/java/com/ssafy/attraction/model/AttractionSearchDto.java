package com.ssafy.attraction.model;

public class AttractionSearchDto {
	int sidoCode;
	int gugunCode;
	int contentType;
	String keyword;

	public AttractionSearchDto() {
	}

	public AttractionSearchDto(int sidoCode, int gugunCode, int contentType, String keyword) {
		super();
		this.sidoCode = sidoCode;
		this.gugunCode = gugunCode;
		this.contentType = contentType;
		this.keyword = keyword;
	}

	public int getSidoCode() {
		return sidoCode;
	}

	public void setSidoCode(int sidoCode) {
		this.sidoCode = sidoCode;
	}

	public int getGugunCode() {
		return gugunCode;
	}

	public void setGugunCode(int gugunCode) {
		this.gugunCode = gugunCode;
	}

	public int getContentType() {
		return contentType;
	}

	public void setContentType(int contentType) {
		this.contentType = contentType;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	@Override
	public String toString() {
		return "SearchTripDto [sidoCode=" + sidoCode + ", gugunCode=" + gugunCode + ", contentType=" + contentType
				+ ", keyword=" + keyword + "]";
	}

}
