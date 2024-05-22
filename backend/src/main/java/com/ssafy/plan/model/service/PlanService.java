package com.ssafy.plan.model.service;

import java.util.List;

import com.ssafy.plan.model.PlanDto;
import com.ssafy.plan.model.PlanReponseDto;
import com.ssafy.plan.model.PlanSearchDto;

public interface PlanService {
	List<PlanDto> getPlans() throws Exception;

	List<PlanDto> getTripWithPlanOfUser(String userId) throws Exception;

	List<PlanDto> searchPlans(PlanSearchDto searchDto) throws Exception;

	/**
	 * 경로글을 조회하기 위해 필요한 모든 정보들을 받아오는 함수
	 * @param planId
	 * @return
	 * @throws Exception
	 */
	PlanReponseDto getPlanWithPlaces(int planId) throws Exception;

	void registPlan(PlanReponseDto newPlan) throws Exception;

	void updatePlan(PlanReponseDto newPlan) throws Exception;

	List<PlanDto> getPlansNew(PlanSearchDto dto) throws Exception;
}
