package com.example.demo.domain.model.checker;

import java.util.List;
import java.util.Optional;

import com.example.demo.domain.model.Card;
import com.example.demo.domain.model.Role;
import com.example.demo.domain.model.RoleName;
import com.example.demo.util.PokerUtil;

public class StraightChecker extends Checker {

	@Override
	public Optional<Role> check(List<Card> hands) {
		if(PokerUtil.isStraight(hands) || PokerUtil.isStraightWithJoker(hands)) {
		  return Optional.of(new Role(RoleName.STRAIGHT, 4));
		}
		return next.check(hands);
	}

}
