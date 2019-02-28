package com.example.demo.controller;

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

/**
 * ログインを実施する
 * @param userName ユーザー名
 * @param password パスワード
 * @return
 * @throws LoginFailureException ログイン失敗エラー
 */
@PostMapping("/login")
	@ResponseBody
	public UserDto login(String userName, String password) throws LoginFailureException {
		UserDto user = loginService.login(userName, password);

		// セッションオブジェクトにログインユーザーの情報を格納
		loginSession.setUserId(user.getUserId());
		loginSession.setUserName(user.getUserName());

		return user;

	}

}
