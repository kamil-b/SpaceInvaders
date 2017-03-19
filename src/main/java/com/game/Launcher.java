package com.game;

import com.utiles.Game;

import javafx.application.Application;
import javafx.stage.Stage;

public class Launcher extends Application {

	Game game;
	
	public static void main(String[] args){
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		game = new Game(primaryStage);
		game.start();
	}
}
