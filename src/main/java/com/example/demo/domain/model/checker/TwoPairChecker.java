package com.example.demo.domain.model.checker;

import java.util.List;
import java.util.Optional;

import com.example.demo.domain.model.Card;
import com.example.demo.domain.model.Role;
import com.example.demo.domain.model.RoleName;
import com.example.demo.util.PokerUtil;

public class TwoPairChecker extends Checker {

	@Override
	public Optional<Role> check(List<Card> hands) {
	  if(PokerUtil.isTwoPair(hands)) {
	    return Optional.of(new Role(RoleName.TWO_PAIR, 2));
	  }
      return next.check(hands);
	}

}
