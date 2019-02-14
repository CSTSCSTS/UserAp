package com.example.demo.repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.dbflute.optional.OptionalEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.dbflute.exbhv.PossessionMoneyBhv;
import com.example.demo.dbflute.exentity.PossessionMoney;

@Component
public class MoneyRepository {

	@Autowired
	public UserRepository userRepository;

	@Autowired
	public PossessionMoneyBhv possessionMoneyBhv;

	 // ユーザーIDを元にユーザーを取得する
  public OptionalEntity<PossessionMoney> getMoney(int userId){
		  return possessionMoneyBhv.selectEntity(cb ->
		    cb.query().setUserId_Equal(userId)
		  );
  }

  // 保存済みなら更新、そうでなければ登録する
	 public void save(int userId, int money, LocalDateTime loginDate) {
		  OptionalEntity<PossessionMoney> optEntity = getMoney(userId);
		  if(optEntity.isPresent()) {
			   update(userId, money, loginDate);
			   return;
		  }
		  insert(userId, money);
  }

	 // 所持金情報を登録
  private void insert(int userId, int money) {
		  PossessionMoney possessionMoney = new PossessionMoney();
		  possessionMoney.setUserId(userId);
		  possessionMoney.setPossessionMoney(new BigDecimal(money));
		  possessionMoneyBhv.insert(possessionMoney);
	 }


  // 所持金情報を更新
	 private void update(int userId, int money, LocalDateTime loginDate) {
		  PossessionMoney possessionMoney = new PossessionMoney();
		  possessionMoney.setUserId(userId);
		  possessionMoney.setPossessionMoney(new BigDecimal(money));
		  possessionMoney.setUpdateDate(loginDate);
		  possessionMoneyBhv.update(possessionMoney);
  }

}
