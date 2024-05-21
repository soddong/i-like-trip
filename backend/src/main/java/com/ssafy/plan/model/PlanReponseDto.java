package com.ssafy.plan.model;

import com.ssafy.member.model.MemberDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class PlanReponseDto {
    private PlanDto plan;
    private List<PlaceDto> places;
    private List<MemberDto> members;
}
