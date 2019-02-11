package com.example.demo.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.domain.model.LoginSession;
import com.example.demo.domain.model.User;
import com.example.demo.exception.NotMatchLoginUserException;
import com.example.demo.service.LoginService;

@Controller
public class LoginController {

	@Autowired
	public LoginService loginService;

	@Autowired
	public LoginSession loginSession;

	@RequestMapping("/")
	public String get() {
		return "index";
	}

	@PostMapping("/login")
	@ResponseBody
	public User login(String username, String password) throws NotMatchLoginUserException {
		User user = loginService.login(username, password);

		// セッションオブジェクトにログインユーザーの情報を格納
		loginSession.setUserId(Optional.of(user.getUserId()));
		loginSession.setUserName(Optional.of(user.getUserName()));

		return user;

	}


}
