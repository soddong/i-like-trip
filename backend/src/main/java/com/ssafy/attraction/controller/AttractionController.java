package com.ssafy.attraction.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.attraction.model.service.AttractionService;
import com.ssafy.attraction.model.AttractionSearchDto;
import com.ssafy.attraction.model.AttractionDto;

@RestController
@RequestMapping("/attraction")
public class AttractionController {
	private AttractionService attractionService;

	public AttractionController(AttractionService attractionService) {
		this.attractionService = attractionService;
	}

	private ResponseEntity<String> exceptionHandling(Exception e) {
		e.printStackTrace();
		return new ResponseEntity<String>("Error : " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@GetMapping("")
	public ResponseEntity<?> search(AttractionSearchDto searchDto) {
		try {
			List<AttractionDto> result = attractionService.list(searchDto);
			return new ResponseEntity<List<AttractionDto>>(result, HttpStatus.OK);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}
}
