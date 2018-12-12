package com.example.demo.service;

import static org.assertj.core.api.Assertions.*;

import org.junit.Before;
import org.junit.Test;

import com.example.demo.domain.model.PokerPlayingInfo;

public class PokerServiceInitialStateTest {

	private PokerService sut;

	@Before
	public void setUp() {
		sut = new PokerService();
	}

	@Test
	public void test() {
		PokerPlayingInfo config = sut.pokerPrepare(true);

		assertThat(config.getDeck().size()).isEqualTo(43);
		assertThat(config.getPlayerHands().size()).isEqualTo(5);
		assertThat(config.getComputerHands().size()).isEqualTo(5);
	}

}
