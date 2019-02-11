package com.example.demo.service;

import static org.assertj.core.api.Assertions.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.example.demo.controller.LoginController;
import com.example.demo.dbflute.exbhv.PokerUserInfoBhv;
import com.example.demo.dbflute.exbhv.PossessionMoneyBhv;
import com.example.demo.dbflute.exentity.PokerUserInfo;
import com.example.demo.dbflute.exentity.PossessionMoney;
import com.example.demo.domain.model.LoginSession;
import com.example.demo.exception.NotMatchLoginUserException;
import com.example.demo.repository.MoneyRepository;
import com.example.demo.repository.UserRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class LoginTest {

	@Autowired
	public LoginController loginController;

	@Autowired
	public UserService userService;

	@Autowired
	public PokerUserInfoBhv pokerUserInfoBhv;

	@Autowired
	public UserRepository userRepository;

	@Autowired
	public MoneyRepository moneyRepository;

	@Autowired
	public PossessionMoneyBhv possessionMoneyBhv;

	@Test
	public void success() throws NotMatchLoginUserException {

		String userName = "ログインテストユーザー";
		String password = "test";

		PokerUserInfo entity = successSetUp(userName, password);

		loginController.login(userName, password);

		PokerUserInfo afterLogin = userRepository.getPokerUserByUsername(userName).get();
		PossessionMoney money = moneyRepository.getMoney(entity.getUserId()).get();
		LoginSession session = loginController.loginSession;

		assertThat(session.getUserId().get()).isEqualTo(entity.getUserId());
		assertThat(session.getUserName().get()).isEqualTo(entity.getUserName());
		assertThat(afterLogin.getLoginDate()).isNotNull();
		assertThat(money.getPossessionMoney()).isEqualTo(new BigDecimal(1100));
	}

	@Test
	public void alreadyLogin() throws NotMatchLoginUserException {

		String userName = "既にログインユーザー";
		String password = "test";

		PokerUserInfo entity = alreadyLoginSetUp(userName, password);

		loginController.login(userName, password);

		PossessionMoney money = moneyRepository.getMoney(entity.getUserId()).get();

		assertThat(money.getPossessionMoney()).isEqualTo(new BigDecimal(1000));

	}


	@Test(expected = NotMatchLoginUserException.class)
	public void unMatchingUser() throws NotMatchLoginUserException {
		loginController.login("マッチしないユーザー", "noMatch");
	}

	private PokerUserInfo successSetUp(String userName, String password) {

		if(userRepository.getPokerUserByUsername(userName).isPresent()) {
		 PokerUserInfo pokerUserInfo = new PokerUserInfo();
		 pokerUserInfo.uniqueBy(userName);
		 pokerUserInfoBhv.delete(pokerUserInfo);
		}
		userRepository.insert(userName, password);
		PokerUserInfo entity = userRepository.getPokerUserByUsername(userName).get();
		moneyRepository.save(entity.getUserId(), 1000);
		userRepository.update(entity.getUserId(), entity.getUserName(), entity.getPassword(), LocalDateTime.of(2018, 12, 11, 2, 33));

	return entity;

	}

	private PokerUserInfo alreadyLoginSetUp(String userName, String password) {
		if(userRepository.getPokerUserByUsername(userName).isPresent()) {
		 PokerUserInfo pokerUserInfo = new PokerUserInfo();
		 pokerUserInfo.uniqueBy(userName);
		 pokerUserInfoBhv.delete(pokerUserInfo);
		}
		userRepository.insert(userName, password);
		PokerUserInfo entity = userRepository.getPokerUserByUsername(userName).get();
		moneyRepository.save(entity.getUserId(), 1000);
		userRepository.update(entity.getUserId(), entity.getUserName(), entity.getPassword(), LocalDateTime.now());

		return entity;

	}

}
