package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class IllegalBetException extends Exception {

	public IllegalBetException(String msg){
		super(msg);
	}

}
