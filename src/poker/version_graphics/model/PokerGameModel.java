package poker.version_graphics.model;

import java.util.ArrayList;

import poker.version_graphics.PokerGame;

public class PokerGameModel {
	private ArrayList<Player> players = new ArrayList<>();
	private DeckOfCards deck;
	
	public PokerGameModel() {
		for (int i = 0; i < PokerGame.NUM_PLAYERS_INIT; i++) {
			players.add(new Player("Player " + i));
		}
		
		deck = new DeckOfCards();
	}
	
	public Player getPlayer(int i) {
		return players.get(i);
	}
	
	public DeckOfCards getDeck() {
		return deck;
	}
	
	/*public void updatePlayer() {
		int playerDiff = PokerGame.NUM_PLAYERS_MAX - players.size();
		for (int i = 0; i < playerDiff; i++) {
			players.add(new Player("Player " + players.size() + 1 + i));
		}
	}*/
	
	public int getPlayersSize(){
		return players.size();
	}
	
	public void addPlayer(String name) {
		players.add(new Player (name));
	}
	
	public void removePlayer(int i) {
		players.remove(i);
	}
}
