package com.example.demo.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dbflute.exentity.PokerUserInfo;
import com.example.demo.domain.model.Money;
import com.example.demo.domain.model.User;
import com.example.demo.dto.UserDto;
import com.example.demo.exception.NotFoundMoneyException;
import com.example.demo.exception.NotMatchLoginUserException;
import com.example.demo.repository.MoneyRepository;
import com.example.demo.repository.UserRepository;

@Service
public class LoginService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private MoneyRepository moneyRepository;

	// ログインする
	public UserDto login(String username, String password) throws NotMatchLoginUserException, NotFoundMoneyException {

		 // 合致するユーザーが存在しなければ、例外を投げる。
	  PokerUserInfo entity =	userRepository.getPokerUserByUsernameAndPassword(username, password)
	  		.orElseThrow(() -> new NotMatchLoginUserException("ログイン失敗"));

	  User user = new User(entity.getUserId(), entity.getUserName(), entity.getPassword(), entity.getLoginDate());

   LocalDateTime now = LocalDateTime.now();
   LocalDateTime loginDate = user.getLoginDate();

   // 現在日時とログイン日時を比較して、ログインがその日初めてかどうか確認
   Calendar nowTime = Calendar.getInstance();
   nowTime.set(now.getYear(), now.getMonthValue() - 1, now.getDayOfMonth());

   Calendar loginTime = Calendar.getInstance();;
   loginTime.set(loginDate.getYear(), loginDate.getMonthValue() - 1, loginDate.getDayOfMonth());

   boolean isFirstLogin = false;
   if(nowTime.compareTo(loginTime) != 0) {
   	Money money = moneyRepository.getMoney(user.getUserId());
   	money.plusMoney(new BigDecimal(100));
   	// ログインがその日初めてならば、所持金を100円増やす。
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
