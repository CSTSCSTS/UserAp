package com.example.demo.service;

import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import com.example.demo.constants.PokerConstants;
import com.example.demo.dbflute.exentity.PokerUserInfo;
import com.example.demo.domain.model.Money;
import com.example.demo.domain.model.User;
import com.example.demo.dto.UserDto;
import com.example.demo.exception.LoginFailureException;
import com.example.demo.repository.MoneyRepository;
import com.example.demo.repository.UserRepository;

@Service
public class LoginService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private MoneyRepository moneyRepository;

	@Autowired
	protected MessageSource messageSource;

	// ログインする
	public UserDto login(String username, String password) throws LoginFailureException {

		 // 合致するユーザーが存在しなければ、例外を投げる。
	  PokerUserInfo entity =	userRepository.getPokerUserByUsernameAndPassword(username, password)
	  		.orElseThrow(() -> new LoginFailureException(messageSource.getMessage("login.fali", null, Locale.JAPAN)));

	  User user = new User(entity.getUserId(), entity.getUserName(), entity.getPassword(), entity.getLoginDate());

   LocalDateTime now = LocalDateTime.now();
   LocalDateTime loginDate = user.getLoginDate();

   Calendar nowTime = Calendar.getInstance();
   nowTime.set(now.getYear(), now.getMonthValue() - 1, now.getDayOfMonth());

   Calendar loginTime = Calendar.getInstance();
   loginTime.set(loginDate.getYear(), loginDate.getMonthValue() - 1, loginDate.getDayOfMonth());

   boolean isFirstLogin = false;
   // 現在日時とログイン日時を比較して、ログインがその日初めてかどうか確認
   if(nowTime.compareTo(loginTime) != 0) {
   	Money money = moneyRepository.getMoney(user.getUserId());
   	// ログインがその日初めてならば、所持金を100円増やす。
   	money.plusMoney(PokerConstants.LOGIN_BONUS);
   	moneyRepository.save(money);
   	isFirstLogin = true;
   }

   // ログイン日時を更新
   userRepository.update(new User(user.getUserId(), user.getUserName(), user.getPassword(), LocalDateTime.now()));

	  return UserDto.builder()
	  		        .userId(user.getUserId())
	  		        .userName(user.getUserName())
	  		        .isFirstLogin(isFirstLogin)
	  		        .build();

	}

}
