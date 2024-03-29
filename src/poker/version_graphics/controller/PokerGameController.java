package poker.version_graphics.controller;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import poker.version_graphics.PokerGame;
import poker.version_graphics.model.Card;
import poker.version_graphics.model.DeckOfCards;
import poker.version_graphics.model.Player;
import poker.version_graphics.model.PokerGameModel;
import poker.version_graphics.view.PlayerPane;
import poker.version_graphics.view.PokerGameView;

public class PokerGameController {
	private PokerGameModel model;
	private PokerGameView view;
	
	public PokerGameController(PokerGameModel model, PokerGameView view) {
		this.model = model;
		this.view = view;
		
		view.getShuffleButton().setOnAction( e -> shuffle() );
		view.getDealButton().setOnAction( e -> deal() );
		view.getAddPlayerButton().setOnAction(e -> addPlayer() );		
		view.getRemovePlayerButton().setOnAction(e -> removePlayer() );
	
	}
	


    /**
     * Remove all cards from players hands, and shuffle the deck
     */
    private void shuffle() {
    	    	
    	for (int i = 0; i < model.getPlayersSize(); i++) {
    		Player p = model.getPlayer(i);
    		p.discardHand();
    		PlayerPane pp = view.getPlayerPane(i);
    		pp.updatePlayerDisplay();
    	}

    	model.getDeck().shuffle();
    }
    
    /**
     * Deal each player five cards, then evaluate the two hands
     */
    private void deal() {
    	int playerActive = 0;
    	for (int i = 0; i < model.getPlayersSize(); i++) {
    		PlayerPane pp = view.getPlayerPane(i);
    		if (pp.getIsPlayerActive()) {
				playerActive++;
			}
    	}
    	if (playerActive > 1) {

        	int cardsRequired = playerActive * Player.HAND_SIZE;
        	DeckOfCards deck = model.getDeck();
        	if (cardsRequired <= deck.getCardsRemaining()) {
            	for (int i = 0; i < model.getPlayersSize(); i++) {
            		
        			Player p = model.getPlayer(i);
            		p.discardHand();
                		if (view.getPlayerPane(i).getIsPlayerActive()) {
                		for (int j = 0; j < Player.HAND_SIZE; j++) {
                			Card card = deck.dealCard();
                			p.addCard(card);
                		}
                		p.evaluateHand();
                		p.evaluateMostValuableCard();
                		p.setIsLeader(true);
                		for (int k = 0; k < i; k++) {
                			if (view.getPlayerPane(k).getIsPlayerActive()) {
                				evaluateLeadingHand(p, model.getPlayer(k));
                			}
                		}  
					}            		     		
            	}
            	for (int i = 0; i < model.getPlayersSize(); i++) {
            		PlayerPane pp = view.getPlayerPane(i);
            		pp.updatePlayerDisplay();
            	}
            	
        	} else {
                Alert alert = new Alert(AlertType.ERROR, "Not enough cards - shuffle first");
                alert.showAndWait();
        	}
		}else {
			Alert alert = new Alert(AlertType.ERROR, "Not enough players - add player first");
			alert.showAndWait();
		}    	
    	
    }
    
    private void addPlayer() {
    	int newPlayerIndex = model.getPlayersSize() ;
    	if (newPlayerIndex < PokerGame.NUM_PLAYERS_MAX) {
    		model.addPlayer("Player " + ((newPlayerIndex)+1));
        	view.addPlayerPane(newPlayerIndex);
		}else {
			Alert alert = new Alert(AlertType.ERROR, "Table is full");
			alert.showAndWait();
		}  	
    	
    }
    
    private void removePlayer() {
    	int removePlayerIndex = model.getPlayersSize()-1;
    	if (removePlayerIndex >= PokerGame.NUM_PLAYERS_MIN) {
    		view.removePlayerPane(removePlayerIndex);
        	model.removePlayer(removePlayerIndex);
		}else {
			Alert alert = new Alert(AlertType.ERROR, "Minimum number of players reached");
			alert.showAndWait();
		}
    }
    
    private void evaluateLeadingHand(Player p, Player o) {
    	if(p.compareTo(o) > 0) {
    		o.setIsLeader(false);
    	}else {
    		p.setIsLeader(false);
    	}
    }
}
