package com.utiles;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.entities.Barier;
import com.entities.Enemy;
import com.entities.Explosion;
import com.entities.Missle;
import com.entities.Player;

import javafx.animation.AnimationTimer;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Game extends AnimationTimer {

	private static final int ROWS = 4;
	private static final int COLUMNS = 15;
	private int score = 0;
	private GameWindow gameWindow;
	private Explosion explosion;
	private Player player;
	private Enemy[][] enemyList;
	private List<Missle> missles;
	private InputMenager inputManager;

	public static int WIDTH = 700;
	public static int HEIGHT = 400;
	private long timeWhenMissleShotByPlayer;
	private long timeWhenMissleShotByEnemy;
	private List<Explosion> explosions;

	public Game(Stage primaryStage) {
		gameWindow = new GameWindow(primaryStage, WIDTH, HEIGHT);
		player = new Player(WIDTH / 2, 340);

		inputManager = new InputMenager(gameWindow.getCanvas());
		enemyList = new Enemy[ROWS][COLUMNS];
		missles = new ArrayList<Missle>();
		explosions = new ArrayList<Explosion>();
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
		player.getBarier().show(gameWindow.getGc());

		for (int i = 0; i < ROWS; i++) {
			for (int j = 0; j < COLUMNS; j++) {
				if (enemyList[i][j].isAlive())
					enemyList[i][j].show(gameWindow.getGc());
			}
		}

		for (Missle m : missles) {
			m.show(gameWindow.getGc());
		}
		for (int i = 0; i < explosions.size();i++) {
			explosions.get(i).show(gameWindow.getGc());
		}
		
/*		if (explosion != null) {
			explosion.show(gameWindow.getGc());
		}*/
		

	}

	public void update() {
		gameWindow.updateBackground();
		long timeNow = System.currentTimeMillis();
		updatePlayer();
		updateEnemies();
		shotMissleByPlayer();
		shotMissleByEnemy();
		for (Missle m : missles) {
			if (m.isFiredByPlayer())
				m.update(0, -5);
			else {
				m.update(0, 5);
			}
		}
		for (int i = 0; i < explosions.size();i++) {
			if( (timeNow - explosions.get(i).getTimeWhenCreated()) > 500)
			{
				explosions.remove(i);
			}
		}
	}

	private void updatePlayer() {
		inputManager.checkIfKeyPressed();
		if (inputManager.isLeft()) {
			player.update(-5, 0);
		} else if (inputManager.isRight()) {
			player.update(5, 0);
		}
		for (int x = 0; x < missles.size(); x++) {
			if (!missles.get(x).isFiredByPlayer() && missles.get(x).checkColision(player)) {
				player.getBarier().setHit(true);
				player.getBarier().setVisiable(true);
				missles.remove(x);
			}
		}
		player.getBarier().updateBarierStatus();
	}

	private void shotMissleByEnemy() {
		double nextMissle = System.currentTimeMillis() - timeWhenMissleShotByEnemy;
		int row = new Random().nextInt(ROWS);
		int column = new Random().nextInt(COLUMNS);
		if (enemyList[row][column].isAlive()) {
			if (nextMissle > 300) {
				Missle missle = new Missle(enemyList[row][column].getPosX(), enemyList[row][column].getPosY(), false);
				missles.add(missle);
				timeWhenMissleShotByEnemy = System.currentTimeMillis();
			}
		}

	}

	private void shotMissleByPlayer() {
		double nextMissle = System.currentTimeMillis() - timeWhenMissleShotByPlayer;
		if (inputManager.isSpace() && nextMissle > 300) {
			Missle missle = new Missle(player.getPosX()+ 15, player.getPosY(), true);
			missles.add(missle);
			timeWhenMissleShotByPlayer = System.currentTimeMillis();
		}
	}

	private void updateEnemies() {
		int update = (System.currentTimeMillis() / 1000 % 2 == 0) ? 1 : -1;

		for (int i = 0; i < ROWS; i++) {
			for (int j = 0; j < COLUMNS; j++) {
				enemyList[i][j].update(update);
				for (int x = 0; x < missles.size(); x++) {
					if (missles.get(x).isFiredByPlayer() && enemyList[i][j].isAlive()
							&& missles.get(x).checkColision(enemyList[i][j])) {
						explosion = new Explosion(enemyList[i][j].getPosX(), enemyList[i][j].getPosY());
						explosions.add(explosion);
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
					enemyList[i][j] = new Enemy(j * ((WIDTH-35) / COLUMNS) +30, i * 30,
							new Image(getClass().getResourceAsStream("../pictures/red.png")));
				} else if (i == 1) {
					enemyList[i][j] = new Enemy(j * ((WIDTH-35) / COLUMNS) +30 , i * 30,
							new Image(getClass().getResourceAsStream("../pictures/lightred.png")));
				} else if (i == 2) {
					enemyList[i][j] = new Enemy(j * ((WIDTH-35) / COLUMNS) +30 , i * 30,
							new Image(getClass().getResourceAsStream("../pictures/orange2.png")));
				} else {
					enemyList[i][j] = new Enemy(j * ((WIDTH-35) / COLUMNS) +30 , i * 30,
							new Image(getClass().getResourceAsStream("../pictures/green.png")));
				}
			}
		}
	}
}
