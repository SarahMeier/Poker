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
    	switch (handType) {
		case HighCard:
			mostValuableCard = cards.get(0);
			for (int i = 1; i < cards.size(); i++) {
				if(mostValuableCard.compareTo(cards.get(i)) < 0) {
					mostValuableCard = cards.get(i);
				}
			}
			break;
			
		case OnePair:
			
			break;

		default:
			break;
		}
    	
    	return mostValuableCard;
    }

    /**
     * Hands are compared, based on the evaluation they have.
     */
    @Override
    public int compareTo(Player o) {
        return handType.compareTo(o.handType);
    }
}
