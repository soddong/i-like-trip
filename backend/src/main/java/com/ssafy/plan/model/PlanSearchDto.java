package com.ssafy.plan.model;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class PlanSearchDto {
	private String keyword;
	private String word;
	private boolean onlyMine;
	private String userId;//api 요청에는 쓰이지 않는다
}
