package poker.version_graphics.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

public enum HandType {
    HighCard, OnePair, TwoPair, ThreeOfAKind, Straight, Flush, FullHouse, FourOfAKind, StraightFlush;
    
    /**
     * Determine the value of this hand. Note that this does not
     * account for any tie-breaking
     */
    public static HandType evaluateHand(ArrayList<Card> cards) {
        HandType currentEval = HighCard;
        
        if (isOnePair(cards)) currentEval = OnePair;
        if (isTwoPair(cards)) currentEval = TwoPair;
        if (isThreeOfAKind(cards)) currentEval = ThreeOfAKind;
        if (isStraight(cards)) currentEval = Straight;
        if (isFlush(cards)) currentEval = Flush;
        if (isFullHouse(cards)) currentEval = FullHouse;
        if (isFourOfAKind(cards)) currentEval = FourOfAKind;
        if (isStraightFlush(cards)) currentEval = StraightFlush;
        
        return currentEval;
    }
    
    public static boolean isOnePair(ArrayList<Card> cards) {
        boolean found = false;
        for (int i = 0; i < cards.size() - 1 && !found; i++) {
            for (int j = i+1; j < cards.size() && !found; j++) {
                if (cards.get(i).getRank() == cards.get(j).getRank()) found = true;
            }
        }
        return found;
    }
    
    public static boolean isTwoPair(ArrayList<Card> cards) {
        // Clone the cards, because we will be altering the list
        ArrayList<Card> clonedCards = (ArrayList<Card>) cards.clone();

        // Find the first pair; if found, remove the cards from the list
        boolean firstPairFound = false;
        for (int i = 0; i < clonedCards.size() - 1 && !firstPairFound; i++) {
            for (int j = i+1; j < clonedCards.size() && !firstPairFound; j++) {
                if (clonedCards.get(i).getRank() == clonedCards.get(j).getRank()) {
                    firstPairFound = true;
                    clonedCards.remove(j);  // Remove the later card
                    clonedCards.remove(i);  // Before the earlier one
                }
            }
        }
        // If a first pair was found, see if there is a second pair
        return firstPairFound && isOnePair(clonedCards);
    }
    
    public static boolean isThreeOfAKind(ArrayList<Card> cards) {
    
       boolean found = false;
        for (int i = 0; i < cards.size() - 1 && !found; i++) {
            for (int j = i + 1; j < cards.size() && !found; j++) {
            	for(int k = j + 1; k < cards.size() && !found; k++){
                	if (cards.get(i).getRank() == cards.get(j).getRank() && cards.get(j).getRank() == cards.get(k).getRank()) 
                	found = true;
            	}
        	}
        }
        return found; 
    	
    	
    }
    
    public static boolean isStraight(ArrayList<Card> cards) {
    	boolean found = false;
    	
    	/* Ansatz über ArrayList:
    	 * ArrayList<Card> clonedCards = (ArrayList<Card>) cards.clone();
    	 * Collections.sort(clonedCards);
    	 * */
    	 
    	
    	int [] cardRanks = new int [cards.size()];
    	
    	for (int i = 0; i < cards.size(); i++) {    		
    		cardRanks[i] = cards.get(i).getRank().ordinal();		
    	}
    	Arrays.sort(cardRanks);
    	
    	if (   cardRanks[0] == cardRanks[1] -1
    		&& cardRanks[0] == cardRanks[2] -2
    		&& cardRanks[0] == cardRanks[3] -3
    		&& cardRanks[0] == cardRanks[4] -4) {
    			found = true;
    	}
    	
    	if (!found && cardRanks[0] == 0 && cardRanks[1] == 1 && cardRanks[2] == 2 && cardRanks[3] == 3 && cardRanks[4] == 12 ) {
    			found = true;
    	}  	
    	
         return found;
  
    }
    
    public static boolean isFlush(ArrayList<Card> cards) {
    	
    	boolean found = false;
        
    	int [] cardSuit = new int [cards.size()];
    	
    	for (int i = 0; i < cards.size(); i++) {    		
    		cardSuit[i] = cards.get(i).getSuit().ordinal();	
    		
    		if (   cardSuit[0] == cardSuit[1]
    			&& cardSuit[0] == cardSuit[2]
    			&& cardSuit[0] == cardSuit[3]
    			&& cardSuit[0] == cardSuit[4])
    			found = true;
    	}
        return found;
    }
    
    public static boolean isFullHouse(ArrayList<Card> cards) {
        boolean found = false; 
        int [] cardRanks = new int [cards.size()];        
        
        if(isOnePair(cards) && isThreeOfAKind(cards)) {
        	for (int i = 0; i < cards.size(); i++) {
        		cardRanks[i] = cards.get(i).getRank().ordinal();
        		Arrays.sort(cardRanks);
        		if (cardRanks[0] != cardRanks[cards.size()]) {       	
        			found = true;
        		}
        	}
        }
        return found;
        
    }
    
    public static boolean isFourOfAKind(ArrayList<Card> cards) {
             
    	 boolean found = false;
         for (int i = 0; i < cards.size() -1 && !found; i++) {
             for (int j = i+1; j < cards.size() -1 && !found; j++) {
             	for(int k = j+1; k < cards.size() -1 && !found; k++){
             		for(int l = k+1; l < cards.size() -1 && !found; l++){
                 	if (cards.get(i).getRank() == cards.get(j).getRank() 
                 		&& cards.get(i).getRank() == cards.get(k).getRank()
                 		&& cards.get(i).getRank() == cards.get(l).getRank())
                 		found = true;
             		}
             	}
         	}
         }
         return found;
    }
    
    public static boolean isStraightFlush(ArrayList<Card> cards) {
    	
    	boolean found = false;
        
    	if (isStraight(cards) == true && isFlush(cards) == true)
    		
    		found = true;
    	
        return found;
    }
}
