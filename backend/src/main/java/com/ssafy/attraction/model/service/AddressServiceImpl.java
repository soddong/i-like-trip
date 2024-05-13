package com.ssafy.attraction.model.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ssafy.attraction.model.AddressDto;
import com.ssafy.attraction.model.mapper.AddressMapper;

@Service
public class AddressServiceImpl implements AddressService {
	private AddressMapper addressMapper;

	public AddressServiceImpl(AddressMapper addressMapper) {
		super();
		this.addressMapper = addressMapper;
	}

	@Override
	public List<AddressDto> sidoList() throws Exception {
		return addressMapper.sidoList();
	}

	@Override
	public List<AddressDto> gugunList(int sidoCode) throws Exception {
		return addressMapper.gugunList(sidoCode);
	}

}
