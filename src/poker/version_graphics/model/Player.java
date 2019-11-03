package poker.version_graphics.model;

import java.util.ArrayList;

public class Player implements Comparable<Player> {
    public static final int HAND_SIZE = 5;
    
    private final String playerName; // This is the ID
    private final ArrayList<Card> cards = new ArrayList<>();
    private Card mostValuableCard;
    private HandType handType;
    private boolean isLeader;
    
    public Player(String playerName) {
        this.playerName = playerName;
    }
    
    public String getPlayerName() {
        return playerName;
    }
    
    
    public boolean getIsLeader() {
    	return isLeader;
    }
    
    public void setIsLeader(boolean isLeader) {
    	this.isLeader = isLeader;
    }
    
    public Card getMostValuableCard() {
    	return mostValuableCard;
    }

    public ArrayList<Card> getCards() {
        return cards;
    }
    
    public void addCard(Card card) {
        if (cards.size() < HAND_SIZE) cards.add(card);
    }
    
    public void discardHand() {
        cards.clear();
        handType = null;
        mostValuableCard = null;
        isLeader = false;
    }
    
    public int getNumCards() {
        return cards.size();
    }

    /**
     * If the hand has not been evaluated, but does have all cards, 
     * then evaluate it.
     */
    public HandType evaluateHand() {
        if (handType == null && cards.size() == HAND_SIZE) {
            handType = HandType.evaluateHand(cards);
        }
        return handType;
    }
    
    public Card evaluateMostValuableCard() {
    	
    	 if (mostValuableCard == null && cards.size() == HAND_SIZE) {
    		 mostValuableCard = HandType.evaluateMVC(cards);
         }
    	
    	return mostValuableCard;
    }

    /**
     * Hands are compared, based on the evaluation they have.
     */
    @Override
    public int compareTo(Player o) {
    	if(handType.compareTo(o.handType) == 0) {
    		return mostValuableCard.compareTo(o.mostValuableCard);
    	}else {
    		return handType.compareTo(o.handType);
    	}
    }
}
