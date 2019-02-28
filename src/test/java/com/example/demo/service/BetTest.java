package com.example.demo.service;

import static org.assertj.core.api.Assertions.*;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.example.demo.constants.PokerConstants;
import com.example.demo.controller.PokerController;
import com.example.demo.dbflute.exbhv.PokerUserInfoBhv;
import com.example.demo.dbflute.exbhv.PossessionMoneyBhv;
import com.example.demo.dbflute.exentity.PokerUserInfo;
import com.example.demo.domain.model.Money;
import com.example.demo.domain.model.PokerPlayingInfo;
import com.example.demo.domain.model.PokerPlayingInfo.Winner;
import com.example.demo.domain.model.User;
import com.example.demo.exception.IllegalBetException;
import com.example.demo.exception.LoginSessionTimeOutException;
import com.example.demo.exception.UserNameDuplicateException;
import com.example.demo.repository.MoneyRepository;
import com.example.demo.repository.UserRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class BetTest {

	@Autowired
	public PokerController pokerController;

	@Autowired
	public PokerService pokerService;

	@Autowired
	public MoneyService moneyService;

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
	public void success() throws UserNameDuplicateException, IllegalBetException, LoginSessionTimeOutException {
			PokerUserInfo entity = userSetUp(LocalDateTime.now());
			pokerController.loginSession.setUserId(entity.getUserId());
			pokerController.loginSession.setUserName(entity.getUserName());
			PokerPlayingInfo info = pokerController.postPokerStart(new BigDecimal(100), true);
   assertThat(info.getDeck().size()).isEqualTo(43);
   assertThat(info.getPlayerHands().size()).isEqualTo(5);
   assertThat(info.getComputerHands().size()).isEqualTo(5);
	}

	@Test(expected = IllegalBetException.class)
	public void moneyExceeded() throws UserNameDuplicateException, IllegalBetException {
		 PokerUserInfo entity = userSetUp(LocalDateTime.now());
		 pokerService.pokerPrepare(entity.getUserId(), new BigDecimal(10000), true);
	}

	@Test
	public void playerWinner() throws UserNameDuplicateException, LoginSessionTimeOutException {
		 LocalDateTime firstTime = LocalDateTime.of(2018, 12, 11, 2, 33);
		 PokerUserInfo entity = userSetUp(firstTime);
		 pokerController.loginSession.setUserId(entity.getUserId());
			pokerController.loginSession.setUserName(entity.getUserName());
			pokerController.result(new BigDecimal(100), Winner.PLAYER);
	  Money money = moneyRepository.getMoney(entity.getUserId());
	  assertThat(money.getMoney()).isEqualTo(new BigDecimal(1100));
	  assertThat(money.getUpdateDate()).isNotEqualTo(firstTime);
	}

	@Test
	public void CPUWinner() throws UserNameDuplicateException, LoginSessionTimeOutException {
		 LocalDateTime firstTime = LocalDateTime.of(2018, 12, 11, 2, 33);
		 PokerUserInfo entity = userSetUp(firstTime);
		 pokerController.loginSession.setUserId(entity.getUserId());
			pokerController.loginSession.setUserName(entity.getUserName());
			pokerController.result(new BigDecimal(100), Winner.CPU);
		 Money money = moneyRepository.getMoney(entity.getUserId());
		 assertThat(money.getMoney()).isEqualTo(new BigDecimal(900));
		 assertThat(money.getUpdateDate()).isNotEqualTo(firstTime);
	}

	@Test
	public void draw() throws UserNameDuplicateException, LoginSessionTimeOutException {
		 LocalDateTime firstTime = LocalDateTime.of(2018, 12, 11, 2, 33);
		 PokerUserInfo entity = userSetUp(firstTime);
		 pokerController.loginSession.setUserId(entity.getUserId());
			pokerController.loginSession.setUserName(entity.getUserName());
			pokerController.result(new BigDecimal(100), Winner.NOTHING);
	  Money money = moneyRepository.getMoney(entity.getUserId());
	  assertThat(money.getMoney()).isEqualTo(new BigDecimal(1000));
	  assertThat(money.getUpdateDate()).isNotEqualTo(firstTime);
	}

	@Test(expected = LoginSessionTimeOutException.class)
	public void getMoneySessionTimeOut() throws LoginSessionTimeOutException {
			pokerController.getMoney();
	}

	@Test(expected = LoginSessionTimeOutException.class)
	public void betSessionTimeOut() throws UserNameDuplicateException, LoginSessionTimeOutException, IllegalBetException {
			pokerController.postPokerStart(new BigDecimal(100), true);
	}

	@Test(expected = LoginSessionTimeOutException.class)
	public void handChangeSessionTimeOut() throws IOException, LoginSessionTimeOutException {
			// 本来は、山札やプレイヤーの手札をJSONにした値が引数にわたってくるが、
			// セッションタイムアウトになることを確かめたいだけなので、""を入れておく
			pokerController.handChange("", "", "");
	}

	@Test(expected = LoginSessionTimeOutException.class)
	public void moneyUpdateSessionTimeOut() throws LoginSessionTimeOutException {
	  pokerController.result(new BigDecimal(100), Winner.PLAYER);
	}

	private PokerUserInfo userSetUp(LocalDateTime time) throws UserNameDuplicateException {
		 if(userRepository.getPokerUserByUsername("テストユーザー").isPresent()) {
		   PokerUserInfo pokerUserInfo = new PokerUserInfo();
		   pokerUserInfo.uniqueBy("テストユーザー");
		   pokerUserInfoBhv.delete(pokerUserInfo);
   }
		 userRepository.insert(new User("テストユーザー", "test"));
		 PokerUserInfo entity = userRepository.getPokerUserByUsername("テストユーザー").get();
		 moneyRepository.save(new Money(entity.getUserId(), PokerConstants.USER_REGISTER_BOUNS, time));
		 return entity;
	}

}
