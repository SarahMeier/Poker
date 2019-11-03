package poker.version_graphics.view;


import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;

public class PlayAndPauseButton extends VBox{
	
	private ToggleGroup playerInGame = new ToggleGroup();
	private ToggleButton play = new ToggleButton("need a break");
	
	
	public PlayAndPauseButton() {
		super();
		setToggleButtons();
		play.getStyleClass().add("toggleButton");
		play.setOnAction(e -> toggleText(play));
		this.getChildren().add(play);
	}
	
	public void setToggleButtons() {
		play.setToggleGroup(playerInGame);
		play.setSelected(true);
	}
	
	public boolean getIsActive() {
		return this.play.isSelected();
	}
	private void toggleText(ToggleButton btn) {
		if (btn.isSelected()) {
			btn.setText("need a break");
		}else {
			btn.setText("let's play");
		}
	}
	


}
