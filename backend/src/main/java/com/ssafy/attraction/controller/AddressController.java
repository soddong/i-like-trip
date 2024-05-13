package com.ssafy.attraction.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.attraction.model.AddressDto;
import com.ssafy.attraction.model.service.AddressService;

@RestController
@RequestMapping("/address")
public class AddressController {
	
	private AddressService addressService;
	
	public AddressController(AddressService addressService) {
		this.addressService = addressService;
	}
	
	private ResponseEntity<String> exceptionHandling(Exception e) {
		e.printStackTrace();
		return new ResponseEntity<String>("Error : " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@GetMapping("/gugun")
	public ResponseEntity<?> gugunList(@RequestParam(value = "sido") int sidoCode) {
		try {
			List<AddressDto> list= addressService.gugunList(sidoCode);
			return new ResponseEntity<List<AddressDto>>(list, HttpStatus.OK);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}
	
	@GetMapping("/sido")
	public ResponseEntity<?> sidoList() {
		try {
			List<AddressDto> list= addressService.sidoList();
			return new ResponseEntity<List<AddressDto>>(list, HttpStatus.OK);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}
}
