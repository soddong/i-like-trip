package com.ssafy.attraction.model.mapper;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.attraction.model.AddressDto;

@Mapper
public interface AddressMapper {
	List<AddressDto> sidoList() throws SQLException;

	List<AddressDto> gugunList(int sidoCode) throws SQLException;
}
