package com.ssafy.plan.model.mapper;

import com.ssafy.plan.model.PlaceDto;
import com.ssafy.plan.model.PlanReponseDto;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.sql.SQLException;
import java.util.List;

@Mapper
public interface PlaceMapper {
	List<PlaceDto> getPlaces(int planId) throws SQLException;

	void registPlace(@Param("planId") int planId, @Param("places") List<PlaceDto> places)  throws SQLException;
}
