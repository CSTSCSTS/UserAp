package com.example.demo.service;

import static org.assertj.core.api.Assertions.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.example.demo.dbflute.exbhv.PokerUserInfoBhv;
import com.example.demo.dbflute.exbhv.PossessionMoneyBhv;
import com.example.demo.dbflute.exentity.PokerUserInfo;
import com.example.demo.domain.model.Money;
import com.example.demo.domain.model.PokerPlayingInfo;
import com.example.demo.domain.model.PokerPlayingInfo.Winner;
import com.example.demo.domain.model.User;
import com.example.demo.exception.IllegalBetException;
import com.example.demo.exception.NotFoundMoneyException;
import com.example.demo.exception.UserNameDuplicateException;
import com.example.demo.repository.MoneyRepository;
import com.example.demo.repository.UserRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class BetTest {

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
	public void success() throws UserNameDuplicateException, IllegalBetException, NotFoundMoneyException {
		 PokerUserInfo entity = userSetUp(LocalDateTime.now());
   PokerPlayingInfo info = pokerService.pokerPrepare(entity.getUserId(), new BigDecimal(100), true);
   assertThat(info.getDeck().size()).isEqualTo(43);
   assertThat(info.getPlayerHands().size()).isEqualTo(5);
   assertThat(info.getComputerHands().size()).isEqualTo(5);

	}

	@Test(expected = IllegalBetException.class)
	public void moneyExceeded() throws UserNameDuplicateException, IllegalBetException, NotFoundMoneyException {
		 PokerUserInfo entity = userSetUp(LocalDateTime.now());
		 pokerService.pokerPrepare(entity.getUserId(), new BigDecimal(10000), true);
	}

	@Test(expected = NotFoundMoneyException.class)
	public void moneyNotFound() throws NotFoundMoneyException {
   moneyService.getMoney(0);
	}

	@Test
	public void playerWinner() throws UserNameDuplicateException, NotFoundMoneyException {
		 LocalDateTime firstTime = LocalDateTime.of(2018, 12, 11, 2, 33);
		 PokerUserInfo entity = userSetUp(firstTime);
	  moneyService.update(entity.getUserId(), new BigDecimal(100), Winner.PLAYER);
	  Money money = moneyRepository.getMoney(entity.getUserId());
	  assertThat(money.getMoney()).isEqualTo(new BigDecimal(1100));
	  assertThat(money.getUpdateDate()).isNotEqualTo(firstTime);
	}

	@Test
	public void CPUWinner() throws UserNameDuplicateException, NotFoundMoneyException {
		 LocalDateTime firstTime = LocalDateTime.of(2018, 12, 11, 2, 33);
		 PokerUserInfo entity = userSetUp(firstTime);
		 moneyService.update(entity.getUserId(), new BigDecimal(100), Winner.CPU);
		 Money money = moneyRepository.getMoney(entity.getUserId());
		 assertThat(money.getMoney()).isEqualTo(new BigDecimal(900));
		 assertThat(money.getUpdateDate()).isNotEqualTo(firstTime);
	}

	@Test
	public void draw() throws UserNameDuplicateException, NotFoundMoneyException {
		 LocalDateTime firstTime = LocalDateTime.of(2018, 12, 11, 2, 33);
		 PokerUserInfo entity = userSetUp(firstTime);
	  moneyService.update(entity.getUserId(), new BigDecimal(100), Winner.NOTHING);
	  Money money = moneyRepository.getMoney(entity.getUserId());
	  assertThat(money.getMoney()).isEqualTo(new BigDecimal(1000));
	  assertThat(money.getUpdateDate()).isNotEqualTo(firstTime);
	}


	private PokerUserInfo userSetUp(LocalDateTime time) throws UserNameDuplicateException {
		 if(userRepository.getPokerUserByUsername("テストユーザー").isPresent()) {
		   PokerUserInfo pokerUserInfo = new PokerUserInfo();
		   pokerUserInfo.uniqueBy("テストユーザー");
		   pokerUserInfoBhv.delete(pokerUserInfo);
   }
		 userRepository.insert(new User("テストユーザー", "test"));
		 PokerUserInfo entity = userRepository.getPokerUserByUsername("テストユーザー").get();
		 moneyRepository.save(new Money(entity.getUserId(), new BigDecimal(1000), time));
		 return entity;
	}

}
