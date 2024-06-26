package com.ssafy.plan.model.mapper;

import com.ssafy.member.model.MemberDto;
import com.ssafy.plan.model.PlanDto;
import com.ssafy.plan.model.PlanReponseDto;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.sql.SQLException;
import java.util.List;

@Mapper
public interface TripWithMapper {
    List<MemberDto> getMembers(int planId) throws SQLException;

    List<PlanDto> getPlanIds(String userId) throws SQLException;

	void registTripWith(@Param("planId") int planId, @Param("members") List<MemberDto> newPlan) throws SQLException;

    void updateTripWith(@Param("planId") int planId, @Param("members") List<MemberDto> members);

    int deleteTripWith(@Param("planId") int plan_id) throws SQLException;
}
