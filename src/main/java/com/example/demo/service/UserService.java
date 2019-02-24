package com.example.demo.service;

import java.time.LocalDateTime;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import com.example.demo.constants.PokerConstants;
import com.example.demo.dbflute.exentity.PokerUserInfo;
import com.example.demo.domain.model.Money;
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

	@Autowired
	protected MessageSource messageSource;

	// ユーザー登録を実施する。
	public User resister(String userName, String password) throws UserNameDuplicateException {

		// ユーザー名重複チェック
		if(userRepository.userNameIsDuplicate(userName)) {
		  throw new UserNameDuplicateException(messageSource.getMessage("user.duplicate", null, Locale.JAPAN));
		}

		// ユーザー情報をDBに保存する
		User user = new User(userName, password);
		userRepository.insert(new User(userName, password));

		PokerUserInfo entity = userRepository.getPokerUserByUsername(userName).get();

	 // 所持金情報をDBに保存する
		moneyRepository.save(new Money(entity.getUserId(), PokerConstants.USER_REGISTER_BOUNS, LocalDateTime.now()));
		user.setUserId(entity.getUserId());
		user.setLoginDate(entity.getLoginDate());
		return user;

	}

}
