package com.utiles;

import java.util.Random;

import javafx.geometry.Insets;
import javafx.geometry.Point2D;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontSmoothingType;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class GameWindow {

	private static final int STARS_AMOUNT = 300;
	private long timeAtGameStart = System.currentTimeMillis();
	private long gameLength;
	private Canvas canvas;
	private GraphicsContext gc;
	private VBox gameWindowVbox, scoreBarVbox;
	private Text scoreText;
	private Stage primaryStage;
	private int width;
	private int height;
	private Point2D[] stars;

	public GameWindow(Stage sprimaryStage, int WIDTH, int HEIGHT) {

		this.width = WIDTH;
		this.height = HEIGHT;

		canvas = new Canvas(WIDTH, HEIGHT);
		this.gc = canvas.getGraphicsContext2D();
		scoreText = setUpStatisticBarTextOptions();

		scoreBarVbox = new VBox(scoreText);
		scoreBarVbox.setMinHeight(30);
		scoreBarVbox.setPadding(new Insets(0, 30, 0, 0));
		scoreBarVbox.setAlignment(Pos.CENTER_RIGHT);
		scoreBarVbox.setBackground(new Background(new BackgroundFill(Color.DARKRED, null, null)));

		this.gameWindowVbox = new VBox(scoreBarVbox, canvas);
		this.primaryStage = new Stage();
		this.primaryStage.setScene(new Scene(gameWindowVbox));
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

		// repaintScoreBar();

	}

	private void repaintScoreBar() {
		gc.setFill(Color.MIDNIGHTBLUE);
		gc.fillRect(0, 0, Game.WIDTH, 40);
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
		scoreText.setText("Time:  " + String.valueOf(gameLength) + "   Score:  " + score);

	}

	public long getTimeAtGameStart() {
		return timeAtGameStart;
	}

	public long getGameLength() {
		return gameLength;
	}

	private Text setUpStatisticBarTextOptions() {
		Text text = new Text();
		text.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
		text.setTextAlignment(TextAlignment.RIGHT);
		return text;
	}
}
