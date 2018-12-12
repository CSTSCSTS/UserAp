package com.example.demo.domain.model.checker;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.example.demo.domain.model.Card;
import com.example.demo.domain.model.Role;
import com.example.demo.domain.model.RoleName;
import com.example.demo.util.PokerUtil;

public class FourCardChecker extends Checker {

	@Override
	public Optional<Role> check(List<Card> hands) {
		List<Card> handsCopy = new ArrayList<>(hands);

		boolean a = PokerUtil.isFourCard(handsCopy) ;
		boolean b = PokerUtil.isFourCardWithJoker(handsCopy);
		if(PokerUtil.isFourCard(handsCopy) || PokerUtil.isFourCardWithJoker(handsCopy)) {
	      return Optional.of(new Role(RoleName.FOUR_CARD, 7));
		}
		return next.check(hands);
	}


}


// CPUの手札交換ロジック
//ジョーカーはキープ
//同じ数字のものはキープ
//ストレート・フラッシュ狙えそう→今回はなしで
//ジョーカーない
//・ペアもない→50％全部交換・５０％１枚残して交換
