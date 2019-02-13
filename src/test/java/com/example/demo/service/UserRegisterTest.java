package com.example.demo.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.dbflute.optional.OptionalEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.example.demo.dbflute.exbhv.PokerUserInfoBhv;
import com.example.demo.dbflute.exbhv.PossessionMoneyBhv;
import com.example.demo.dbflute.exentity.PokerUserInfo;
import com.example.demo.dbflute.exentity.PossessionMoney;
import com.example.demo.exception.UserNameDuplicateException;
import com.example.demo.repository.MoneyRepository;
import com.example.demo.repository.UserRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class UserRegisterTest {

	@Autowired
	private UserService sut;

	@Autowired
	public PokerUserInfoBhv pokerUserInfoBhv;

	@Autowired
	public UserRepository userRepository;

	@Autowired
	public MoneyRepository moneyRepository;

	@Autowired
	public PossessionMoneyBhv possessionMoneyBhv;

		@Test
		public void success() throws UserNameDuplicateException {

			// テストユーザーが既に存在するなら、削除
			if(userRepository.getPokerUserByUsername("テストユーザー").isPresent()) {
				 PokerUserInfo pokerUserInfo = new PokerUserInfo();
				 pokerUserInfo.uniqueBy("テストユーザー");
				 pokerUserInfoBhv.delete(pokerUserInfo);
		 }

			sut.resister("テストユーザー", "test");
			PokerUserInfo entity = userRepository.getPokerUserByUsername("テストユーザー").get();
			OptionalEntity<PossessionMoney> moneyEntity = moneyRepository.getMoney(entity.getUserId());
			assertThat(entity.getUserName()).isEqualTo("テストユーザー");
			assertThat(entity.getLoginDate()).isNotNull();
			assertTrue(moneyEntity.isPresent());
			assertThat(moneyEntity.get().getPossessionMoney()).isEqualTo(new BigDecimal(1000));
			assertThat(moneyEntity.get().getUpdateDate()).isNotNull();
		}

		@Test(expected = UserNameDuplicateException.class)
		public void userNameDuplicate() throws UserNameDuplicateException {

   // ユーザー名が「重複ユーザー」のユーザーを生成
			if(!userRepository.getPokerUserByUsername("重複ユーザー").isPresent()) {
			  sut.resister("重複ユーザー", "test");
			}

			sut.resister("重複ユーザー", "test");
		}
}
