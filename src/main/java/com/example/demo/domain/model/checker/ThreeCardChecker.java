package com.example.demo.domain.model.checker;

import java.util.List;
import java.util.Optional;

import com.example.demo.domain.model.Card;
import com.example.demo.domain.model.Role;
import com.example.demo.domain.model.RoleName;
import com.example.demo.util.PokerUtil;

public class ThreeCardChecker extends Checker {

	@Override
	public Optional<Role> check(List<Card> hands) {
	  if(PokerUtil.isThreeCard(hands) || PokerUtil.isThreeCardWithJoker(hands)) {
	    return Optional.of(new Role(RoleName.THREE_CARD, 3));
      }
	  return next.check(hands);
	}

}
