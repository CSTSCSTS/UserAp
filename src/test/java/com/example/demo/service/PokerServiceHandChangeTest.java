package com.example.demo.service;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.example.demo.domain.model.Card;
import com.example.demo.domain.model.PokerPlayingInfo;

public class PokerServiceHandChangeTest {

	private PokerService sut;

	@Before
	public void setUp() {
		sut = new PokerService();
	}

	@Test
	public void test() {
		PokerPlayingInfo config = sut.pokerSetUp(true);

		List<Card> playerHands = config.getPlayerHands();
		List<Card> cpuHands = config.getComputerHands();

		playerHands.forEach(h -> h.setIsChange(true));

		List<Card> afterHandChangeplayerHands = sut.handChange(playerHands, config.getDeck());
		List<Card> afterHandChangeCpuHands = sut.handChange(cpuHands, config.getDeck());

		assertThat(playerHands.size()).isEqualTo(5);
		assertThat(cpuHands.size()).isEqualTo(5);
		assertThat(afterHandChangeplayerHands.size()).isEqualTo(5);
		assertThat(afterHandChangeCpuHands.size()).isEqualTo(5);
		assertThat(isEqualToCardList(afterHandChangeplayerHands, playerHands)).isEqualTo(false);
		assertThat(isEqualToCardList(afterHandChangeCpuHands, cpuHands)).isEqualTo(true);
	}

	private boolean isEqualToCardList(List<Card> list1, List<Card> list2) {
		if(list1.size() != list2.size()) {
			return false;
		}

		for(int i=0;i < list1.size();i++) {
			if(!isEqualToCard(list1.get(i), list2.get(i))) {
				return false;
			}
		}
		return true;

	}

	private boolean isEqualToCard(Card card1, Card card2) {
    return card1.getNumber() == card2.getNumber() && card1.getType() == card2.getType();
	}
}
