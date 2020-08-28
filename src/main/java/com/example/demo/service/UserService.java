package com.example.demo.service;

import java.time.LocalDateTime;
import java.util.Locale;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
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

	@Autowired
	private MoneyService moneyService;

	@Autowired
	protected MessageSource messageSource;

	// ユーザー登録を実施する。
	public User resister(String userName, String password) throws UserNameDuplicateException {

		// ユーザー名重複チェック
		if(userRepository.userNameIsDuplicate(userName)) {
		  throw new UserNameDuplicateException(messageSource.getMessage("user.duplicate", null, Locale.JAPAN));
		}

		// ユーザー情報をDBに保存する
		User user = User.builder().userName(userName)
						.password(Optional.of(password)).build();
		userRepository.insert(user);

		PokerUserInfo entity = userRepository.getPokerUserByUsername(userName).get();
		// 所持金情報をDBに保存する
		moneyService.register(entity.getUserId());
		user.setUserId(entity.getUserId());
		user.setLoginDate(entity.getLoginDate());
		return user;

	}

 //OAuthユーザー登録を実施する。
 public User oauthResister(String userName) throws UserNameDuplicateException {

   // ユーザー情報をDBに保存する
   User user = User.builder().userName(userName).build();
   userRepository.oauthInsert(user);

   User entity = userRepository.getOAuthPokerUserByUsername(userName).get();
   // 所持金情報をDBに保存する
   moneyService.register(entity.getUserId());
   user.setUserId(entity.getUserId());
   user.setPassword(Optional.empty());
   user.setLoginDate(entity.getLoginDate());
   return user;

 }

	 public void update(int userId, String userName, String password) {
	 		User user = userRepository.getUserByUserId(userId).get();
			 userRepository.update(new User(userId, userName, Optional.ofNullable(password), LocalDateTime.now(), user.isOAuthUser));
	 }


}
