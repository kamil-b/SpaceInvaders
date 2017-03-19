package com.utiles;

import java.util.ArrayList;
import java.util.List;

import com.entities.Enemy;
import com.entities.Explosion;
import com.entities.ImageHelper;
import com.entities.Missle;
import com.entities.Player;

import javafx.animation.AnimationTimer;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Game extends AnimationTimer {

	private static final int ROWS = 4;
	private static final int COLUMNS = 16;
	private int score = 0;
	private GameWindow gameWindow;
	private Explosion explosion;
	private Player player;
	private Enemy[][] enemyList;
	private List<Missle> missles;
	private InputMenager inputManager;

	private int WIDTH = 700;
	private int HEIGHT = 400;
	private long timeWhenMissleShot;

	public Game(Stage primaryStage) {
		gameWindow = new GameWindow(primaryStage, WIDTH, HEIGHT);
		player = new Player(WIDTH / 2, 340);

		inputManager = new InputMenager(gameWindow.getCanvas());
		enemyList = new Enemy[ROWS][COLUMNS];
		missles = new ArrayList<Missle>();

		generateEnemyMatrix();
	}

	@Override
	public void handle(long currentNanoTime) {

		update();
		show();
	}

	public void show() {
		gameWindow.repaintBackground();
		gameWindow.showGameStaticstics(score);
		player.show(gameWindow.getGc());

		for (int i = 0; i < ROWS; i++) {
			for (int j = 0; j < COLUMNS; j++) {
				if (enemyList[i][j].isAlive())
					enemyList[i][j].show(gameWindow.getGc());
			}
		}

		for (Missle m : missles) {
			m.show(gameWindow.getGc());
		}
		if (explosion != null) {
			explosion.show(gameWindow.getGc());
		}
	}

	public void update() {
		inputManager.checkIfKeyPressed();
		if (inputManager.isLeft()) {
			player.update(-5, 0);
		} else if (inputManager.isRight()) {
			player.update(5, 0);
		}

		updateEnemies();
		shotMissle();

		for (Missle m : missles) {
			m.update(0, -5);
		}

	}

	private void shotMissle() {
		double nextMissle = System.currentTimeMillis() - timeWhenMissleShot;
		if (inputManager.isSpace() && nextMissle > 300) {
			Missle missle = new Missle(player.getPosX(), player.getPosY());
			missles.add(missle);
			timeWhenMissleShot = System.currentTimeMillis();
		}
	}

	private void updateEnemies() {
		int update = (System.currentTimeMillis() / 1000 % 2 == 0) ? 1 : -1;

		for (int i = 0; i < ROWS; i++) {
			for (int j = 0; j < COLUMNS; j++) {
				enemyList[i][j].update(update);
				for (int x = 0; x < missles.size(); x++) {
					if (enemyList[i][j].isAlive() && missles.get(x).checkColision(enemyList[i][j])) {
						explosion = new Explosion(enemyList[i][j].getPosX(), enemyList[i][j].getPosY());

						enemyList[i][j].setAlive(false);
						score++;
						missles.remove(x);

					}
				}
			}
		}
	}

	private void generateEnemyMatrix() {
		// ImageHelper imageHelper = new ImageHelper();
		for (int i = 0; i < ROWS; i++) {
			for (int j = 0; j < COLUMNS; j++) {
				if (i == 0) {
					enemyList[i][j] = new Enemy(j * (WIDTH / COLUMNS) + 20, i * 30,
							new Image(getClass().getResourceAsStream("../pictures/red.png")));
				} else if (i == 1) {
					enemyList[i][j] = new Enemy(j * (WIDTH / COLUMNS) + 20, i * 30,
							new Image(getClass().getResourceAsStream("../pictures/lightred.png")));
				} else if (i == 2) {
					enemyList[i][j] = new Enemy(j * (WIDTH / COLUMNS) + 20, i * 30,
							new Image(getClass().getResourceAsStream("../pictures/orange2.png")));
				} else {
					enemyList[i][j] = new Enemy(j * (WIDTH / COLUMNS) + 20, i * 30,
							new Image(getClass().getResourceAsStream("../pictures/green.png")));
				}
			}
		}
	}
}
