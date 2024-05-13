package com.ssafy.plan.model.service;

import java.util.ArrayList;
import java.util.List;

import com.ssafy.member.model.MemberDto;
import com.ssafy.plan.model.PlaceDto;
import com.ssafy.plan.model.PlanDto;
import com.ssafy.plan.model.PlanReponseDto;
import com.ssafy.plan.model.PlanSearchDto;
import com.ssafy.plan.model.mapper.PlaceMapper;
import com.ssafy.plan.model.mapper.PlanMapper;
import com.ssafy.plan.model.mapper.TripWithMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PlanServiceImpl implements PlanService {
	private PlanMapper planMapper;
	private PlaceMapper placeMapper;
	private TripWithMapper tripWithMapper;

	private final int PUBLIC_VISIBILITY = 1;
	private final int PRIVATE_VISIBILITY = 2;

	public PlanServiceImpl(PlanMapper planMapper, PlaceMapper placeMapper, TripWithMapper tripWithMapper) {
		super();
		this.planMapper = planMapper;
		this.placeMapper = placeMapper;
		this.tripWithMapper = tripWithMapper;
	}

	/**
	 * 경로글을 조회하기 위해 필요한 모든 정보들을 받아오는 함수
	 * @param planId
	 * @return
	 * @throws Exception
	 */
	@Override
	public PlanReponseDto getPlanWithPlaces(int planId) throws Exception {
		PlanDto plan = planMapper.getPlan(planId);
		List<PlaceDto> places = placeMapper.getPlaces(planId);
		List<MemberDto> members = tripWithMapper.getMembers(planId);
		return new PlanReponseDto(plan, places, members);
	}

	/**
	 * 전체공개로 설정되어 있는 모든 게시글(경로)들을 받아오는 함수
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<PlanDto> getPlans() throws Exception {
		return planMapper.getPlans(PUBLIC_VISIBILITY);
	}

	/**
	 * 동행으로 참여하는 모든 경로들을 받아오는 함수
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<PlanDto> getTripWithPlanOfUser(String userId) throws Exception {
		// 동행으로 참가하는 모든 plan id들을 리턴할것
		List<PlanDto> plans = new ArrayList<>();
//		for (String planId : tripWithMapper.getPlanIds(userId)) {
//			plans.add(planMapper.getPlan(planId));
//		}
		return plans;
	}

	/**
	 * 검색 조건에 맞는 여행 계획 조회
	 * @param searchDto
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<PlanDto> searchPlans(PlanSearchDto searchDto) throws Exception {
		return planMapper.searchPlans(searchDto);
	}

	@Override
	@Transactional
	public void registPlan(PlanReponseDto newPlan) throws Exception {
		System.out.println("00 "+newPlan.getPlan().getPlanId());
		planMapper.registPlan(newPlan);
		System.out.println("11 "+newPlan.getPlan().getPlanId());
		placeMapper.registPlace(newPlan);
		System.out.println("22");
		tripWithMapper.registTripWith(newPlan);
		
	}
}
