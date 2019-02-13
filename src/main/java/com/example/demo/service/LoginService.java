package com.example.demo.service;

import java.time.LocalDateTime;
import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dbflute.exentity.PokerUserInfo;
import com.example.demo.dbflute.exentity.PossessionMoney;
import com.example.demo.domain.model.User;
import com.example.demo.exception.NotMatchLoginUserException;
import com.example.demo.repository.MoneyRepository;
import com.example.demo.repository.UserRepository;

@Service
public class LoginService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private MoneyRepository moneyRepository;

	public User login(String username, String password) throws NotMatchLoginUserException {

	  PokerUserInfo entity =	userRepository.getPokerUserByUsernameAndPassword(username, password)
	  		.orElseThrow(() -> new NotMatchLoginUserException("ログイン失敗"));

	  User user = new User(entity.getUserId(), entity.getUserName(), entity.getPassword(), entity.getLoginDate());

   LocalDateTime now = LocalDateTime.now();
   LocalDateTime loginDate = user.getLoginDate();

   // ログインがその日初めてかどうか確認
   Calendar nowTime = Calendar.getInstance();
   nowTime.set(now.getYear(), now.getMonthValue() - 1, now.getDayOfMonth());

   Calendar loginTime = Calendar.getInstance();;
   loginTime.set(loginDate.getYear(), loginDate.getMonthValue() - 1, loginDate.getDayOfMonth());

   if(nowTime.compareTo(loginTime) != 0) {
   	PossessionMoney money = moneyRepository.getMoney(user.getUserId()).get();
   	// ログインがその日初めてならば、所持金を100円増やす。
   	moneyRepository.save(user.getUserId(), money.getPossessionMoney().intValue() + 100, LocalDateTime.now());
   }

   // ログイン日時を更新
   userRepository.update(user.getUserId(), user.getUserName(), user.getPassword(), LocalDateTime.now());

	  return user;

	}

}
