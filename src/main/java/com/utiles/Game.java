package com.utiles;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.entities.missles.MissleInterface;
import com.entities.missles.MissleFactory;
import com.entities.ship.Enemy;
import com.entities.ship.Explosion;
import com.entities.ship.Player;

import javafx.animation.AnimationTimer;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Game extends AnimationTimer {

	private static final int ROWS = 4;
	private static final int COLUMNS = 15;
	private int liveEnemyNumber = ROWS * COLUMNS;
	private int score = 0;
	private GameWindow gameWindow;
	private Explosion explosion;
	private Player player;
	private Enemy[][] enemyList;
	private List<MissleInterface> missles;
	private InputMenager inputManager;
	private int misslesCount = 0;

	public static int WIDTH = 700;
	public static int HEIGHT = 400;
	private long timeWhenMissleShotByPlayer;
	private long timeWhenMissleShotByEnemy;
	private List<Explosion> explosions;

	public Game(Stage primaryStage) {
		gameWindow = new GameWindow(primaryStage, WIDTH, HEIGHT);
		player = new Player(WIDTH / 2, 340);

		inputManager = new InputMenager(gameWindow.getCanvas());
		missles = new ArrayList<MissleInterface>();
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

		for (MissleInterface m : missles) {
			m.show(gameWindow.getGc());
		}
		for (int i = 0; i < explosions.size(); i++) {
			explosions.get(i).show(gameWindow.getGc());
		}

	}

	public void update() {
		gameWindow.updateBackground();
		long timeNow = System.currentTimeMillis();
		updatePlayer();
		updateEnemies();
		shotMissleByPlayer();
		shotMissleByEnemy();
		for (MissleInterface m : missles) {
			if (m.isFiredByPlayer())
				m.update(0, -5);
			else {
				m.update(0, 5);
			}
		}
		for (int i = 0; i < explosions.size(); i++) {
			if ((timeNow - explosions.get(i).getTimeWhenCreated()) > 500) {
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
		player.getBarier().updateBarierStatus(score);

	}

	private void shotMissleByEnemy() {
		double nextMissle = System.currentTimeMillis() - timeWhenMissleShotByEnemy;
		MissleInterface missle;
		int row = new Random().nextInt(ROWS);
		int column = new Random().nextInt(COLUMNS);

		if (enemyList[row][column].isAlive()) {
			if (nextMissle > 300 - calculateDifficulty()) {
				if (misslesCount == 5) {
					missle = MissleFactory.getBigMissle(enemyList[row][column].getPosX(),
							enemyList[row][column].getPosY());
					misslesCount = 0;
				} else {
					missle = MissleFactory.getNormalMissle(enemyList[row][column].getPosX(),
							enemyList[row][column].getPosY());
				}

				missles.add(missle);
				misslesCount++;
				timeWhenMissleShotByEnemy = System.currentTimeMillis();
			}
		}

	}

	private void shotMissleByPlayer() {
		double nextMissle = System.currentTimeMillis() - timeWhenMissleShotByPlayer;
		if (inputManager.isSpace() && nextMissle > 300) {
			MissleInterface missle = MissleFactory.getPlayerMissle(player.getPosX() + 15, player.getPosY());
			missles.add(missle);
			timeWhenMissleShotByPlayer = System.currentTimeMillis();
		}
	}

	private long calculateDifficulty(){
		return gameWindow.getGameLength() ;

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
						liveEnemyNumber--;
						System.out.println(liveEnemyNumber);
					}
				}
			}
		}
		if (liveEnemyNumber == 0)
		{
			this.stop();
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			player.setPosX(WIDTH/2);
			player.setPosX(340);
			 generateEnemyMatrix();
			 liveEnemyNumber = ROWS * COLUMNS;
			 this.start();
		}
	}

	private void generateEnemyMatrix() {
		enemyList = new Enemy[ROWS][COLUMNS];
		// ImageHelper imageHelper = new ImageHelper();
		for (int i = 0; i < ROWS; i++) {
			for (int j = 0; j < COLUMNS; j++) {
				if (i == 0) {
					enemyList[i][j] = new Enemy(j * ((WIDTH - 35) / COLUMNS) + 30, i * 30,
							new Image(getClass().getResourceAsStream("../entities/pictures/red.png")));
				} else if (i == 1) {
					enemyList[i][j] = new Enemy(j * ((WIDTH - 35) / COLUMNS) + 30, i * 30,
							new Image(getClass().getResourceAsStream("../entities/pictures/lightred.png")));
				} else if (i == 2) {
					enemyList[i][j] = new Enemy(j * ((WIDTH - 35) / COLUMNS) + 30, i * 30,
							new Image(getClass().getResourceAsStream("../entities/pictures/orange2.png")));
				} else {
					enemyList[i][j] = new Enemy(j * ((WIDTH - 35) / COLUMNS) + 30, i * 30,
							new Image(getClass().getResourceAsStream("../entities/pictures/green.png")));
				}
			}
		}
	}
}
