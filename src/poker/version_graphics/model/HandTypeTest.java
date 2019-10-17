package poker.version_graphics.model;

import static org.junit.Assert.*;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

public class HandTypeTest {
	// We define the hands using abbreviations. The code at the bottom
	// of this class can translate one of these strings into a card.
	//
	// Another method takes a set of five cards, and translates the whole hand
	//
	// Yet another method does this for a whole set of hands
	private static String[][] highCards = {
			{ "2S", "9C", "3H", "5D", "7H" },
			{ "7S", "5C", "AH", "JD", "6H" },
			{ "2S", "3C", "4S", "5S", "7S" },
			{ "AS", "KC", "QH", "7D", "TH" }
			};
	
	private static String[][] pairs = {
			{ "2S", "2C", "3H", "5D", "7H" },
			{ "2S", "AC", "3H", "5D", "AH" },
			{ "3S", "2C", "3H", "KD", "QH" },
			{ "9S", "2C", "2H", "5D", "7H" }
			};

	private static String[][] twoPairs = {
			{ "2S", "2C", "7H", "5D", "7H" },
			{ "2S", "AC", "5H", "5D", "AH" },
			{ "3S", "2C", "3H", "2D", "QH" },
			{ "9S", "2C", "2H", "5D", "5H" }
			};
	
	private static String [][] threeOfAKind = {
			{ "2S", "2C", "2H", "5D", "7H" },
			{ "2S", "AC", "3H", "AD", "AH" },
			{ "3S", "2C", "3H", "3D", "QH" },
			{ "9S", "2C", "2H", "2D", "7H" }
	};
	
	private static String [][] fourOfAKind = {
			{ "2S", "2C", "2H", "2D", "7H" },
			{ "AS", "AC", "AH", "AD", "3H" },
			{ "3S", "3C", "3H", "3D", "QH" },
			{ "9S", "9C", "9H", "9D", "7H" }
	};
	
	private static String [][] straight = {
			{ "3S", "4C", "5H", "6D", "7H" },
			{ "AS", "2C", "3H", "4D", "5H" },
			{ "TS", "JC", "QH", "KD", "AH" },
			{ "5S", "6C", "7H", "8D", "9H" }
	};
	
	private static String [][] flush = {
			{ "2S", "4S", "5S", "TS", "JS" },
			{ "AC", "2C", "4C", "6C", "8C" },
			{ "3H", "5H", "TH", "KH", "AH" },
			{ "5D", "7D", "9D", "JD", "KD" }
	};
	
	private static String [][] fullHouse = {
			{ "2S", "2C", "2H", "5D", "5H" },
			{ "3H", "3D", "AC", "AD", "AH" },
			{ "JS", "JD", "JH", "4S", "4H" },
			{ "9S", "9C", "9H", "QD", "QH" }
	};
	
	private static String [][] straightFlush = {
			{ "2S", "3S", "4S", "5S", "6S" },
			{ "AC", "2C", "3C", "4C", "5C" },
			{ "TH", "JH", "QH", "KH", "AH" },
			{ "5D", "6D", "7D", "8D", "9D" }
	};
	
	// This is where we store the translated hands
	ArrayList<ArrayList<Card>> highCardHands;
	ArrayList<ArrayList<Card>> pairHands;
	ArrayList<ArrayList<Card>> twoPairHands;
	ArrayList<ArrayList<Card>> threeOfAKindHands;
	ArrayList<ArrayList<Card>> fourOfAKindHands;
	ArrayList<ArrayList<Card>> straightHands;
	ArrayList<ArrayList<Card>> flushHands;
	ArrayList<ArrayList<Card>> fullHouseHands;
	ArrayList<ArrayList<Card>> straightFlushHands;
	
	
	/**
	 * The makeHands method is called before each test method,
	 * and prepares the translated hands. We recreate these for
	 * each test method, in case the test method damages the data.
	 */
	@Before
	public void makeHands() {
		highCardHands = makeHands(highCards);
		pairHands = makeHands(pairs);
		twoPairHands = makeHands(twoPairs);
		threeOfAKindHands = makeHands(threeOfAKind);
		fourOfAKindHands = makeHands(fourOfAKind);
		straightHands = makeHands(straight);
		flushHands = makeHands(flush);
		fullHouseHands = makeHands(fullHouse);
		straightFlushHands = makeHands(straightFlush);
		
	}

	/**
	 * This is a test method for the isOnePair method in HandType.
	 * We expect all HighCard hands to be false, all OnePair hands to
	 * be true, all TwoPair hands to be true, etc.
	 */
	@Test
	public void testIsOnePair() {
		for (ArrayList<Card> hand : highCardHands) {
			assertFalse(HandType.isOnePair(hand));
		}
		for (ArrayList<Card> hand : pairHands) {
			assertTrue(HandType.isOnePair(hand));
		}
		for (ArrayList<Card> hand : twoPairHands) {
			assertTrue(HandType.isOnePair(hand)); // Two-pair contains a pair
		}	
		for (ArrayList<Card> hand : threeOfAKindHands) {
			assertTrue(HandType.isOnePair(hand));	
		}
		for (ArrayList<Card> hand : fourOfAKindHands) {
			assertTrue(HandType.isOnePair(hand));	
		}
		for (ArrayList<Card> hand : straightHands) {
			assertFalse(HandType.isOnePair(hand));	
		}
		for (ArrayList<Card> hand : flushHands) {
			assertFalse(HandType.isOnePair(hand));	
		}
		for (ArrayList<Card> hand : fullHouseHands) {
			assertTrue(HandType.isOnePair(hand));	
		}
		for (ArrayList<Card> hand : straightFlushHands) {
			assertFalse(HandType.isOnePair(hand));	
		}
		
	}

	/**
	 * This is the test method for the isTwoPair in HandType.
	 */
	@Test
	public void testIsTwoPair() {
		for (ArrayList<Card> hand : highCardHands) {
			assertFalse(HandType.isTwoPair(hand));
		}
		for (ArrayList<Card> hand : pairHands) {
			assertFalse(HandType.isTwoPair(hand));
		}
		for (ArrayList<Card> hand : twoPairHands) {
			assertTrue(HandType.isTwoPair(hand));
		}
		for (ArrayList<Card> hand : threeOfAKindHands) {
			assertFalse(HandType.isTwoPair(hand));
		}
		for (ArrayList<Card> hand : fourOfAKindHands) {
			assertTrue(HandType.isTwoPair(hand));	
		}
		for (ArrayList<Card> hand : straightHands) {
			assertFalse(HandType.isTwoPair(hand));	
		}
		for (ArrayList<Card> hand : flushHands) {
			assertFalse(HandType.isTwoPair(hand));	
		}
		for (ArrayList<Card> hand : fullHouseHands) {
			assertTrue(HandType.isTwoPair(hand));	
		}
		for (ArrayList<Card> hand : straightFlushHands) {
			assertFalse(HandType.isTwoPair(hand));	
		}
	}
	
	@Test
	public void testIsThreeOfAKind() {
		for (ArrayList<Card> hand : highCardHands) {
			assertFalse(HandType.isThreeOfAKind(hand));
		}
		for (ArrayList<Card> hand : pairHands) {
			assertFalse(HandType.isThreeOfAKind(hand));
		}
		for (ArrayList<Card> hand : twoPairHands) {
			assertFalse(HandType.isThreeOfAKind(hand));
		}
		for (ArrayList<Card> hand : threeOfAKindHands) {
			assertTrue(HandType.isThreeOfAKind(hand));
		}
		for (ArrayList<Card> hand : fourOfAKindHands) {
			assertTrue(HandType.isThreeOfAKind(hand));	
		}
		for (ArrayList<Card> hand : straightHands) {
			assertFalse(HandType.isThreeOfAKind(hand));	
		}
		for (ArrayList<Card> hand : flushHands) {
			assertFalse(HandType.isThreeOfAKind(hand));	
		}
		for (ArrayList<Card> hand : fullHouseHands) {
			assertTrue(HandType.isThreeOfAKind(hand));	
		}
		for (ArrayList<Card> hand : straightFlushHands) {
			assertFalse(HandType.isThreeOfAKind(hand));	
		}
	}
	
	@Test
	public void testIsFourOfAKind() {
		for (ArrayList<Card> hand : highCardHands) {
			assertFalse(HandType.isFourOfAKind(hand));
		}
		for (ArrayList<Card> hand : pairHands) {
			assertFalse(HandType.isFourOfAKind(hand));
		}
		for (ArrayList<Card> hand : twoPairHands) {
			assertFalse(HandType.isFourOfAKind(hand));
		}
		for (ArrayList<Card> hand : threeOfAKindHands) {
			assertFalse(HandType.isFourOfAKind(hand));
		}
		for (ArrayList<Card> hand : fourOfAKindHands) {
			assertTrue(HandType.isFourOfAKind(hand));	
		}
		for (ArrayList<Card> hand : straightHands) {
			assertFalse(HandType.isFourOfAKind(hand));	
		}
		for (ArrayList<Card> hand : flushHands) {
			assertFalse(HandType.isFourOfAKind(hand));	
		}
		for (ArrayList<Card> hand : fullHouseHands) {
			assertFalse(HandType.isFourOfAKind(hand));	
		}
		for (ArrayList<Card> hand : straightFlushHands) {
			assertFalse(HandType.isFourOfAKind(hand));	
		}
	}
	
	@Test
	public void testStraight() {
		for (ArrayList<Card> hand : highCardHands) {
			assertFalse(HandType.isStraight(hand));
		}
		for (ArrayList<Card> hand : pairHands) {
			assertFalse(HandType.isStraight(hand));
		}
		for (ArrayList<Card> hand : twoPairHands) {
			assertFalse(HandType.isStraight(hand));
		}
		for (ArrayList<Card> hand : threeOfAKindHands) {
			assertFalse(HandType.isStraight(hand));
		}
		for (ArrayList<Card> hand : fourOfAKindHands) {
			assertFalse(HandType.isStraight(hand));	
		}
		for (ArrayList<Card> hand : straightHands) {
			assertTrue(HandType.isStraight(hand));	
		}
		for (ArrayList<Card> hand : flushHands) {
			assertFalse(HandType.isStraight(hand));	
		}
		for (ArrayList<Card> hand : fullHouseHands) {
			assertFalse(HandType.isStraight(hand));	
		}
		for (ArrayList<Card> hand : straightFlushHands) {
			assertTrue(HandType.isStraight(hand));	
		}
	}
	
	@Test
	public void testFlush() {
		for (ArrayList<Card> hand : highCardHands) {
			assertFalse(HandType.isFlush(hand));
		}
		for (ArrayList<Card> hand : pairHands) {
			assertFalse(HandType.isFlush(hand));
		}
		for (ArrayList<Card> hand : twoPairHands) {
			assertFalse(HandType.isFlush(hand));
		}
		for (ArrayList<Card> hand : threeOfAKindHands) {
			assertFalse(HandType.isFlush(hand));
		}
		for (ArrayList<Card> hand : fourOfAKindHands) {
			assertFalse(HandType.isFlush(hand));	
		}
		for (ArrayList<Card> hand : straightHands) {
			assertFalse(HandType.isFlush(hand));	
		}
		for (ArrayList<Card> hand : flushHands) {
			assertTrue(HandType.isFlush(hand));	
		}
		for (ArrayList<Card> hand : fullHouseHands) {
			assertFalse(HandType.isFlush(hand));	
		}
		for (ArrayList<Card> hand : straightFlushHands) {
			assertTrue(HandType.isFlush(hand));	
		}
	}
	
	@Test
	public void testFullHouse() {
		for (ArrayList<Card> hand : highCardHands) {
			assertFalse(HandType.isFullHouse(hand));
		}
		for (ArrayList<Card> hand : pairHands) {
			assertTrue(HandType.isFullHouse(hand));
		}
		for (ArrayList<Card> hand : twoPairHands) {
			assertTrue(HandType.isFullHouse(hand));
		}
		for (ArrayList<Card> hand : threeOfAKindHands) {
			assertTrue(HandType.isFullHouse(hand));
		}
		for (ArrayList<Card> hand : fourOfAKindHands) {
			assertFalse(HandType.isFullHouse(hand));	
		}
		for (ArrayList<Card> hand : straightHands) {
			assertFalse(HandType.isFullHouse(hand));	
		}
		for (ArrayList<Card> hand : flushHands) {
			assertFalse(HandType.isFullHouse(hand));	
		}
		for (ArrayList<Card> hand : fullHouseHands) {
			assertTrue(HandType.isFullHouse(hand));	
		}
		for (ArrayList<Card> hand : straightFlushHands) {
			assertFalse(HandType.isFullHouse(hand));	
		}
	}
	
	@Test
	public void testStraightFlush() {
		for (ArrayList<Card> hand : highCardHands) {
			assertFalse(HandType.isStraightFlush(hand));
		}
		for (ArrayList<Card> hand : pairHands) {
			assertFalse(HandType.isStraightFlush(hand));
		}
		for (ArrayList<Card> hand : twoPairHands) {
			assertFalse(HandType.isStraightFlush(hand));
		}
		for (ArrayList<Card> hand : threeOfAKindHands) {
			assertFalse(HandType.isStraightFlush(hand));
		}
		for (ArrayList<Card> hand : fourOfAKindHands) {
			assertFalse(HandType.isStraightFlush(hand));	
		}
		for (ArrayList<Card> hand : straightHands) {
			assertTrue(HandType.isStraightFlush(hand));	
		}
		for (ArrayList<Card> hand : flushHands) {
			assertTrue(HandType.isStraightFlush(hand));	
		}
		for (ArrayList<Card> hand : fullHouseHands) {
			assertFalse(HandType.isStraightFlush(hand));	
		}
		for (ArrayList<Card> hand : straightFlushHands) {
			assertTrue(HandType.isStraightFlush(hand));	
		}
	}
	
	/**
	 * Make an ArrayList of hands from an array of string-arrays
	 */
	private ArrayList<ArrayList<Card>> makeHands(String[][] handsIn) {
		ArrayList<ArrayList<Card>> handsOut = new ArrayList<>();
		for (String[] hand : handsIn) {
			handsOut.add(makeHand(hand));
		}
		return handsOut;
	}
	
	/**
	 * Make a hand (ArrayList<Card>) from an array of 5 strings
	 */
	private ArrayList<Card> makeHand(String[] inStrings) {
		ArrayList<Card> hand = new ArrayList<>();
		for (String in : inStrings) {
			hand.add(makeCard(in));
		}
		return hand;
	}
	
	/**
	 * Create a card from a 2-character String.
	 * First character is the rank (2-9, T, J, Q, K, A) 
	 * Second character is the suit (C, D, H, S)
	 * 
	 * No validation or error handling!
	 */
	private Card makeCard(String in) {
		char r = in.charAt(0);
		Card.Rank rank = null;
		if (r <= '9') rank = Card.Rank.values()[r-'0' - 2];
		else if (r == 'T') rank = Card.Rank.Ten;
		else if (r == 'J') rank = Card.Rank.Jack;
		else if (r == 'Q') rank = Card.Rank.Queen;
		else if (r == 'K') rank = Card.Rank.King;
		else if (r == 'A') rank = Card.Rank.Ace;
		
		char s = in.charAt(1);
		Card.Suit suit = null;
		if (s == 'C') suit = Card.Suit.Clubs;
		if (s == 'D') suit = Card.Suit.Diamonds;
		if (s == 'H') suit = Card.Suit.Hearts;
		if (s == 'S') suit = Card.Suit.Spades;

		return new Card(suit, rank);
	}
}
