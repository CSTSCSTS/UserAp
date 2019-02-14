package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.domain.model.User;
import com.example.demo.exception.UserNameDuplicateException;
import com.example.demo.service.UserService;

@Controller
public class UserController {

	@Autowired
	public UserService userService;


	// ユーザー登録を実施する
	@PostMapping("/user")
	@ResponseBody
	public User add(String userName, String password) throws UserNameDuplicateException {
		return userService.resister(userName, password);
	}

}
