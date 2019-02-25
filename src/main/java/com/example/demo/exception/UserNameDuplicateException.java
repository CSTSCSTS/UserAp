package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class UserNameDuplicateException extends Exception {

	public UserNameDuplicateException(String msg){
		super(msg);
	}

}
