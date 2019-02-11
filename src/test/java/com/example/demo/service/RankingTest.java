package com.example.demo.service;

import static org.assertj.core.api.Assertions.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.example.demo.dbflute.exbhv.PokerUserInfoBhv;
import com.example.demo.dbflute.exbhv.PossessionMoneyBhv;
import com.example.demo.dbflute.exentity.PokerUserInfo;
import com.example.demo.dbflute.exentity.PossessionMoney;
import com.example.demo.domain.model.MoneyRanking.MoneyRankingItem;
import com.example.demo.domain.model.Ranking;
import com.example.demo.repository.UserRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class RankingTest {

	@Autowired
	private RankingService sut;

	@Autowired
	public PokerUserInfoBhv pokerUserInfoBhv;

	@Autowired
	public UserRepository userRepository;

	@Autowired
	public PossessionMoneyBhv possessionMoneyBhv;

	@Test
	public void success() {
		testSetup();

		Ranking ranking = sut.getRankingInfo();
		List<MoneyRankingItem> list = ranking.getMoneyRanking().getMoneyRankingList();

		assertThat(list.size()).isEqualByComparingTo(5);
		assertThat(list.get(0).username).isEqualTo("aaa");
		assertThat(list.get(1).username).isEqualTo("ccc");
		assertThat(list.get(2).username).isEqualTo("ddd");
		assertThat(list.get(3).username).isEqualTo("eee");
		assertThat(list.get(4).username).isEqualTo("bbb");

	}

	private void testSetup() {

//全件削除
	pokerUserInfoBhv.queryDelete(cb ->
	  cb.query().setUserId_GreaterThan(0)
	);

		List<PokerUserInfo> pokerUserInfoList = new ArrayList<>();

		pokerUserInfoList.add(createUserEntity("aaa", "aaa"));
		pokerUserInfoList.add(createUserEntity("bbb", "bbb"));
		pokerUserInfoList.add(createUserEntity("ccc", "ccc"));
		pokerUserInfoList.add(createUserEntity("ddd", "ddd"));
		pokerUserInfoList.add(createUserEntity("eee", "eee"));

		pokerUserInfoBhv.batchInsert(pokerUserInfoList);

		List<PokerUserInfo> resultList = pokerUserInfoBhv.selectList(cb ->
		  cb.query().setUserId_IsNotNull()
		).getSelectedList();

		List<PossessionMoney> moneyList = new ArrayList<>();

		PokerUserInfo entity_A = getUserEntityByUserName(resultList, "aaa");
		PokerUserInfo entity_B = getUserEntityByUserName(resultList, "bbb");
		PokerUserInfo entity_C= getUserEntityByUserName(resultList, "ccc");
		PokerUserInfo entity_D = getUserEntityByUserName(resultList, "ddd");
		PokerUserInfo entity_E = getUserEntityByUserName(resultList, "eee");

		moneyList.add(createMoneyEntity(entity_A.getUserId(), new BigDecimal(10000), null));
		moneyList.add(createMoneyEntity(entity_B.getUserId(), new BigDecimal(1000), LocalDateTime.of(2018, 1, 15, 14, 22)));
		moneyList.add(createMoneyEntity(entity_C.getUserId(), new BigDecimal(5000), null));
		moneyList.add(createMoneyEntity(entity_D.getUserId(), new BigDecimal(3000), null));
		moneyList.add(createMoneyEntity(entity_E.getUserId(), new BigDecimal(1000), LocalDateTime.of(2019, 1, 15, 14, 22)));

		possessionMoneyBhv.batchInsert(moneyList);

	}

	private PokerUserInfo createUserEntity(String username, String password) {
		PokerUserInfo pokerUserInfo = new PokerUserInfo();
		pokerUserInfo.setUserName(username);
		pokerUserInfo.setPassword(password);
		return pokerUserInfo;
	}

	private PossessionMoney createMoneyEntity(int userId, BigDecimal money, LocalDateTime updateDate) {
		PossessionMoney possessionMoney = new PossessionMoney();
		possessionMoney.setUserId(userId);
		possessionMoney.setPossessionMoney(money);
		possessionMoney.setUpdateDate(updateDate);
		return possessionMoney;

	}

	private PokerUserInfo getUserEntityByUserName(List<PokerUserInfo> list, String userName) {
		return list.stream()
		.filter(i -> i.getUserName().equals(userName))
		.findFirst().get();
	}


}
