package com.ssafy.plan.model;

import com.ssafy.attraction.model.AttractionDto;

public class PlaceDto {
	private int order;
	private String startTime;
	private String endTime;
	private String comment;
	private AttractionDto place;

	public int getOrder() {
		return order;
	}

	public void setOrder(int order) {
		this.order = order;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public AttractionDto getPlace() {
		return place;
	}

	public void setPlace(AttractionDto place) {
		this.place = place;
	}

	@Override
	public String toString() {
		return "PlaceDto [order=" + order + ", startTime=" + startTime + ", endTime=" + endTime + ", comment=" + comment
				+ ", place=" + place + "]";
	}

}
