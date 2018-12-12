package com.example.demo.service;

import static org.assertj.core.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.example.demo.domain.model.Card;
import com.example.demo.domain.model.CardType;
import com.example.demo.domain.model.Role;
import com.example.demo.domain.model.checker.Checker;
import com.example.demo.util.PokerUtil;

public class PokerServiceRoleCheckTest {

	private PokerService sut;
	private final Checker checker = PokerUtil.makeChecker();

	@Before
	public  void setUp() {
		sut = new PokerService();
	}

	private static Card makeCard(Integer number, CardType type) {
		return Card.builder().number(number).type(type).build();
	}
	// 各役を認識できること

	// 5カード
		@Test
		public void isFiveCard() {
			List<Card> hands = new ArrayList<>();
			hands.add(makeCard(1, CardType.SPADE));
			hands.add(makeCard(1, CardType.CLUB));
			hands.add(makeCard(1, CardType.HEART));
			hands.add(makeCard(1, CardType.DIAMOND));
			hands.add(makeCard(0, CardType.JOKER));

			assertThat(sut.roleCheck(hands, checker)).isEqualTo(Role.getFiveCard());
		}



	// ロイヤルストレートフラッシュ
		@Test
		public void isRoyalStraightFlush() {
			List<Card> hands = new ArrayList<>();
			hands.add(makeCard(1, CardType.SPADE));
			hands.add(makeCard(10, CardType.SPADE));
			hands.add(makeCard(11, CardType.SPADE));
			hands.add(makeCard(12, CardType.SPADE));
			hands.add(makeCard(13, CardType.SPADE));

			assertThat(sut.roleCheck(hands, checker)).isEqualTo(Role.getRoyelStraightFlush());
		}
		@Test
		public void isRoyalStraightFlushWithJoker() {
			List<Card> hands = new ArrayList<>();
			hands.add(makeCard(1, CardType.SPADE));
			hands.add(makeCard(10, CardType.SPADE));
			hands.add(makeCard(11, CardType.SPADE));
			hands.add(makeCard(12, CardType.SPADE));
			hands.add(makeCard(0, CardType.JOKER));

			assertThat(sut.roleCheck(hands, checker)).isEqualTo(Role.getRoyelStraightFlush());
		}

	// ストレートフラッシュ
		@Test
		public void isStraightFlush() {
			List<Card> hands = new ArrayList<>();
			hands.add(makeCard(1, CardType.SPADE));
			hands.add(makeCard(2, CardType.SPADE));
			hands.add(makeCard(3, CardType.SPADE));
			hands.add(makeCard(4, CardType.SPADE));
			hands.add(makeCard(5, CardType.SPADE));

			assertThat(sut.roleCheck(hands, checker)).isEqualTo(Role.getStraightFlush());
		}
		@Test
		public void isStraightFlushWithJoker() {
			List<Card> hands = new ArrayList<>();
			hands.add(makeCard(5, CardType.SPADE));
			hands.add(makeCard(7, CardType.SPADE));
			hands.add(makeCard(8, CardType.SPADE));
			hands.add(makeCard(9, CardType.SPADE));
			hands.add(makeCard(0, CardType.JOKER));

			assertThat(sut.roleCheck(hands, checker)).isEqualTo(Role.getStraightFlush());
		}


	// 4カード
		@Test
		public void isFourCard() {
			List<Card> hands = new ArrayList<>();
			hands.add(makeCard(1, CardType.SPADE));
			hands.add(makeCard(1, CardType.CLUB));
			hands.add(makeCard(1, CardType.HEART));
			hands.add(makeCard(1, CardType.DIAMOND));
			hands.add(makeCard(5, CardType.SPADE));

			assertThat(sut.roleCheck(hands, checker)).isEqualTo(Role.getFourCard());
		}

		@Test
		public void isFourCardWithJoker() {
			List<Card> hands = new ArrayList<>();
			hands.add(makeCard(1, CardType.SPADE));
			hands.add(makeCard(1, CardType.CLUB));
			hands.add(makeCard(1, CardType.HEART));
			hands.add(makeCard(0, CardType.JOKER));
			hands.add(makeCard(5, CardType.SPADE));

			assertThat(sut.roleCheck(hands, checker)).isEqualTo(Role.getFourCard());
		}


	//フルハウス
		@Test
		public void isFullHouse() {
			List<Card> hands = new ArrayList<>();
			hands.add(makeCard(1, CardType.SPADE));
			hands.add(makeCard(1, CardType.CLUB));
			hands.add(makeCard(1, CardType.HEART));
			hands.add(makeCard(5, CardType.DIAMOND));
			hands.add(makeCard(5, CardType.SPADE));

			assertThat(sut.roleCheck(hands, checker)).isEqualTo(Role.getFullHouse());
		}

		@Test
		public void isFullHouseWithJoker() {
			List<Card> hands = new ArrayList<>();
			hands.add(makeCard(1, CardType.SPADE));
			hands.add(makeCard(1, CardType.CLUB));
			hands.add(makeCard(5, CardType.HEART));
			hands.add(makeCard(0, CardType.JOKER));
			hands.add(makeCard(5, CardType.SPADE));

			assertThat(sut.roleCheck(hands, checker)).isEqualTo(Role.getFullHouse());
		}


	// フラッシュ
		@Test
		public void isFlush() {
			List<Card> hands = new ArrayList<>();
			hands.add(makeCard(1, CardType.SPADE));
			hands.add(makeCard(2, CardType.SPADE));
			hands.add(makeCard(8, CardType.SPADE));
			hands.add(makeCard(7, CardType.SPADE));
			hands.add(makeCard(11, CardType.SPADE));

			assertThat(sut.roleCheck(hands, checker)).isEqualTo(Role.getFlush());
		}
		@Test
		public void isFlushWithJoker() {
			List<Card> hands = new ArrayList<>();
			hands.add(makeCard(5, CardType.SPADE));
			hands.add(makeCard(7, CardType.SPADE));
			hands.add(makeCard(11, CardType.SPADE));
			hands.add(makeCard(3, CardType.SPADE));
			hands.add(makeCard(0, CardType.JOKER));

			assertThat(sut.roleCheck(hands, checker)).isEqualTo(Role.getFlush());
		}


	// ストレート

		@Test
		public void isStraight() {
			List<Card> hands = new ArrayList<>();
			hands.add(makeCard(1, CardType.SPADE));
			hands.add(makeCard(2, CardType.HEART));
			hands.add(makeCard(3, CardType.SPADE));
			hands.add(makeCard(4, CardType.DIAMOND));
			hands.add(makeCard(5, CardType.SPADE));

			assertThat(sut.roleCheck(hands, checker)).isEqualTo(Role.getStraight());
		}
		@Test
		public void isStraightWithJoker() {
			List<Card> hands = new ArrayList<>();
			hands.add(makeCard(1, CardType.CLUB));
			hands.add(makeCard(10, CardType.CLUB));
			hands.add(makeCard(12, CardType.SPADE));
			hands.add(makeCard(11, CardType.DIAMOND));
			hands.add(makeCard(0, CardType.JOKER));

			assertThat(sut.roleCheck(hands, checker)).isEqualTo(Role.getStraight());
		}

	// 3カード

		@Test
		public void isThreeCard() {
			List<Card> hands = new ArrayList<>();
			hands.add(makeCard(1, CardType.SPADE));
			hands.add(makeCard(1, CardType.CLUB));
			hands.add(makeCard(1, CardType.HEART));
			hands.add(makeCard(5, CardType.DIAMOND));
			hands.add(makeCard(10, CardType.SPADE));

			assertThat(sut.roleCheck(hands, checker)).isEqualTo(Role.getThreeCard());
		}

		@Test
		public void isThreeCardWithJoker() {
			List<Card> hands = new ArrayList<>();
			hands.add(makeCard(1, CardType.SPADE));
			hands.add(makeCard(7, CardType.CLUB));
			hands.add(makeCard(5, CardType.HEART));
			hands.add(makeCard(0, CardType.JOKER));
			hands.add(makeCard(5, CardType.SPADE));

			assertThat(sut.roleCheck(hands, checker)).isEqualTo(Role.getThreeCard());
		}

	// 2ペア

		@Test
		public void isTwoPair() {
			List<Card> hands = new ArrayList<>();
			hands.add(makeCard(1, CardType.SPADE));
			hands.add(makeCard(1, CardType.CLUB));
			hands.add(makeCard(5, CardType.HEART));
			hands.add(makeCard(5, CardType.DIAMOND));
			hands.add(makeCard(10, CardType.SPADE));

			assertThat(sut.roleCheck(hands, checker)).isEqualTo(Role.getTwoPair());
		}


	// 1ペア
		@Test
		public void isOnePair() {
			List<Card> hands = new ArrayList<>();
			hands.add(makeCard(1, CardType.SPADE));
			hands.add(makeCard(1, CardType.CLUB));
			hands.add(makeCard(9, CardType.HEART));
			hands.add(makeCard(5, CardType.DIAMOND));
			hands.add(makeCard(10, CardType.SPADE));

			assertThat(sut.roleCheck(hands, checker)).isEqualTo(Role.getOnePair());
		}

		@Test
		public void isOnePairWithJoker() {
			List<Card> hands = new ArrayList<>();
			hands.add(makeCard(1, CardType.SPADE));
			hands.add(makeCard(7, CardType.CLUB));
			hands.add(makeCard(6, CardType.HEART));
			hands.add(makeCard(0, CardType.JOKER));
			hands.add(makeCard(5, CardType.SPADE));

			assertThat(sut.roleCheck(hands, checker)).isEqualTo(Role.getOnePair());
		}

		@Test
		public void isHighCard() {
			List<Card> hands = new ArrayList<>();
			hands.add(makeCard(1, CardType.SPADE));
			hands.add(makeCard(2, CardType.CLUB));
			hands.add(makeCard(9, CardType.HEART));
			hands.add(makeCard(5, CardType.DIAMOND));
			hands.add(makeCard(10, CardType.SPADE));

			assertThat(sut.roleCheck(hands, checker)).isEqualTo(Role.getHighCard());
		}

}
