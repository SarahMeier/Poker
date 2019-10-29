
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
			{ "3S", "9C", "4H", "5D", "7H" },
			{ "7S", "5C", "AH", "JD", "6H" },
			{ "8S", "3C", "4S", "5S", "7S" },
			{ "AS", "KC", "QH", "7D", "TH" }
			};
	
	private static String[][] pairs = {
			{ "4S", "2C", "4H", "5D", "7H" },
			{ "2S", "AC", "3H", "5D", "AH" },
			{ "5S", "2C", "5H", "KD", "QH" },
			{ "9S", "2C", "2H", "5D", "7H" }
			};

	private static String[][] twoPairs = {
			{ "2S", "2C", "7H", "5D", "7D" },
			{ "2S", "AC", "5H", "5D", "AH" },
			{ "3S", "2C", "3H", "2D", "QH" },
			{ "9S", "2C", "2H", "5D", "5H" }
			};
	
	private static String [][] threeOfAKind = {
			{ "2S", "2C", "2H", "5D", "7H" },
			{ "2S", "AC", "3H", "AD", "AH" },
			{ "3S", "2C", "3H", "3D", "QH" },
			{ "9S", "2C", "7H", "2D", "2H" }
	};
	
	private static String [][] fourOfAKind = {
			{ "2S", "2C", "2H", "2D", "7H" },
			{ "AS", "AC", "AH", "AD", "3H" },
			{ "3S", "3C", "3H", "3D", "QH" },
			{ "7H", "9C", "9H", "9D", "9S" }
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
			{ "9H", "TH", "JH", "QH", "KH" },
			{ "5D", "6D", "7D", "8D", "9D" }
	};
	
	private static String [][] royalFlush = {
			{ "TS", "JS", "QS", "KS", "AS" },
			{ "TC", "JC", "KC", "QC", "AC" },
			{ "AH", "JH", "QH", "KH", "TH" },
			{ "AD", "KD", "QD", "JD", "TD" }
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
	ArrayList<ArrayList<Card>> royalFlushHands;
	
	
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
		royalFlushHands = makeHands(royalFlush);
		
	}

	/**
	 * test method adapted to the modified HandTypeClass (return object, not boolean)
	 * (earlier comment:
	 * This is a test method for the isOnePair method in HandType.
	 * We expect all HighCard hands to be false, all OnePair hands to
	 * be true, all TwoPair hands to be true, etc.)
	 */
	@Test
	public void testIsOnePair() {
		
		for (ArrayList<Card> hand : highCardHands) {
			assertNull(HandType.isOnePair(hand));
		}
		for (ArrayList<Card> hand : pairHands) {
			assertNotNull(HandType.isOnePair(hand));
		}
		for (ArrayList<Card> hand : twoPairHands) {
			assertNotNull(HandType.isOnePair(hand)); // Two-pair contains a pair
		}	
		for (ArrayList<Card> hand : threeOfAKindHands) {
			assertNotNull(HandType.isOnePair(hand));	
		}
		for (ArrayList<Card> hand : fourOfAKindHands) {
			assertNotNull(HandType.isOnePair(hand));	
		}
		for (ArrayList<Card> hand : straightHands) {
			assertNull(HandType.isOnePair(hand));	
		}
		for (ArrayList<Card> hand : flushHands) {
			assertNull(HandType.isOnePair(hand));	
		}
		for (ArrayList<Card> hand : fullHouseHands) {
			assertNotNull(HandType.isOnePair(hand));	
		}
		for (ArrayList<Card> hand : straightFlushHands) {
			assertNull(HandType.isOnePair(hand));	
		}
		
	}

	/**
	 * This is the test method for the isTwoPair in HandType.
	 */
	@Test
	public void testIsTwoPair() {
		for (ArrayList<Card> hand : highCardHands) {
			assertNull(HandType.isTwoPair(hand));
		}
		for (ArrayList<Card> hand : pairHands) {
			assertNull(HandType.isTwoPair(hand));
		}
		for (ArrayList<Card> hand : twoPairHands) {
			assertNotNull(HandType.isTwoPair(hand));
		}
		for (ArrayList<Card> hand : threeOfAKindHands) {
			assertNull(HandType.isTwoPair(hand));
		}
		for (ArrayList<Card> hand : fourOfAKindHands) {
			assertNotNull(HandType.isTwoPair(hand));	
		}
		for (ArrayList<Card> hand : straightHands) {
			assertNull(HandType.isTwoPair(hand));	
		}
		for (ArrayList<Card> hand : flushHands) {
			assertNull(HandType.isTwoPair(hand));	
		}
		for (ArrayList<Card> hand : fullHouseHands) {
			assertNotNull(HandType.isTwoPair(hand));	
		}
		for (ArrayList<Card> hand : straightFlushHands) {
			assertNull(HandType.isTwoPair(hand));	
		}
		for (ArrayList<Card> hand : royalFlushHands) {
			assertNull(HandType.isTwoPair(hand));	
		}
	}
	
	@Test
	public void testIsThreeOfAKind() {
		for (ArrayList<Card> hand : highCardHands) {
			assertNull(HandType.isThreeOfAKind(hand));
		}
		for (ArrayList<Card> hand : pairHands) {
			assertNull(HandType.isThreeOfAKind(hand));
		}
		for (ArrayList<Card> hand : twoPairHands) {
			assertNull(HandType.isThreeOfAKind(hand));
		}
		for (ArrayList<Card> hand : threeOfAKindHands) {
			assertNotNull(HandType.isThreeOfAKind(hand));
		}
		for (ArrayList<Card> hand : fourOfAKindHands) {
			assertNotNull(HandType.isThreeOfAKind(hand));	
		}
		for (ArrayList<Card> hand : straightHands) {
			assertNull(HandType.isThreeOfAKind(hand));	
		}
		for (ArrayList<Card> hand : flushHands) {
			assertNull(HandType.isThreeOfAKind(hand));	
		}
		for (ArrayList<Card> hand : fullHouseHands) {
			assertNotNull(HandType.isThreeOfAKind(hand));	
		}
		for (ArrayList<Card> hand : straightFlushHands) {
			assertNull(HandType.isThreeOfAKind(hand));	
		}
		for (ArrayList<Card> hand : royalFlushHands) {
			assertNull(HandType.isThreeOfAKind(hand));	
		}
	}
	
	@Test
	public void testIsFourOfAKind() {
		for (ArrayList<Card> hand : highCardHands) {
			assertNull(HandType.isFourOfAKind(hand));
		}
		for (ArrayList<Card> hand : pairHands) {
			assertNull(HandType.isFourOfAKind(hand));
		}
		for (ArrayList<Card> hand : twoPairHands) {
			assertNull(HandType.isFourOfAKind(hand));
		}
		for (ArrayList<Card> hand : threeOfAKindHands) {
			assertNull(HandType.isFourOfAKind(hand));
		}
		for (ArrayList<Card> hand : fourOfAKindHands) {
			assertNotNull(HandType.isFourOfAKind(hand));	
		}
		for (ArrayList<Card> hand : straightHands) {
			assertNull(HandType.isFourOfAKind(hand));	
		}
		for (ArrayList<Card> hand : flushHands) {
			assertNull(HandType.isFourOfAKind(hand));	
		}
		for (ArrayList<Card> hand : fullHouseHands) {
			assertNull(HandType.isFourOfAKind(hand));	
		}
		for (ArrayList<Card> hand : straightFlushHands) {
			assertNull(HandType.isFourOfAKind(hand));	
		}
		for (ArrayList<Card> hand : royalFlushHands) {
			assertNull(HandType.isFourOfAKind(hand));	
		}
	}
	
	@Test
	public void testStraight() {
		for (ArrayList<Card> hand : highCardHands) {
			assertNull(HandType.isStraight(hand));
		}
		for (ArrayList<Card> hand : pairHands) {
			assertNull(HandType.isStraight(hand));
		}
		for (ArrayList<Card> hand : twoPairHands) {
			assertNull(HandType.isStraight(hand));
		}
		for (ArrayList<Card> hand : threeOfAKindHands) {
			assertNull(HandType.isStraight(hand));
		}
		for (ArrayList<Card> hand : fourOfAKindHands) {
			assertNull(HandType.isStraight(hand));	
		}
		for (ArrayList<Card> hand : straightHands) {
			assertNotNull(HandType.isStraight(hand));	
		}
		for (ArrayList<Card> hand : flushHands) {
			assertNull(HandType.isStraight(hand));	
		}
		for (ArrayList<Card> hand : fullHouseHands) {
			assertNull(HandType.isStraight(hand));	
		}
		for (ArrayList<Card> hand : straightFlushHands) {
			assertNotNull(HandType.isStraight(hand));	
		}
		for (ArrayList<Card> hand : royalFlushHands) {
			assertNotNull(HandType.isStraight(hand));	
		}
	}
	
	@Test
	public void testFlush() {
		for (ArrayList<Card> hand : highCardHands) {
			assertNull(HandType.isFlush(hand));
		}
		for (ArrayList<Card> hand : pairHands) {
			assertNull(HandType.isFlush(hand));
		}
		for (ArrayList<Card> hand : twoPairHands) {
			assertNull(HandType.isFlush(hand));
		}
		for (ArrayList<Card> hand : threeOfAKindHands) {
			assertNull(HandType.isFlush(hand));
		}
		for (ArrayList<Card> hand : fourOfAKindHands) {
			assertNull(HandType.isFlush(hand));	
		}
		for (ArrayList<Card> hand : straightHands) {
			assertNull(HandType.isFlush(hand));	
		}
		for (ArrayList<Card> hand : flushHands) {
			assertNotNull(HandType.isFlush(hand));	
		}
		for (ArrayList<Card> hand : fullHouseHands) {
			assertNull(HandType.isFlush(hand));	
		}
		for (ArrayList<Card> hand : straightFlushHands) {
			assertNotNull(HandType.isFlush(hand));	
		}
		for (ArrayList<Card> hand : royalFlushHands) {
			assertNotNull(HandType.isFlush(hand));	
		}
	}
	
	@Test
	public void testFullHouse() {
		for (ArrayList<Card> hand : highCardHands) {
			assertNull(HandType.isFullHouse(hand));
		}
		for (ArrayList<Card> hand : pairHands) {
			assertNull(HandType.isFullHouse(hand));
		}
		for (ArrayList<Card> hand : twoPairHands) {
			assertNull(HandType.isFullHouse(hand));
		}
		for (ArrayList<Card> hand : threeOfAKindHands) {
			assertNull(HandType.isFullHouse(hand));
		}
		for (ArrayList<Card> hand : fourOfAKindHands) {
			assertNull(HandType.isFullHouse(hand));	
		}
		for (ArrayList<Card> hand : straightHands) {
			assertNull(HandType.isFullHouse(hand));	
		}
		for (ArrayList<Card> hand : flushHands) {
			assertNull(HandType.isFullHouse(hand));	
		}
		for (ArrayList<Card> hand : fullHouseHands) {
			assertNotNull(HandType.isFullHouse(hand));	
		}
		for (ArrayList<Card> hand : straightFlushHands) {
			assertNull(HandType.isFullHouse(hand));	
		}
		for (ArrayList<Card> hand : royalFlushHands) {
			assertNull(HandType.isFullHouse(hand));	
		}
	}
	
	@Test
	public void testStraightFlush() {
		for (ArrayList<Card> hand : highCardHands) {
			assertNull(HandType.isStraightFlush(hand));
		}
		for (ArrayList<Card> hand : pairHands) {
			assertNull(HandType.isStraightFlush(hand));
		}
		for (ArrayList<Card> hand : twoPairHands) {
			assertNull(HandType.isStraightFlush(hand));
		}
		for (ArrayList<Card> hand : threeOfAKindHands) {
			assertNull(HandType.isStraightFlush(hand));
		}
		for (ArrayList<Card> hand : fourOfAKindHands) {
			assertNull(HandType.isStraightFlush(hand));	
		}
		for (ArrayList<Card> hand : straightHands) {
			assertNull(HandType.isStraightFlush(hand));	
		}
		for (ArrayList<Card> hand : flushHands) {
			assertNull(HandType.isStraightFlush(hand));	
		}
		for (ArrayList<Card> hand : fullHouseHands) {
			assertNull(HandType.isStraightFlush(hand));	
		}
		for (ArrayList<Card> hand : straightFlushHands) {
			assertNotNull(HandType.isStraightFlush(hand));	
		}
		for (ArrayList<Card> hand : royalFlushHands) {
			assertNotNull(HandType.isStraightFlush(hand));	
		}
	}
	
	@Test
	public void testRoyalFlush() {
		for (ArrayList<Card> hand : highCardHands) {
			assertNull(HandType.isRoyalFlush(hand));
		}
		for (ArrayList<Card> hand : pairHands) {
			assertNull(HandType.isRoyalFlush(hand));
		}
		for (ArrayList<Card> hand : twoPairHands) {
			assertNull(HandType.isRoyalFlush(hand));
		}
		for (ArrayList<Card> hand : threeOfAKindHands) {
			assertNull(HandType.isRoyalFlush(hand));
		}
		for (ArrayList<Card> hand : fourOfAKindHands) {
			assertNull(HandType.isRoyalFlush(hand));	
		}
		for (ArrayList<Card> hand : straightHands) {
			assertNull(HandType.isRoyalFlush(hand));	
		}
		for (ArrayList<Card> hand : flushHands) {
			assertNull(HandType.isRoyalFlush(hand));	
		}
		for (ArrayList<Card> hand : fullHouseHands) {
			assertNull(HandType.isRoyalFlush(hand));	
		}
		for (ArrayList<Card> hand : straightFlushHands) {
			assertNull(HandType.isRoyalFlush(hand));	
		}
		for (ArrayList<Card> hand : royalFlushHands) {
			assertNotNull(HandType.isRoyalFlush(hand));	
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
