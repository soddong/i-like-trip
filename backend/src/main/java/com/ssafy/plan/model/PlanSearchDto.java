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
}
