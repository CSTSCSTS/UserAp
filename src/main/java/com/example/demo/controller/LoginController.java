package com.example.demo.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.domain.model.LoginSession;
import com.example.demo.dto.UserDto;
import com.example.demo.exception.LoginFailureException;
import com.example.demo.service.LoginService;

@Controller
public class LoginController {

	@Autowired
	public LoginService loginService;

	@Autowired
	public LoginSession loginSession;

	// ログイン画面を表示する
	@RequestMapping("/")
	public String get() {
		return "index";
	}

	// リクエストで送られてきたユーザー名・パスワードをログイン処理を行う
	@PostMapping("/login")
	@ResponseBody
	public UserDto login(String userName, String password) throws LoginFailureException {
		UserDto user = loginService.login(userName, password);

		// セッションオブジェクトにログインユーザーの情報を格納
		loginSession.setUserId(Optional.of(user.getUserId()));
		loginSession.setUserName(Optional.of(user.getUserName()));

		return user;

	}


}
