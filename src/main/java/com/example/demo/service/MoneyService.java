package com.example.demo.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.constants.PokerConstants;
import com.example.demo.domain.model.Money;
import com.example.demo.domain.model.Winner;
import com.example.demo.repository.MoneyRepository;

@Service
// 所持金情報を扱うサービスクラス
public class MoneyService {

@Autowired
public MoneyRepository moneyRepository;

// 所持金を取得する
public Money getMoney(int userId) {
	 return moneyRepository.getMoney(userId);
}

public Money register(int userId) {
		Money money = new Money(userId, PokerConstants.USER_REGISTER_BOUNS, LocalDateTime.now());
		moneyRepository.save(money);
		return money;
}

// 所持金を更新する
public Money update(int userId, BigDecimal betMoney, Winner winner) {
	 Money money = moneyRepository.getMoney(userId);
		if (winner == Winner.PLAYER) {
			money.plusMoney(betMoney);
		} else if(winner == Winner.CPU) {
			money.minusMoney(betMoney);
		}
		money.setUpdateDate(LocalDateTime.now());
		moneyRepository.save(money);

		return money;

}

//所持金を更新する
public Money update(Money money) {
  moneyRepository.save(money);
  return money;
}


}
