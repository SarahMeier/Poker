package poker.version_graphics;

import javafx.application.Application;
import javafx.stage.Stage;
import poker.version_graphics.controller.PokerGameController;
import poker.version_graphics.model.PokerGameModel;
import poker.version_graphics.view.PokerGameView;

public class PokerGame extends Application {
	public static final int NUM_PLAYERS_MAX = 6;
	public static final int NUM_PLAYERS_INIT = 2;
	public static final int NUM_PLAYERS_MIN = 2;
	PokerGameModel model;
	PokerGameView view;
	PokerGameController controller;
	
    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
    	// Create and initialize the MVC components
    	model = new PokerGameModel();
    	view = new PokerGameView(primaryStage, model);
    	controller = new PokerGameController(model, view);
    }
}
