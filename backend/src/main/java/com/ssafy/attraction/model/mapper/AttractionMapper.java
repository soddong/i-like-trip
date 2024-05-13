package com.ssafy.attraction.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.attraction.model.AttractionSearchDto;
import com.ssafy.attraction.model.AttractionDto;

@Mapper
public interface AttractionMapper {

	List<AttractionDto> list(AttractionSearchDto searchDto);
}
