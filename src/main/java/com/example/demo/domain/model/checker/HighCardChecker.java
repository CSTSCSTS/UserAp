package com.example.demo.domain.model.checker;

import java.util.List;
import java.util.Optional;

import com.example.demo.domain.model.Card;
import com.example.demo.domain.model.Role;
import com.example.demo.domain.model.RoleName;
import com.example.demo.util.PokerUtil;

public class HighCardChecker extends Checker {

	@Override
	public Optional<Role> check(List<Card> hands) {
	  if(PokerUtil.isHighCard(hands)) {
	    return Optional.of(new Role(RoleName.HIGH_CARD, 0));
    }
	  return Optional.empty();
	}

}
