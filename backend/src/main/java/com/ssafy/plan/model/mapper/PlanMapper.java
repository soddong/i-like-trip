package com.ssafy.plan.model.mapper;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.member.model.MemberDto;
import com.ssafy.plan.model.PlaceDto;
import com.ssafy.plan.model.PlanDto;
import com.ssafy.plan.model.PlanReponseDto;
import com.ssafy.plan.model.PlanSearchDto;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface PlanMapper {

	PlanDto getPlan(int planId) throws SQLException;

	List<PlanDto> getPlans(int visibility) throws SQLException;

	List<PlanDto> searchPlans(@Param("word") String word) throws SQLException;

	void registPlan(PlanDto newPlan) throws SQLException;

    void updatePlan(PlanDto plan);
    
    List<PlanDto> getPlansNew(PlanSearchDto dto) throws SQLException;
}
