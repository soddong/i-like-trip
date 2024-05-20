package com.ssafy.attraction.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class AttractionSearchDto {
	Integer sidoCode;
	Integer gugunCode;
	List<Integer> contentType;
	String keyword;
}
