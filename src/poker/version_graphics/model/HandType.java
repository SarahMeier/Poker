package poker.version_graphics.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public enum HandType {
    HighCard, OnePair, TwoPair, ThreeOfAKind, Straight, Flush, FullHouse, FourOfAKind, StraightFlush, RoyalFlush;
    
    /**
     * Determine the value of this hand. Note that this does not
     * account for any tie-breaking
     */
    public static HandType evaluateHand(ArrayList<Card> cards) {
        HandType currentEval = HighCard;
        
        if (isOnePair(cards) != null) currentEval = OnePair;
        if (isTwoPair(cards) != null) currentEval = TwoPair;
        if (isThreeOfAKind(cards) != null) currentEval = ThreeOfAKind;
        if (isStraight(cards) != null) currentEval = Straight;
        if (isFlush(cards) != null) currentEval = Flush;
        if (isFullHouse(cards) != null) currentEval = FullHouse;
        if (isFourOfAKind(cards) != null) currentEval = FourOfAKind;
        if (isStraightFlush(cards) != null) currentEval = StraightFlush;
        if (isRoyalFlush(cards) != null) currentEval = RoyalFlush;
        
        return currentEval;
    }
    
    public static Card evaluateMVC(ArrayList<Card> cards) {
        Card currentMVC = isHighCard(cards);
        
        if (isOnePair(cards) != null) currentMVC = isOnePair(cards);
        if (isTwoPair(cards) != null) currentMVC = isTwoPair(cards);
        if (isThreeOfAKind(cards) != null) currentMVC = isThreeOfAKind(cards);
        if (isStraight(cards) != null) currentMVC = isStraight(cards);
        if (isFlush(cards) != null) currentMVC = isFlush(cards);
        if (isFullHouse(cards) != null) currentMVC = isFullHouse(cards);
        if (isFourOfAKind(cards) != null) currentMVC = isFourOfAKind(cards);
        if (isStraightFlush(cards) != null) currentMVC = isStraightFlush(cards);
        if (isRoyalFlush(cards) != null) currentMVC = isRoyalFlush(cards);
        
        return currentMVC;
    }
    
    public static Card isHighCard(ArrayList<Card> cards) {
    	ArrayList<Card> clonedCards = (ArrayList<Card>) cards.clone();
    	
    	Card mvc = null;
    	
    	Collections.sort(clonedCards);
    	mvc = clonedCards.get(clonedCards.size()-1);
    	
    	return mvc;
    	
    }
    
    public static Card isOnePair(ArrayList<Card> cards) {
        Card mvc = null;
        for (int i = 0; i < cards.size() - 1 && mvc == null; i++) {
            for (int j = i+1; j < cards.size() && mvc == null; j++) {
                if (cards.get(i).getRank() == cards.get(j).getRank()){
                	if(cards.get(i).compareTo(cards.get(j)) > 0){
                		mvc = cards.get(i);
                	} else {
                		mvc = cards.get(j);
                	}
                }
            }
        }
        
        return mvc;
    }
    
    public static Card isTwoPair(ArrayList<Card> cards) {
        // Clone the cards, because we will be altering the list
        ArrayList<Card> clonedCards = (ArrayList<Card>) cards.clone();

        // Find the first pair; if found, remove the cards from the list
        Card mvc = null;
        Card tempMVC = null;
        
        for (int i = 0; i < clonedCards.size()-1 && mvc == null; i++) {
            for (int j = i+1; j < clonedCards.size() && mvc == null; j++) {
                if (clonedCards.get(i).getRank() == clonedCards.get(j).getRank()) {
      
                	if(tempMVC == null && clonedCards.get(i).compareTo(clonedCards.get(j)) > 0){
                		tempMVC = clonedCards.get(i);
                	}
                	if(tempMVC == null && clonedCards.get(i).compareTo(clonedCards.get(j)) < 0){
                		tempMVC = clonedCards.get(j);
                	}            	
                	
                	if (clonedCards.size() < cards.size()) {
                		
                		if(tempMVC != null && clonedCards.get(i).compareTo(clonedCards.get(j)) > 0) {
                			if (tempMVC.compareTo(clonedCards.get(i)) < 0) {
                				mvc = clonedCards.get(i);
                			} else {
                				mvc = tempMVC;
                			}
                		}
                		
                		if(tempMVC != null && clonedCards.get(i).compareTo(clonedCards.get(j)) < 0){
                			if (tempMVC.compareTo(clonedCards.get(j)) < 0) {
                				mvc = clonedCards.get(j);                			
                			} else {
                				mvc = tempMVC;
                			}
                		}
                	
                	}
                    clonedCards.remove(j);  // Remove the later card
                    clonedCards.remove(i);  // Before the earlier one
                    i = 0;
                    j = 0; // Sorry for that!!!!
                }
            }
        }
        return mvc;
    }
    
    public static Card isThreeOfAKind(ArrayList<Card> cards) {
    
       Card mvc = null;;
        for (int i = 0; i < cards.size() - 2 && mvc == null; i++) {
            for (int j = i + 1; j < cards.size() && mvc == null; j++) {
            	for(int k = j + 1; k < cards.size() && mvc == null; k++){
                	if (cards.get(i).getRank() == cards.get(j).getRank() 
                			&& cards.get(j).getRank() == cards.get(k).getRank()) {
                		if(cards.get(i).compareTo(cards.get(j)) > 0 && cards.get(i).compareTo(cards.get(k)) > 0) {
                			mvc = cards.get(i);
                		} else {
                			if(cards.get(j).compareTo(cards.get(k)) > 0) {
                				mvc = cards.get(j);
            				} else {
                				mvc = cards.get(k);
            				}                			
            			}                		
            		}
            	}                	
        	}
    	}
 
        return mvc;   	
    	
    }
    
    public static Card isStraight(ArrayList<Card> cards) {
    	ArrayList<Card> clonedCards = (ArrayList<Card>) cards.clone();
    	
    	Card mvc = null;    	 
    	
    	Collections.sort(clonedCards);
    	
    	
    	if (   clonedCards.get(0).getRank().ordinal() == clonedCards.get(1).getRank().ordinal() -1
    		&& clonedCards.get(0).getRank().ordinal() == clonedCards.get(2).getRank().ordinal() -2
    		&& clonedCards.get(0).getRank().ordinal() == clonedCards.get(3).getRank().ordinal() -3
    		&& clonedCards.get(0).getRank().ordinal() == clonedCards.get(4).getRank().ordinal() -4) {
    			mvc = clonedCards.get(clonedCards.size()-1);
    	}
    	
    	if (mvc == null 
    			&& clonedCards.get(0).getRank().ordinal() == 0 
    			&& clonedCards.get(1).getRank().ordinal() == 1 
    			&& clonedCards.get(2).getRank().ordinal() == 2 
    			&& clonedCards.get(3).getRank().ordinal() == 3 
    			&& clonedCards.get(4).getRank().ordinal() == 12 ) {
    			mvc = clonedCards.get(clonedCards.size()-2);
    	}  	
    	
    	return mvc;
  
    }
    
    public static Card isFlush(ArrayList<Card> cards) {
    	ArrayList<Card> clonedCards = (ArrayList<Card>) cards.clone();
    	
    	Card mvc = null;    	 
    	
    	Collections.sort(clonedCards);
    	    	
    	if (   	clonedCards.get(0).getSuit().ordinal() == clonedCards.get(1).getSuit().ordinal()
    		 && clonedCards.get(0).getSuit().ordinal() == clonedCards.get(2).getSuit().ordinal()
    		 && clonedCards.get(0).getSuit().ordinal() == clonedCards.get(3).getSuit().ordinal()
    		 && clonedCards.get(0).getSuit().ordinal() == clonedCards.get(4).getSuit().ordinal()) {    		
    			mvc = clonedCards.get(clonedCards.size()-1);
    		}
    	
        return mvc;
    }
    
    public static Card isFullHouse(ArrayList<Card> cards) {
        Card mvc = null; 
        int [] cardRanks = new int [cards.size()];        
        
        if(isThreeOfAKind(cards) != null) {
        	for (int i = 0; i < cards.size(); i++) {
        		cardRanks[i] = cards.get(i).getRank().ordinal();     		
        	}
        	Arrays.sort(cardRanks);
        	if (cardRanks[0] == cardRanks[1] && cardRanks[0] == cardRanks[2]) {       	
    			if(cardRanks[3] == cardRanks[4]) {
    				mvc = isThreeOfAKind(cards);
    			} 
    		} else {
    			if (cardRanks[0] == cardRanks[1]) {
    				mvc = isThreeOfAKind(cards);
    			}
    		}
        	
        }
        return mvc;
        
    }
    
    public static Card isFourOfAKind(ArrayList<Card> cards) {
             
    	 Card mvc = null;
         for (int i = 0; i < cards.size() -3 && mvc == null; i++) {
             for (int j = i+1; j < cards.size() && mvc == null; j++) {
             	for(int k = j+1; k < cards.size() && mvc == null; k++){
             		for(int l = k+1; l < cards.size() && mvc == null; l++){
             			if (cards.get(i).getRank() == cards.get(j).getRank() 
             					&& cards.get(i).getRank() == cards.get(k).getRank()
             					&& cards.get(i).getRank() == cards.get(l).getRank()) {
             				if(cards.get(i).compareTo(cards.get(j)) > 0) {
             					mvc = cards.get(i);
             				}else {
             					mvc = cards.get(j);
             				}
             				if(cards.get(k).compareTo(mvc) > 0) {
             					mvc = cards.get(k);
             				}
             				if(cards.get(l).compareTo(mvc) > 0) {
             					mvc = cards.get(l);
             				}
         				}
             		}
             	}
         	}
         }
         return mvc;
    }
    
    public static Card isStraightFlush(ArrayList<Card> cards) {
    	
    	ArrayList<Card> clonedCards = (ArrayList<Card>) cards.clone();
    	
    	Card mvc = null;
        
    	if (isStraight(clonedCards) != null && isFlush(clonedCards) != null) {
    		
        	mvc = isStraight(clonedCards);
    	}
        return mvc;
    }
    
    public static Card isRoyalFlush(ArrayList<Card> cards) {
    	
    	Card mvc = null;
    	int [] cardRanks = new int [cards.size()];   
        
    	if (isStraightFlush(cards) != null) {
    		
    		for (int i = 0; i < cards.size(); i++) {
        		cardRanks[i] = cards.get(i).getRank().ordinal();     		
        	}
        	Arrays.sort(cardRanks);
        	
        	if (cardRanks[3] == 11 && cardRanks[4] == 12) {
        		mvc = isStraight(cards);
        	}
    		
    	}
        return mvc;
    }
    
    public static HandType getHandType(HandType currentEval) {
		return currentEval;
	}

	
    
}
