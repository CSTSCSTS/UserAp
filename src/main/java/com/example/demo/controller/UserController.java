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


/**
 * ユーザー登録をする
 * @param userName ユーザー名
 * @param password パスワード
 * @return
 * @throws UserNameDuplicateException ユーザー名重複エラー
 */
@PostMapping("/user")
	@ResponseBody
	public User add(String userName, String password) throws UserNameDuplicateException {
		return userService.resister(userName, password);
	}

}
