package poker.version_graphics.view;

import java.util.ArrayList;

import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import poker.version_graphics.PokerGame;
import poker.version_graphics.model.PokerGameModel;

public class PokerGameView {
	private VBox players;
	private ControlArea controls;
	private HBox playerArea1;
	private HBox playerArea2;
	
	private PokerGameModel model;
	
	public PokerGameView(Stage stage, PokerGameModel model) {
		this.model = model;
		
		// Create all of the player panes we need, and put them into an HBox
		
		players = new VBox();
		playerArea1 = new HBox();
		playerArea2 = new HBox();
		for (int i = 0; i < PokerGame.NUM_PLAYERS_MAX; i++) {
			PlayerPane pp = new PlayerPane();
			pp.setPlayer(model.getPlayer(i)); // link to player object in the logic
			if(i < 2) {
				playerArea1.getChildren().add(pp);
			}else {
				playerArea2.getChildren().add(pp);
			}
			//players.getChildren().add(pp);
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
		stage.setResizable(false);

        // Create the scene using our layout; then display it
        Scene scene = new Scene(root);
        scene.getStylesheets().add(
                getClass().getResource("poker.css").toExternalForm());
        stage.setTitle("Poker Miniproject");
        stage.setScene(scene);
        stage.show();		
	}
	
	public PlayerPane getPlayerPane(int i) {
		if (i < 2) {
			return (PlayerPane) playerArea1.getChildren().get(i);
		}else {
			return (PlayerPane) playerArea2.getChildren().get(i -2);
		}		
	}
	
	public Button getShuffleButton() {
		return controls.btnShuffle;
	}
	
	public Button getDealButton() {
		return controls.btnDeal;
	}
	
}
