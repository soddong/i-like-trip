package com.ssafy.attraction.model.service;

import java.util.List;

import com.ssafy.attraction.model.AddressDto;

public interface AddressService {
	List<AddressDto> sidoList() throws Exception;

	List<AddressDto> gugunList(int sidoCode) throws Exception;
}
