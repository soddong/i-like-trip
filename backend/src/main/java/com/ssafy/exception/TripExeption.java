package com.ssafy.exception;

public class TripExeption extends IllegalStateException {
	
	private String message;

	public TripExeption(String message) {
		super("[TripProject ErrorMessage] " + message);
	}
}
