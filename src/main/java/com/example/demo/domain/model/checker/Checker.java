package com.example.demo.domain.model.checker;

import java.util.List;
import java.util.Optional;

import com.example.demo.domain.model.Card;
import com.example.demo.domain.model.Role;

public abstract class Checker {

	public Checker next;

	public Checker setNext(Checker checker) {
		this.next = checker;
		return checker;
	}

	public abstract Optional<Role> check(List<Card> hands);

}
