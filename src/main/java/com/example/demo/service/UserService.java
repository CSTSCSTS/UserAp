package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dbflute.exentity.PokerUserInfo;
import com.example.demo.domain.model.User;
import com.example.demo.exception.UserNameDuplicateException;
import com.example.demo.repository.MoneyRepository;
import com.example.demo.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private MoneyRepository moneyRepository;


	public User resister(String userName, String password) throws UserNameDuplicateException {

		// ユーザー名重複チェック
		if(userRepository.getPokerUserByUsername(userName).isPresent()) {
		  throw new UserNameDuplicateException("ユーザー名が重複しています。");
		}

		// ユーザー新規作成
		userRepository.insert(userName, password);

		PokerUserInfo entity = userRepository.getPokerUserByUsername(userName).get();

		moneyRepository.save(entity.getUserId(), 1000);
		return new User(entity.getUserId(), entity.getUserName(), entity.getPassword(), entity.getLoginDate());

	}

}
