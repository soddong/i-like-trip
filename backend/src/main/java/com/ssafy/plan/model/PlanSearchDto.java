package com.ssafy.plan.model;

public class PlanSearchDto {
	private String keyword;
	private String word;

	public PlanSearchDto(String keyword, String word) {
		super();
		this.keyword = keyword;
		this.word = word;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	@Override
	public String toString() {
		return "PlanSearchDto [keyword=" + keyword + ", word=" + word + "]";
	}

}
