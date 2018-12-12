package com.example.demo.service;

import static org.assertj.core.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Before;
import org.junit.Test;

import com.example.demo.domain.model.Card;
import com.example.demo.domain.model.CardType;
import com.example.demo.domain.model.checker.Checker;
import com.example.demo.util.PokerUtil;

public class PokerServiceCpuChangeLogicTest {

	private PokerService sut;
	private final Checker checker = PokerUtil.makeChecker();

	@Before
	public void setUp() {
		sut = new PokerService();
	}

	private static Card makeCard(Integer number, CardType type, Boolean isChange) {
		return Card.builder().number(number).type(type).isChange(isChange).build();
	}

	// 役に応じた動きをすること(手札交換後、少なくとも、交換前の役は維持していること)

  //5カード→手札交換しない
	@Test
	public void isFiveCard() {
		List<Card> hands = new ArrayList<>();
		hands.add(makeCard(1, CardType.SPADE, false));
		hands.add(makeCard(1, CardType.CLUB, false));
		hands.add(makeCard(1, CardType.HEART, false));
		hands.add(makeCard(1, CardType.DIAMOND, false));
		hands.add(makeCard(0, CardType.JOKER, false));

		List<Card> keepHands = sut.exchangeTargetDecision(sut.roleCheck(hands, checker), hands)
				.stream().filter(h -> h.getIsChange() == false)
				.collect(Collectors.toList());

		assertThat(keepHands.size()).isEqualTo(5);
	}

	// ロイヤルストレートフラッシュ→手札交換しない
	@Test
	public void isRoyalStraightFlush() {
		List<Card> hands = new ArrayList<>();
		hands.add(makeCard(1, CardType.CLUB, false));
		hands.add(makeCard(10, CardType.CLUB, false));
		hands.add(makeCard(11, CardType.CLUB, false));
		hands.add(makeCard(12, CardType.CLUB, false));
		hands.add(makeCard(13, CardType.CLUB, false));


		List<Card> keepHands = sut.exchangeTargetDecision(sut.roleCheck(hands, checker), hands)
				.stream().filter(h -> h.getIsChange() == false)
				.collect(Collectors.toList());

		assertThat(keepHands.size()).isEqualTo(5);
	}

	@Test
	public void isRoyalStraightFlushWithJoker() {
		List<Card> hands = new ArrayList<>();
		hands.add(makeCard(1, CardType.DIAMOND, false));
		hands.add(makeCard(10, CardType.DIAMOND, false));
		hands.add(makeCard(11, CardType.DIAMOND, false));
		hands.add(makeCard(12, CardType.DIAMOND, false));
		hands.add(makeCard(0, CardType.JOKER, false));

		List<Card> keepHands = sut.exchangeTargetDecision(sut.roleCheck(hands, checker), hands)
				.stream().filter(h -> h.getIsChange() == false)
				.collect(Collectors.toList());

		assertThat(keepHands.size()).isEqualTo(5);
	}

	// ストレートフラッシュ→手札交換しない

	@Test
	public void isStraightFlush() {
		List<Card> hands = new ArrayList<>();
		hands.add(makeCard(1, CardType.SPADE, false));
		hands.add(makeCard(2, CardType.SPADE, false));
		hands.add(makeCard(3, CardType.SPADE, false));
		hands.add(makeCard(4, CardType.SPADE, false));
		hands.add(makeCard(5, CardType.SPADE, false));

		List<Card> keepHands = sut.exchangeTargetDecision(sut.roleCheck(hands, checker), hands)
				.stream().filter(h -> h.getIsChange() == false)
				.collect(Collectors.toList());

		assertThat(keepHands.size()).isEqualTo(5);
	}
	@Test
	public void isStraightFlushWithJoker() {
		List<Card> hands = new ArrayList<>();
		hands.add(makeCard(5, CardType.SPADE, false));
		hands.add(makeCard(7, CardType.SPADE, false));
		hands.add(makeCard(8, CardType.SPADE, false));
		hands.add(makeCard(9, CardType.SPADE, false));
		hands.add(makeCard(0, CardType.JOKER, false));

		List<Card> keepHands = sut.exchangeTargetDecision(sut.roleCheck(hands, checker), hands)
				.stream().filter(h -> h.getIsChange() == false)
				.collect(Collectors.toList());

		assertThat(keepHands.size()).isEqualTo(5);
	}

	// 4カード→手札交換する
	@Test
	public void isFourCard() {
		List<Card> hands = new ArrayList<>();
		hands.add(makeCard(1, CardType.SPADE, false));
		hands.add(makeCard(1, CardType.CLUB, false));
		hands.add(makeCard(1, CardType.HEART, false));
		hands.add(makeCard(1, CardType.DIAMOND, false));
		hands.add(makeCard(5, CardType.SPADE, false));

		List<Card> keepHands = sut.exchangeTargetDecision(sut.roleCheck(hands, checker), hands)
				.stream().filter(h -> h.getIsChange() == false)
				.collect(Collectors.toList());

		assertThat(keepHands.size()).isEqualTo(4);
	}

	@Test
	public void isFourCardWithJoker() {
		List<Card> hands = new ArrayList<>();
		hands.add(makeCard(1, CardType.SPADE, false));
		hands.add(makeCard(1, CardType.CLUB, false));
		hands.add(makeCard(1, CardType.HEART, false));
		hands.add(makeCard(0, CardType.JOKER, false));
		hands.add(makeCard(5, CardType.SPADE, false));

		List<Card> keepHands = sut.exchangeTargetDecision(sut.roleCheck(hands, checker), hands)
				.stream().filter(h -> h.getIsChange() == false)
				.collect(Collectors.toList());

		assertThat(keepHands.size()).isEqualTo(4);
	}

	//フルハウス→手札交換しない
	@Test
	public void isFullHouse() {
		List<Card> hands = new ArrayList<>();
		hands.add(makeCard(1, CardType.SPADE, false));
		hands.add(makeCard(1, CardType.CLUB, false));
		hands.add(makeCard(1, CardType.HEART, false));
		hands.add(makeCard(5, CardType.DIAMOND, false));
		hands.add(makeCard(5, CardType.SPADE, false));

		List<Card> keepHands = sut.exchangeTargetDecision(sut.roleCheck(hands, checker), hands)
				.stream().filter(h -> h.getIsChange() == false)
				.collect(Collectors.toList());

		assertThat(keepHands.size()).isEqualTo(5);
	}

	@Test
	public void isFullHouseWithJoker() {
		List<Card> hands = new ArrayList<>();
		hands.add(makeCard(1, CardType.SPADE, false));
		hands.add(makeCard(1, CardType.CLUB, false));
		hands.add(makeCard(5, CardType.HEART, false));
		hands.add(makeCard(0, CardType.JOKER, false));
		hands.add(makeCard(5, CardType.SPADE, false));

		List<Card> keepHands = sut.exchangeTargetDecision(sut.roleCheck(hands, checker), hands)
				.stream().filter(h -> h.getIsChange() == false)
				.collect(Collectors.toList());

		assertThat(keepHands.size()).isEqualTo(5);
	}

	// フラッシュ→手札交換しない
	@Test
	public void isFlush() {
		List<Card> hands = new ArrayList<>();
		hands.add(makeCard(1, CardType.SPADE, false));
		hands.add(makeCard(2, CardType.SPADE, false));
		hands.add(makeCard(8, CardType.SPADE, false));
		hands.add(makeCard(7, CardType.SPADE, false));
		hands.add(makeCard(11, CardType.SPADE, false));

		List<Card> keepHands = sut.exchangeTargetDecision(sut.roleCheck(hands, checker), hands)
				.stream().filter(h -> h.getIsChange() == false)
				.collect(Collectors.toList());

		assertThat(keepHands.size()).isEqualTo(5);
	}
	@Test
	public void isFlushWithJoker() {
		List<Card> hands = new ArrayList<>();
		hands.add(makeCard(5, CardType.SPADE, false));
		hands.add(makeCard(7, CardType.SPADE, false));
		hands.add(makeCard(11, CardType.SPADE, false));
		hands.add(makeCard(3, CardType.SPADE, false));
		hands.add(makeCard(0, CardType.JOKER, false));

		List<Card> keepHands = sut.exchangeTargetDecision(sut.roleCheck(hands, checker), hands)
				.stream().filter(h -> h.getIsChange() == false)
				.collect(Collectors.toList());

		assertThat(keepHands.size()).isEqualTo(5);
	}

	// ストレート→手札交換しない
	@Test
	public void isStraight() {
		List<Card> hands = new ArrayList<>();
		hands.add(makeCard(1, CardType.SPADE, false));
		hands.add(makeCard(2, CardType.HEART, false));
		hands.add(makeCard(3, CardType.SPADE, false));
		hands.add(makeCard(4, CardType.DIAMOND, false));
		hands.add(makeCard(5, CardType.SPADE, false));

		List<Card> keepHands = sut.exchangeTargetDecision(sut.roleCheck(hands, checker), hands)
				.stream().filter(h -> h.getIsChange() == false)
				.collect(Collectors.toList());

		assertThat(keepHands.size()).isEqualTo(5);
	}
	@Test
	public void isStraightWithJoker() {
		List<Card> hands = new ArrayList<>();
		hands.add(makeCard(1, CardType.CLUB, false));
		hands.add(makeCard(10, CardType.CLUB, false));
		hands.add(makeCard(12, CardType.SPADE, false));
		hands.add(makeCard(11, CardType.DIAMOND, false));
		hands.add(makeCard(0, CardType.JOKER, false));

		List<Card> keepHands = sut.exchangeTargetDecision(sut.roleCheck(hands, checker), hands)
				.stream().filter(h -> h.getIsChange() == false)
				.collect(Collectors.toList());

		assertThat(keepHands.size()).isEqualTo(5);
	}

	// 3カード→手札交換する

	@Test
	public void isThreeCard() {
		List<Card> hands = new ArrayList<>();
		hands.add(makeCard(1, CardType.SPADE, false));
		hands.add(makeCard(1, CardType.CLUB, false));
		hands.add(makeCard(1, CardType.HEART, false));
		hands.add(makeCard(5, CardType.DIAMOND, false));
		hands.add(makeCard(10, CardType.SPADE, false));

		List<Card> keepHands = sut.exchangeTargetDecision(sut.roleCheck(hands, checker), hands)
				.stream().filter(h -> h.getIsChange() == false)
				.collect(Collectors.toList());

		assertThat(keepHands.size()).isGreaterThan(2);
		assertThat(keepHands.size()).isLessThan(5);
	}

	@Test
	public void isThreeCardWithJoker() {
		List<Card> hands = new ArrayList<>();
		hands.add(makeCard(1, CardType.SPADE, false));
		hands.add(makeCard(7, CardType.CLUB, false));
		hands.add(makeCard(5, CardType.HEART, false));
		hands.add(makeCard(0, CardType.JOKER, false));
		hands.add(makeCard(5, CardType.SPADE, false));

		List<Card> keepHands = sut.exchangeTargetDecision(sut.roleCheck(hands, checker), hands)
				.stream().filter(h -> h.getIsChange() == false)
				.collect(Collectors.toList());

		assertThat(keepHands.size()).isGreaterThan(2);
		assertThat(keepHands.size()).isLessThan(5);
	}

	// 2ペア→手札交換する
	@Test
	public void isTwoPair() {
		List<Card> hands = new ArrayList<>();
		hands.add(makeCard(1, CardType.SPADE, false));
		hands.add(makeCard(1, CardType.CLUB, false));
		hands.add(makeCard(5, CardType.HEART, false));
		hands.add(makeCard(5, CardType.DIAMOND, false));
		hands.add(makeCard(10, CardType.SPADE, false));

		List<Card> keepHands = sut.exchangeTargetDecision(sut.roleCheck(hands, checker), hands)
				.stream().filter(h -> h.getIsChange() == false)
				.collect(Collectors.toList());

		assertThat(keepHands.size()).isEqualTo(4);
	}

	// 1ペア→手札交換する
	@Test
	public void isOnePair() {
		List<Card> hands = new ArrayList<>();
		hands.add(makeCard(1, CardType.SPADE, false));
		hands.add(makeCard(10, CardType.CLUB, false));
		hands.add(makeCard(9, CardType.HEART, false));
		hands.add(makeCard(5, CardType.DIAMOND, false));
		hands.add(makeCard(10, CardType.SPADE, false));

		List<Card> keepHands = sut.exchangeTargetDecision(sut.roleCheck(hands, checker), hands)
				.stream().filter(h -> h.getIsChange() == false)
				.collect(Collectors.toList());

		assertThat(keepHands.size()).isGreaterThan(1);
		assertThat(keepHands.size()).isLessThan(4);
	}

	@Test
	public void isOnePairWithJoker() {
		List<Card> hands = new ArrayList<>();
		hands.add(makeCard(1, CardType.SPADE, false));
		hands.add(makeCard(7, CardType.CLUB, false));
		hands.add(makeCard(6, CardType.HEART, false));
		hands.add(makeCard(0, CardType.JOKER, false));
		hands.add(makeCard(5, CardType.SPADE, false));

		List<Card> keepHands = sut.exchangeTargetDecision(sut.roleCheck(hands, checker), hands)
				.stream().filter(h -> h.getIsChange() == false)
				.collect(Collectors.toList());

		assertThat(keepHands.size()).isGreaterThan(1);
		assertThat(keepHands.size()).isLessThan(4);
	}

	// ぶた→手札交換する
	@Test
	public void isHighCard() {
		List<Card> hands = new ArrayList<>();
		hands.add(makeCard(1, CardType.SPADE, false));
		hands.add(makeCard(2, CardType.CLUB, false));
		hands.add(makeCard(9, CardType.HEART, false));
		hands.add(makeCard(5, CardType.DIAMOND, false));
		hands.add(makeCard(10, CardType.SPADE, false));

		List<Card> keepHands = sut.exchangeTargetDecision(sut.roleCheck(hands, checker), hands)
				.stream().filter(h -> h.getIsChange() == false)
				.collect(Collectors.toList());

		assertThat(keepHands.size()).isLessThan(2);
	}


}
