package com.entities.ship;

import com.utiles.Game;
import com.utiles.InputMenager;

import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;

public class Player extends Ship {
	public static int playerPositionX;
	private Barier barier;
	private Canvas canvas;
	private Image image2 ;
	private InputMenager inputManager;
	private int dx, dy;

	public Player(Canvas canvas, int posX, int posY) {
		super(posX, posY);
		this.canvas = canvas;
		inputManager = new InputMenager(this.canvas);
		image = new Image(getClass().getResourceAsStream("../pictures/ship.png"));
		image2 = new Image(getClass().getResourceAsStream("../pictures/explosion.png"));
		barier = new Barier(posX, posY);
	}

	@Override
	public String toString() {
		return "Player: " + posX + " " + posY;
	}

	public Barier getBarier() {
		return barier;
	}

	public void update() {
		inputManager.checkIfKeyPressed();

		if (inputManager.isLeft()) {
			dx = -5;
			dy = 0;
		} else if (inputManager.isRight()) {
			dx = 5;
			dy = 0;
		}
		if (posX >= Game.WIDTH - 30) {
			posX = Game.WIDTH - 30;
		}
		if (posX <= 0) {
			posX = 0;
		}
		posX += dx;
		posY += dy;
		barier.setPosX(posX - 5);
		barier.setPosY(posY - 5);

		playerPositionX = posX;
		dx = 0;
		dy = 0;
	}

	public void explode() {
		image = image2;
		inputManager.stop();
	}

	public boolean isFirePressed() {
		return inputManager.isSpace();
	}
}
