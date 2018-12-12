package com.example.demo.domain.model.checker;

import java.util.List;
import java.util.Optional;

import com.example.demo.domain.model.Card;
import com.example.demo.domain.model.Role;
import com.example.demo.domain.model.RoleName;
import com.example.demo.util.PokerUtil;

public class StraightFlushChecker extends Checker {

	@Override
	public Optional<Role> check(List<Card> hands) {
	  if(PokerUtil.isStraightFlush(hands) || PokerUtil.isStraightFlushWithJoker(hands)) {
	    return Optional.of(new Role(RoleName.STRAIGHT_FLUSH, 8));
      }
	  return next.check(hands);
	}

}
