package com.ssafy.plan.model;

import com.ssafy.member.model.MemberDto;

import java.util.List;

public class PlanReponseDto {
    private PlanDto plan;
    private List<PlaceDto> places;
    private List<MemberDto> members;

    public PlanReponseDto(PlanDto plan, List<PlaceDto> places, List<MemberDto> members) {
        this.plan = plan;
        this.places = places;
        this.members = members;
    }

    public PlanReponseDto() {

    }

    public PlanDto getPlan() {
        return plan;
    }

    public void setPlan(PlanDto plan) {
        this.plan = plan;
    }

    public List<PlaceDto> getPlaces() {
        return places;
    }

    @Override
	public String toString() {
		return "PlanReponseDto [plan=" + plan + ", places=" + places + ", members=" + members + "]";
	}

	public void setPlaces(List<PlaceDto> places) {
        this.places = places;
    }

    public List<MemberDto> getMembers() {
        return members;
    }

    public void setMembers(List<MemberDto> members) {
        this.members = members;
    }

}
