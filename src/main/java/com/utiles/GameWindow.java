package com.utiles;

import java.util.Random;

import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class GameWindow {

	private static final int STARS_AMOUNT = 300;
	private long timeAtGameStart = System.currentTimeMillis();
	private long gameLength;
	private Canvas canvas;
	private GraphicsContext gc;
	private VBox vbox;
	private Stage primaryStage;
	private int width;
	private int height;
	private Point2D[] stars;

	public GameWindow(Stage sprimaryStage, int WIDTH, int HEIGHT) {

		this.width = WIDTH;
		this.height = HEIGHT;

		canvas = new Canvas(WIDTH, HEIGHT);
		this.gc = canvas.getGraphicsContext2D();
		this.vbox = new VBox(canvas);
		this.primaryStage = new Stage();
		this.primaryStage.setScene(new Scene(vbox));
		canvas.setFocusTraversable(true);
		stars = new Point2D[STARS_AMOUNT];
		for (int i = 0; i < STARS_AMOUNT; i++) {
			stars[i] = new Point2D(new Random().nextInt(width), new Random().nextInt(height));
		}
		repaintBackground();
		primaryStage.show();
	}

	public void repaintBackground() {
		gc.setFill(Color.BLACK);
		gc.fillRect(0, 0, width + 10, height + 10);
		for (int i = 0; i < STARS_AMOUNT; i++) {
			Color color;
			int number = new Random().nextInt(3) + 2;
			switch (number) {
			case 2:
				color = Color.YELLOW;
				break;
			case 3:
				color = Color.GREEN;
				break;
			case 4:
				color = Color.RED;
				break;
			default:
				color = Color.BLUE;
				break;
			}
			gc.setFill(color);
			gc.fillOval(stars[i].getX(), stars[i].getY(), number, number);
		}

	}

	public void updateBackground() {
		for (int i = 0; i < stars.length; i++) {
			stars[i] = stars[i].add(0.2, 0.3);
			if (stars[i].getX() > width + 10 || stars[i].getY() > height + 10) {
				stars[i] = new Point2D(new Random().nextInt(2 * width) - width, -10);
			}

		}
	}

	public GraphicsContext getGc() {
		return gc;
	}

	public Canvas getCanvas() {
		return canvas;
	}

	public void showGameStaticstics(int score) {
		gameLength = (System.currentTimeMillis() - timeAtGameStart) / 1000;
		gc.setFill(Color.RED);
		gc.fillText("Score: " + (score * 10), 500, 390);
		gc.fillText("Time: " + gameLength, 450, 390);
	}
	
	public long getTimeAtGameStart() {
		return timeAtGameStart;
	}
	
	public long getGameLength() {
		return gameLength;
	}

}
