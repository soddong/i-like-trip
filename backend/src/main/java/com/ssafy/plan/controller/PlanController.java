package com.ssafy.plan.controller;

import java.util.List;

import com.ssafy.plan.model.PlanReponseDto;
import com.ssafy.plan.model.PlanSearchDto;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ssafy.plan.model.PlanDto;
import com.ssafy.plan.model.service.PlanService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/plans")
public class PlanController {

	private final PlanService planService;

	public PlanController(PlanService planService) {
		this.planService = planService;
	}

	private ResponseEntity<String> exceptionHandling(Exception e) {
		e.printStackTrace();
		return new ResponseEntity<String>("Error : " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}

	/**
	 * TODO: 계획경로(게시글) 조회하기
	 *
	 * @param planId
	 * @return
	 */
	@GetMapping("/{planId}")
	public ResponseEntity<?> viewPlanWithPlaces(@PathVariable(value = "planId") int planId) {
		try {
			PlanReponseDto planReponseDto = planService.getPlanWithPlaces(planId);
			if (planReponseDto == null) {
				return ResponseEntity.notFound().build();
			}
			return new ResponseEntity<PlanReponseDto>(planReponseDto, HttpStatus.OK);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}

	/**
	 * TODO: 검색 조건에 따른 여행계획 조회
	 *
	 * @param searchDto
	 * @return
	 */
	@GetMapping("")
	public ResponseEntity<?> viewPlans(@ModelAttribute PlanSearchDto searchDto, HttpServletRequest request) {
		try {
			if(searchDto.isOnlyMine()) {
				searchDto.setUserId(request.getUserPrincipal().getName());
			}
			List<PlanDto> planDtos = planService.getPlansNew(searchDto);
//			if (searchDto.getWord() == null || searchDto.getWord().isBlank()) {
//				planDtos = planService.getPlans();
//			} else {
//				planDtos = planService.searchPlans(searchDto);
//			}
			if (planDtos.isEmpty()) {
				return ResponseEntity.notFound().build();
			}
			return new ResponseEntity<List<PlanDto>>(planDtos, HttpStatus.OK);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}

	// TODO : 등록하기
	@PostMapping("")
	public ResponseEntity<?> registPlan(@RequestBody PlanReponseDto newPlan) {
		try {
			planService.registPlan(newPlan);
			return new ResponseEntity<Void>(HttpStatus.CREATED);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}

	// TODO : 수정하기
	@PutMapping("")
	public ResponseEntity<?> updatePlan(@RequestBody PlanReponseDto newPlan) {
		try {
			planService.updatePlan(newPlan);
			return new ResponseEntity<Void>(HttpStatus.CREATED);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}
}
