package com.example.demo.service;

import static org.assertj.core.api.Assertions.*;

import org.junit.Before;
import org.junit.Test;

import com.example.demo.domain.model.PokerPlayingInfo.Winner;
import com.example.demo.domain.model.Role;

public class PokerServiceWinOrLoseJudgeTest {

	private PokerService sut;

	@Before
	public void setUp() {
		sut = new PokerService();
	}

	// 役に応じて勝敗を判定できること

	@Test
	public void isWinnerPlayer() {
		Winner winner = sut.WinOrLossJudge(Role.getFullHouse(), Role.getFlush());
		assertThat(winner).isEqualTo(Winner.PLAYER);
	}

	@Test
	public void isWinnerCpu() {
		Winner winner = sut.WinOrLossJudge(Role.getFullHouse(), Role.getStraightFlush());
		assertThat(winner).isEqualTo(Winner.CPU);
	}

	@Test
	public void isWinnerNothing() {
		Winner winner = sut.WinOrLossJudge(Role.getHighCard(), Role.getHighCard());
		assertThat(winner).isEqualTo(Winner.NOTHING);
	}

}
