package com.ssafy.plan.model;

import java.util.List;

import com.ssafy.attraction.model.AttractionDto;
import com.ssafy.member.model.MemberDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PlaceDto {
	private int order;
	private String startTime;
	private String endTime;
	private String comment;
	private AttractionDto place;
}
