package com.example.demo.repository;

import org.dbflute.optional.OptionalEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.dbflute.exbhv.PossessionMoneyBhv;
import com.example.demo.dbflute.exentity.PossessionMoney;
import com.example.demo.domain.model.Money;
import com.example.demo.exception.NotFoundMoneyException;

@Component
public class MoneyRepository {

	@Autowired
	public UserRepository userRepository;

	@Autowired
	public PossessionMoneyBhv possessionMoneyBhv;

	 // ユーザーIDを元にユーザーを取得する
  public Money getMoney(int userId) throws NotFoundMoneyException{

		  PossessionMoney entity = possessionMoneyBhv.selectEntity(cb ->
		    cb.query().setUserId_Equal(userId)
		  ).orElseThrow(() -> new NotFoundMoneyException("所持金情報を取得できません"));

		  return new Money(entity.getUserId(), entity.getPossessionMoney(), entity.getUpdateDate());
  }

  // 保存済みなら更新、そうでなければ登録する
	 public void save(Money money) {
		  OptionalEntity<PossessionMoney> optEntity = possessionMoneyBhv.selectEntity(cb ->
      cb.query().setUserId_Equal(money.getUserId())
    );
		  if(optEntity.isPresent()) {
			   update(money);
			   return;
		  }
		  insert(money);
  }

	 // 所持金情報を登録
  private void insert(Money money) {
		  PossessionMoney possessionMoney = new PossessionMoney();
		  possessionMoney.setUserId(money.getUserId());
		  possessionMoney.setPossessionMoney(money.getMoney());
		  possessionMoneyBhv.insert(possessionMoney);
	 }


  // 所持金情報を更新
	 private void update(Money money) {
		  PossessionMoney possessionMoney = new PossessionMoney();
		  possessionMoney.setUserId(money.getUserId());
		  possessionMoney.setPossessionMoney(money.getMoney());
		  possessionMoney.setUpdateDate(money.getUpdateDate());
		  possessionMoneyBhv.update(possessionMoney);
  }

}
