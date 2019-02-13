package com.example.demo.service;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dbflute.exentity.PossessionMoney;
import com.example.demo.domain.model.Money;
import com.example.demo.domain.model.PokerPlayingInfo.Winner;
import com.example.demo.exception.NotFoundMoneyException;
import com.example.demo.repository.MoneyRepository;

@Service
public class MoneyService {

@Autowired
public MoneyRepository moneyRepository;

public Money getMoney(int userId) throws NotFoundMoneyException {
	 PossessionMoney entity = moneyRepository.getMoney(userId)
	 		.orElseThrow(() -> new NotFoundMoneyException("所持金情報を取得できません"));
	 return new Money(entity.getUserId(), entity.getPossessionMoney());
}

public Money update(int userId, BigDecimal betMoney, Winner winner) {
		PossessionMoney entity = moneyRepository.getMoney(userId).get();
		Money money = new Money(entity.getUserId(), entity.getPossessionMoney());
		if (winner == Winner.PLAYER) {
			money.plusMoney(betMoney);
		} else if(winner == Winner.CPU) {
			money.minusMoney(betMoney);
		}

		moneyRepository.save(money.getUserId(), money.getMoney().intValue());

		return money;

	}

}