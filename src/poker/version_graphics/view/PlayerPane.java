package poker.version_graphics.view;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import poker.version_graphics.model.Card;
import poker.version_graphics.model.HandType;
import poker.version_graphics.model.Player;

public class PlayerPane extends VBox {
    private Label lblName = new Label();
    private HBox hboxCards = new HBox();
    private Label lblEvaluation = new Label("--");
    private Label lblWinner = new Label("");
	private PlayAndPauseButton playerInGame = new PlayAndPauseButton(); 
	
	
    
    // Link to player object
    private Player player;
    
    public PlayerPane() {
        super(); // Always call super-constructor first !!
        this.getStyleClass().add("player"); // CSS style class
        
        // Add child nodes
        this.getChildren().addAll(lblName, hboxCards, lblEvaluation, lblWinner, playerInGame);
        
        // Add CardLabels for the cards
        for (int i = 0; i < 5; i++) {
            Label lblCard = new CardLabel();
            hboxCards.getChildren().add(lblCard);
        }
        lblWinner.setMinHeight(32);
    }
    
    public boolean getIsPlayerActive() {
    	return this.playerInGame.getIsActive();
    }
    
    public void setPlayer(Player player) {
    	this.player = player;
    	updatePlayerDisplay(); // Immediately display the player information
    }
    
    public void updatePlayerDisplay() {
    	lblName.setText(player.getPlayerName());
    	hideWinner();
    	for (int i = 0; i < Player.HAND_SIZE; i++) {
    		Card card = null;
    		if (player.getCards().size() > i) card = player.getCards().get(i);
    		CardLabel cl = (CardLabel) hboxCards.getChildren().get(i);
    		cl.setCard(card, this);
    		HandType evaluation = player.evaluateHand();
    		if (evaluation != null)
    			lblEvaluation.setText(evaluation.toString());
    		else
    			lblEvaluation.setText("--");
    		
    		if (player.getIsLeader()) {
    			Image image = new Image(this.getClass().getClassLoader().getResourceAsStream("poker/images/money-bag.png"));
    			ImageView imv = new ImageView(image);
    			imv.setFitHeight(24);
    			imv.setPreserveRatio(true);
    			lblWinner.setGraphic(imv);
    			lblWinner.setText("Winner!");
    		} else {
    			lblWinner.setGraphic(null);
    			lblWinner.setText("");
    		}
    	}
    }
    public void hideWinner() {
    	lblEvaluation.setVisible(false);
    	lblWinner.setVisible(false);
    }
    
    public void showWinner() {
    	lblEvaluation.setVisible(true);
    	lblWinner.setVisible(true);
    }
}
