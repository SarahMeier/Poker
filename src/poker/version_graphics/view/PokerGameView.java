package poker.version_graphics.view;

import java.util.ArrayList;

import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBase;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;
import poker.version_graphics.PokerGame;
import poker.version_graphics.model.PokerGameModel;

public class PokerGameView {
	private final int PLAYERS_PER_ROW = 3;
	private VBox players;
	private ControlArea controls;
	private HBox playerArea1;
	private HBox playerArea2;
	private Stage stage;
	
	private PokerGameModel model;
	
	public PokerGameView(Stage stage, PokerGameModel model) {
		this.stage = stage;
		this.model = model;
		
		// Create all of the player panes we need, and put them into an HBox
		
		players = new VBox();
		playerArea1 = new HBox();
		playerArea2 = new HBox();
		for (int i = 0; i < PokerGame.NUM_PLAYERS_INIT; i++) {
			PlayerPane pp = new PlayerPane();
			pp.setPlayer(model.getPlayer(i)); // link to player object in the logic
			if(i < PLAYERS_PER_ROW) {
				playerArea1.getChildren().add(pp);
			}else {
				playerArea2.getChildren().add(pp);
			}
		}
		players.getChildren().addAll(playerArea1, playerArea2);
		
		// Create the control area
		controls = new ControlArea();
		controls.linkDeck(model.getDeck()); // link DeckLabel to DeckOfCards in the logic
		
				
		// Put players and controls into a BorderPane
		BorderPane root = new BorderPane();
		//root.setCenter(players);
		root.setCenter(players);
		root.setBottom(controls);

		// Disallow resizing - which is difficult to get right with images
		this.stage.setResizable(false);

        // Create the scene using our layout; then display it
        Scene scene = new Scene(root);
        scene.getStylesheets().add(
                getClass().getResource("poker.css").toExternalForm());
        this.stage.setTitle("Poker Miniproject");
        this.stage.setScene(scene);
        this.stage.show();		
	}
	
	public PlayerPane getPlayerPane(int i) {
		if (i < PLAYERS_PER_ROW) {
			return (PlayerPane) playerArea1.getChildren().get(i);
		}else {
			return (PlayerPane) playerArea2.getChildren().get(i - PLAYERS_PER_ROW);
		}		
	}
	
	public Button getShuffleButton() {
		return controls.btnShuffle;
	}
	
	public Button getDealButton() {
		return controls.btnDeal;
	}
	
	public Button getAddPlayerButton() {
		return controls.btnAddPlayer;
	}
	
	public Button getRemovePlayerButton() {
		return controls.btnRemovePlayer;
	}
	
	public void addPlayerPane(int playerIndex) {
		PlayerPane pp = new PlayerPane();
		pp.setPlayer(model.getPlayer(playerIndex)); // link to player object in the logic
		if(playerIndex < PLAYERS_PER_ROW) {
			playerArea1.getChildren().add(pp);
		}else {
			playerArea2.getChildren().add(pp);
		}
		stage.sizeToScene();
	}

	public void removePlayerPane(int removePlayerIndex) {
		if(removePlayerIndex < PLAYERS_PER_ROW) {
			playerArea1.getChildren().remove(removePlayerIndex);
		}else {
			playerArea2.getChildren().remove(removePlayerIndex - PLAYERS_PER_ROW);
		}
		stage.sizeToScene();
	}
}
