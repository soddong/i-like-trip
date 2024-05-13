package com.ssafy.attraction.model.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ssafy.attraction.model.mapper.AttractionMapper;
import com.ssafy.attraction.model.AttractionSearchDto;
import com.ssafy.attraction.model.AttractionDto;

@Service
public class AttractionServiceImpl implements AttractionService {
	private AttractionMapper attractionMapper;

	public AttractionServiceImpl(AttractionMapper attractionMapper) {
		super();
		this.attractionMapper = attractionMapper;
	}

	@Override
	public List<AttractionDto> list(AttractionSearchDto searchDto) throws Exception {
		return attractionMapper.list(searchDto);
	}
}
