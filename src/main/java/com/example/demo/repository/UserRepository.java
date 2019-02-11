package com.example.demo.repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.dbflute.optional.OptionalEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.dbflute.exbhv.PokerUserInfoBhv;
import com.example.demo.dbflute.exbhv.PossessionMoneyBhv;
import com.example.demo.dbflute.exbhv.pmbean.SelectPmb;
import com.example.demo.dbflute.exentity.PokerUserInfo;
import com.example.demo.dbflute.exentity.customize.Select;
import com.example.demo.domain.model.MoneyRanking;
import com.example.demo.domain.model.MoneyRanking.MoneyRankingItem;

@Component
public class UserRepository {

	@Autowired
	public PokerUserInfoBhv pokerUserInfoBhv;

	@Autowired
	public PossessionMoneyBhv possessionMoneyBhv;

	public OptionalEntity<PokerUserInfo> getPokerUserByUsername(String username) {
	  return  pokerUserInfoBhv.selectEntity(cb ->
	    cb.query().setUserName_Equal(username)
	  );
  }

	public OptionalEntity<PokerUserInfo> getPokerUserByUsernameAndPassword(String username, String password) {
  return pokerUserInfoBhv.selectEntity(cb -> {
  	cb.query().setUserName_Equal(username);
  	cb.query().setPassword_Equal(password);
  });
 }

	public MoneyRanking getMoneyRanking() {

		List<Select> outsideSqlResult = pokerUserInfoBhv.outsideSql().selectList(new SelectPmb()).getSelectedList();
		List<MoneyRankingItem> moneyRankingList = new ArrayList<>();
		outsideSqlResult.stream()
				.map(t -> MoneyRankingItem.builder()
						                        .username(t.getUserName())
						                        .money(t.getPossessionMoney())
						                        .build())
				.forEach(i -> moneyRankingList.add(i));

		return new MoneyRanking(moneyRankingList);

	}

	public boolean userNameIsDuplicate(String username) {
		return pokerUserInfoBhv.selectEntity(cb ->
		  cb.query().setUserName_Equal(username)
		).isPresent();
	}

	public void insert(String username, String password) {
		PokerUserInfo pokerUserInfo = new PokerUserInfo();
		pokerUserInfo.setUserName(username);
		pokerUserInfo.setPassword(password);
		pokerUserInfoBhv.insert(pokerUserInfo);

	}


	public void update(int userId, String username, String password, LocalDateTime loginDate) {// 今はユーザー登録しかできないから不要？
		PokerUserInfo pokerUserInfo = new PokerUserInfo();
		pokerUserInfo.setUserId(userId);
		pokerUserInfo.setUserName(username);
		pokerUserInfo.setPassword(password);
		pokerUserInfo.setLoginDate(loginDate);
		pokerUserInfoBhv.update(pokerUserInfo);
	}

}
