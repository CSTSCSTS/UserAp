package com.example.demo.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.example.demo.domain.model.Card;
import com.example.demo.domain.model.CardType;
import com.example.demo.domain.model.checker.Checker;
import com.example.demo.domain.model.checker.FiveCardChecker;
import com.example.demo.domain.model.checker.FlushChecker;
import com.example.demo.domain.model.checker.FourCardChecker;
import com.example.demo.domain.model.checker.FullHouseChecker;
import com.example.demo.domain.model.checker.HighCardChecker;
import com.example.demo.domain.model.checker.OnePairChecker;
import com.example.demo.domain.model.checker.RoyalStraightFlushChecker;
import com.example.demo.domain.model.checker.StraightChecker;
import com.example.demo.domain.model.checker.StraightFlushChecker;
import com.example.demo.domain.model.checker.ThreeCardChecker;
import com.example.demo.domain.model.checker.TwoPairChecker;

public class PokerUtil {

	public static boolean isHighCard(List<Card> hands) {
		return !isIncludeJoker(hands) &&
				!isOnePair(hands) &&
				!isStraight(hands) &&
				!isFlush(hands);
	}

	public static boolean isOnePair(List<Card> hands) {
		List<Card> handsCopy = new ArrayList<>(hands);
		while (handsCopy.size() > 1) {
			Card card = handsCopy.remove(0);
			int numberOfMatch = handsCopy.stream()
					.filter(h -> h.getNumber() == card.getNumber())
					.collect(Collectors.toList()).size();
			if (numberOfMatch == 1) {
				return true;
			}
		}
		return false;
	}

	public static boolean isOnePairWithJoker(List<Card> hands) {
		return isIncludeJoker(hands);
	}

	public static boolean isTwoPair(List<Card> hands) {
		List<Card> handsCopy = new ArrayList<>(hands);
		for (int i = 0; i < 2; i++) {
			Card card = handsCopy.remove(0);
			int numberOfMatch = handsCopy.stream()
					.filter(h -> h.getNumber() == card.getNumber())
					.collect(Collectors.toList()).size();
			if (numberOfMatch == 1) {
				// 一致したcardは削除する。
				// 残りの手札で1ペアがあれば、2ペアが成立する。
				return isOnePair(deleteSameNumberCard(handsCopy, card));
			}
		}
		return false;
	}

	public static boolean isThreeCard(List<Card> hands) {
		List<Card> handsCopy = new ArrayList<>(hands);
		while (handsCopy.size() > 2) {
			Card card = handsCopy.remove(0);
			int numberOfMatch = handsCopy.stream()
					.filter(h -> h.getNumber() == card.getNumber())
					.collect(Collectors.toList()).size();
			if (numberOfMatch == 2) {
				return true;
			}
		}
		return false;
	}

	public static boolean isThreeCardWithJoker(List<Card> hands) {
		if (!isIncludeJoker(hands)) {
			return false;
		}
		List<Card> excludeJoker = hands.stream()
				.filter(h -> !h.getType().equals(CardType.JOKER))
				.collect(Collectors.toList());

		return isOnePair(excludeJoker);
	}

	// 数字が小さい順でソートする(1番小さい数字をnとする)
	// 残りの4枚がそれぞれ、n+1, n+2, n+3, n+4であること(重複はNG)

	// 1番小さいのが1だった場合
	// 残りの4枚が2,3,4,5 or 10,11,12,13であればOK
	public static boolean isStraight(List<Card> hands) {

		List<Card> afterSort =
				hands.stream()
				.sorted((h1,h2) -> h1.getNumber().compareTo(h2.getNumber()))
        .collect(Collectors.toList());

		if(afterSort.get(0).getNumber() == 1) {
			return isRoyal(afterSort) ||
					isIncludeNumber(afterSort, 2) +
					isIncludeNumber(afterSort, 3) +
					isIncludeNumber(afterSort, 4) +
					isIncludeNumber(afterSort, 5) == 4;
		}

		Integer smallest = afterSort.get(0).getNumber();

		return isIncludeNumber(hands, smallest + 1) +
		    isIncludeNumber(hands, smallest + 2) +
		    isIncludeNumber(hands, smallest + 3) +
		    isIncludeNumber(hands, smallest + 4) == 4;
	}

	// ジョーカー判定
	// ジョーカーをリストから削除した4枚で考える。
	// 数字が小さい順でソートする(1番小さい数字をnとする)
	// 残りの3枚がそれぞれ、n+1, n+2, n+3, n+4の内のいずれかであること(重複はNG)
	public static boolean isStraightWithJoker(List<Card> hands) {

		if (!isIncludeJoker(hands)) {
			return false;
		}
		List<Card> excludeJoker = hands.stream()
				.filter(h -> !h.getType().equals(CardType.JOKER))
				.collect(Collectors.toList());

		List<Card> afterSort =
				excludeJoker.stream()
				.sorted((h1,h2) -> h1.getNumber().compareTo(h2.getNumber()))
        .collect(Collectors.toList());

		if(afterSort.get(0).getNumber() == 1) {
			return isRoyalWithJoker(afterSort) ||
					isIncludeNumber(afterSort, 2) +
					isIncludeNumber(afterSort, 3) +
					isIncludeNumber(afterSort, 4) +
					isIncludeNumber(afterSort, 5) == 3;
		}

		Integer smallest = afterSort.get(0).getNumber();

		return isIncludeNumber(hands, smallest + 1) +
		    isIncludeNumber(hands, smallest + 2) +
		    isIncludeNumber(hands, smallest + 3) +
		    isIncludeNumber(hands, smallest + 4) == 3;
	}

	public static boolean isFlush(List<Card> hands) {
		List<Card> handsCopy = new ArrayList<>(hands);
		Card card = handsCopy.remove(0);
		int matchNumber = hands.stream()
				.filter(h -> !h.getType().equals(card.getType()))
				.collect(Collectors.toList()).size();
		if (matchNumber == 0) {
			return true;
		}
		return false;

	}

	// ジョーカー判定
	public static boolean isFlushWithJoker(List<Card> hands) {
		if (!isIncludeJoker(hands)) {
			return false;
		}
		List<Card> excludeJoker = hands.stream()
				.filter(h -> !h.getType().equals(CardType.JOKER))
				.collect(Collectors.toList());

		return isFlush(excludeJoker);
	}

	public static boolean isFullHouse(List<Card> hands) {
		List<Card> handsCopy = new ArrayList<>(hands);
		Card card = handsCopy.remove(0);
		int matchNumber = handsCopy.stream()
				.filter(h -> h.getNumber().equals(card.getNumber()))
				.collect(Collectors.toList()).size();

		switch (matchNumber) {
		case 1:
			// 一致したものをhandsCopyから消す
			List<Card> two = deleteSameNumberCard(handsCopy, card);
			int number = two.get(0).getNumber();
			// 残り2枚が同じカードならフルハウス
			return two.stream()
					.filter(e -> e.getNumber() == number)
					.collect(Collectors.toList()).size() == 3;

		case 2:
			// 一致したものをhandsCopyから消す
			List<Card> three = deleteSameNumberCard(handsCopy, card);
			int num = three.get(0).getNumber();
			// 残り3枚が同じカードならフルハウス
			return three.stream()
					.filter(e -> e.getNumber() == num)
					.collect(Collectors.toList()).size() == 2;

		}
		return false;

	}

	public static boolean isFullHouseWithJoker(List<Card> hands) {

		if (!isIncludeJoker(hands)) {
			return false;
		}
		List<Card> excludeJoker = hands.stream()
				.filter(h -> !h.getType().equals(CardType.JOKER))
				.collect(Collectors.toList());

		for (int i = 0; i < 2; i++) {
			Card card = excludeJoker.remove(0);
			int numberOfMatch = excludeJoker.stream()
					.filter(h -> h.getNumber() == card.getNumber())
					.collect(Collectors.toList()).size();
			if (numberOfMatch != 1) {
				// 一致したcardは削除する。
				// 残りの手札で1ペアがあれば、フルハウスが成立する。
				return false;
			}
			excludeJoker = deleteSameNumberCard(excludeJoker, card);
		}

		return true;

	}

	public static boolean isFourCard(List<Card> hands) {
		List<Card> handsCopy = new ArrayList<>(hands);
		while (handsCopy.size() > 3) {
			Card card = handsCopy.remove(0);
			int numberOfMatch = handsCopy.stream()
					.filter(h -> h.getNumber() == card.getNumber())
					.collect(Collectors.toList()).size();
			if (numberOfMatch == 3) {
				return true;
			}
		}
		return false;

	}

	public static boolean isFourCardWithJoker(List<Card> hands) {
		if (!isIncludeJoker(hands)) {
			return false;
		}
		List<Card> excludeJoker = hands.stream()
				.filter(h -> !h.getType().equals(CardType.JOKER))
				.collect(Collectors.toList());

		return isThreeCard(excludeJoker);

	}

	public static boolean isStraightFlush(List<Card> hands) {
		return isStraight(hands) && isFlush(hands);
	}

	public static boolean isStraightFlushWithJoker(List<Card> hands) {
		return isStraightWithJoker(hands) && isFlushWithJoker(hands);
	}

	public static boolean isRoyalStraightFlush(List<Card> hands) {
		return isRoyal(hands) && isStraight(hands) && isFlush(hands);
	}

	//以下の要件を満たしていること
	// マークが一緒であること
	// 10,11,12,13,1のうち、4つが入っていること。
	public static boolean isRoyalStraightFlushWithJoker(List<Card> hands) {
		return isRoyalWithJoker(hands) && isStraightWithJoker(hands) && isFlushWithJoker(hands);
	}

	public static boolean isFiveCard(List<Card> hands) {
		List<Card> excludeJoker = hands.stream()
				.filter(h -> !h.getType().equals(CardType.JOKER))
				.collect(Collectors.toList());

		return isFourCard(excludeJoker) && isIncludeJoker(hands);
	}

  //numberがあるかどうか
	public static int isIncludeNumber(List<Card> hands, int number) {
		Optional<Card> card =  hands.stream()
				.filter(h -> h.getNumber() == number)
				.findFirst();

		if(card.isPresent()) {
			return 1;
		} else {
			return 0;
		}

	}

	public static boolean isIncludeJoker(List<Card> hands) {
		return hands.stream()
				.filter(h -> h.getType().equals(CardType.JOKER))
				.findFirst().isPresent();
	}

	// 手札が10,11,12,13,1であること
	public static boolean isRoyal(List<Card> hands) {
		return isIncludeNumber(hands, 10) +
				    isIncludeNumber(hands, 11) +
				    isIncludeNumber(hands, 12) +
				    isIncludeNumber(hands, 13) +
				    isIncludeNumber(hands, 1) == 5;

	}

	public static boolean nearRoyal(List<Card> hands) {
		return isIncludeNumber(hands, 10) +
				    isIncludeNumber(hands, 11) +
				    isIncludeNumber(hands, 12) +
				    isIncludeNumber(hands, 13) +
				    isIncludeNumber(hands, 1) == 4;

	}

	//手札が10,11,12,13,1の内4枚のいずれかであること(重複はNG)
	public static boolean isRoyalWithJoker(List<Card> hands) {

		return isIncludeNumber(hands, 10) +
				    isIncludeNumber(hands, 11) +
				    isIncludeNumber(hands, 12) +
				    isIncludeNumber(hands, 13) +
				    isIncludeNumber(hands, 1) == 4;

	}

	// 引数のcardと同じ数字のcardをリストから削除する。
	private static List<Card> deleteSameNumberCard(List<Card> handsCopy, Card card) {
		return handsCopy.stream()
				.filter(h -> h.getNumber() != card.getNumber())
				.collect(Collectors.toList());
	}

	public static List<Card> noDupilicateNumberCards(List<Card> hands) {

		List<Card> noDuplicateList = new ArrayList<>();
		for(int i=0;i<hands.size();i++) {
      List<Card> handsCopy = new ArrayList<>(hands);
      Card card = handsCopy.remove(i);
      Optional<Card> duplicate = handsCopy.stream()
      .filter(h -> h.getNumber().equals(card.getNumber()))
      .findFirst();

      if(!duplicate.isPresent()) {
      	noDuplicateList.add(card);
      }

		}
		return noDuplicateList.stream()
				.filter(n -> !n.getType().equals(CardType.JOKER))
				.sorted((h1,h2) -> h1.getNumber().compareTo(h2.getNumber()))
	      .collect(Collectors.toList());
	}

	// ストレートを作るのに不要な手札を見つける
	public static List<Card> changeFlagDisturbedCardToMakeStraight(List<Card> hands) {

		List<Card> changeCandidateList = new ArrayList<>();

		for(Card card: hands) {
			if(!isIncludeDoubleNumber(hands, card.getNumber() + 1, card.getNumber() - 1)) {
				changeCandidateList.add(card);
			}
		}
		switch (changeCandidateList.size()) {
		case 0:

			List<Card> afterSort =
			hands.stream()
			.sorted((h1,h2) -> h1.getNumber().compareTo(h2.getNumber()))
      .collect(Collectors.toList());

			afterSort.get(0).setIsChange(true);
			return afterSort;

		case 1:

			hands.stream()
			.filter(h -> h.equals(changeCandidateList.get(0)))
			.forEach(h -> h.setIsChange(true));

			return hands;

		case 2:

			for(Card card: changeCandidateList) {
				if(!isIncludeDoubleNumber(hands, card.getNumber() - 2, card.getNumber() + 2)) {
					hands.stream()
					.filter(h -> h.equals(card))
					.forEach(h -> h.setIsChange(true));

					return hands;

				}
				Card changeTarget =
						changeCandidateList.stream()
						.sorted((h1,h2) -> h1.getNumber().compareTo(h2.getNumber()))
			      .collect(Collectors.toList()).get(0);

				hands.stream()
				.filter(h -> h.equals(changeTarget))
				.forEach(h -> h.setIsChange(true));

			}

			return hands;

		}
		return hands;

	}

	// マークがほかに重複していないカードのisChangedをtrueにして返す。
	public static List<Card> noDupilicateMarkCardChange(List<Card> hands) {
		for(Card card: hands) {
			if(sameCardTypeNumber(hands, card.getType()) != 4) {
				card.setIsChange(true);
				return hands;
			}
		}
		return hands;
	}

	// ストレートに近い札かどうか？
	public static boolean nearStraight(List<Card> hands) {
		List<Card> afterSort =
				hands.stream()
				.sorted((h1,h2) -> h1.getNumber().compareTo(h2.getNumber()))
        .collect(Collectors.toList());

		if(afterSort.get(0).getNumber() == 1) {
			return nearRoyal(afterSort) ||
					isIncludeNumber(afterSort, 2) +
					isIncludeNumber(afterSort, 3) +
					isIncludeNumber(afterSort, 4) +
					isIncludeNumber(afterSort, 5) == 3;
		}

		Integer smallest = afterSort.get(0).getNumber();

		List<Card> excludeSmallest = new ArrayList<>(afterSort);
		excludeSmallest.remove(0);

		return isIncludeNumber(hands, smallest + 1) +
		    isIncludeNumber(hands, smallest + 2) +
		    isIncludeNumber(hands, smallest + 3) +
		    isIncludeNumber(hands, smallest + 4) == 3 ||
		    isContinuous(excludeSmallest);
	}


	public static boolean nearFlush(List<Card> hands) {
		return sameCardTypeNumber(hands, CardType.SPADE) == 4 ||
				    sameCardTypeNumber(hands, CardType.CLUB) == 4 ||
		    		sameCardTypeNumber(hands, CardType.HEART) == 4 ||
		    		sameCardTypeNumber(hands, CardType.DIAMOND) == 4;
	}

	public static int sameCardTypeNumber(List<Card> hands, CardType type) {
		return hands.stream()
				.filter(h -> h.getType().equals(type))
				.collect(Collectors.toList()).size();
	}

	public static boolean isContinuous(List<Card> excludeSmallestNumbers) {
		Integer secondalySmall = excludeSmallestNumbers.get(0).getNumber();
		return isIncludeNumber(excludeSmallestNumbers, secondalySmall + 1) +
		    isIncludeNumber(excludeSmallestNumbers, secondalySmall + 2) +
		    isIncludeNumber(excludeSmallestNumbers, secondalySmall + 3) +
		    isIncludeNumber(excludeSmallestNumbers, secondalySmall + 4) == 3;
	}

	public static boolean isIncludeDoubleNumber(List<Card> hands, Integer number1, Integer number2) {
		return hands.stream()
				.filter(h -> h.getNumber().equals(number1))
				.findFirst().isPresent() ||
				 hands.stream()
				.filter(h -> h.getNumber().equals(number2))
				.findFirst().isPresent();
	}

	public static Checker makeChecker() {
		Checker fiveCardChecker = new FiveCardChecker();
		Checker royalStraightFlushChecker = new RoyalStraightFlushChecker();
		Checker straightFlushChecker = new StraightFlushChecker();
		Checker fourCardChecker = new FourCardChecker();
		Checker fullHouseChecker = new FullHouseChecker();
		Checker flushChecker = new FlushChecker();
		Checker straightChecker = new StraightChecker();
		Checker threeCardChecker = new ThreeCardChecker();
		Checker twoPairChecker = new TwoPairChecker();
		Checker onePairChecker = new OnePairChecker();
		Checker highCardChecker = new HighCardChecker();

		fiveCardChecker
		  .setNext(royalStraightFlushChecker)
		  .setNext(straightFlushChecker)
		  .setNext(fourCardChecker)
		  .setNext(fullHouseChecker)
		  .setNext(flushChecker)
		  .setNext(straightChecker)
		  .setNext(threeCardChecker)
		  .setNext(twoPairChecker)
		  .setNext(onePairChecker)
		  .setNext(highCardChecker);
		return fiveCardChecker;
	}

}
