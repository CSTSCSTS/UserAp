package com.example.demo.domain.model.checker;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.example.demo.domain.model.Card;
import com.example.demo.domain.model.Role;
import com.example.demo.domain.model.RoleName;
import com.example.demo.util.PokerUtil;

public class FullHouseChecker extends Checker {

	@Override
	public Optional<Role> check(List<Card> hands) {
		List<Card> handsCopy = new ArrayList<>(hands);
		if(PokerUtil.isFullHouse(handsCopy) || PokerUtil.isFullHouseWithJoker(handsCopy)) {
	      return Optional.of(new Role(RoleName.FULL_HOUSE, 6));
		}
		return next.check(hands);
	}

}
