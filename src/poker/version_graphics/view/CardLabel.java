package poker.version_graphics.view;


import javafx.animation.Animation;
import javafx.animation.RotateTransition;
import javafx.animation.SequentialTransition;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;
import poker.version_graphics.model.Card;

public class CardLabel extends Label {
	public CardLabel() {
		super();
		this.getStyleClass().add("card");
	}

	public void rotateCard(Card card, PlayerPane pp) {	
		String fileName = cardToFileName(card);
		Image image = new Image(this.getClass().getClassLoader().getResourceAsStream("poker/images/" + fileName));
		ImageView imv = new ImageView(image);
		imv.fitWidthProperty().bind(this.widthProperty());
		imv.fitHeightProperty().bind(this.heightProperty());
		imv.setPreserveRatio(true);
		this.setRotationAxis(Rotate.Y_AXIS);
		this.setRotate(270);
		this.setGraphic(imv);
		RotateTransition rotation = new RotateTransition(Duration.seconds(0.5), this);		
		rotation.setCycleCount(1);
		rotation.setByAngle(90);
		rotation.setOnFinished(e -> pp.showWinner());
		this.setRotationAxis(Rotate.Y_AXIS);
		rotation.play();
	}
	public void setCard(Card card, PlayerPane pp) {
		if (card != null) {	
			RotateTransition rotation = new RotateTransition(Duration.seconds(0.5), this);		
			rotation.setCycleCount(1);
			rotation.setByAngle(90);
			rotation.setOnFinished(e -> rotateCard(card, pp));
			this.setRotationAxis(Rotate.Y_AXIS);
			rotation.play();
		} else {
			Image image = new Image(this.getClass().getClassLoader().getResourceAsStream("poker/images/back2.png"));
			ImageView imv = new ImageView(image);
			imv.fitWidthProperty().bind(this.widthProperty());
			imv.fitHeightProperty().bind(this.heightProperty());
			this.setGraphic(imv);
		}
		
	}
	private String cardToFileName(Card card) {
		String rank = card.getRank().toString();
		String suit = card.getSuit().toString();
		return rank + "_of_" + suit + ".png";
	}

}
