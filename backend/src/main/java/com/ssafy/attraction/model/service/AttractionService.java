package com.ssafy.attraction.model.service;

import java.util.List;

import com.ssafy.attraction.model.AttractionSearchDto;
import com.ssafy.attraction.model.AttractionDto;

public interface AttractionService {

	List<AttractionDto> list(AttractionSearchDto searchDto) throws Exception;
}
