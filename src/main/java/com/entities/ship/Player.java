package com.entities.ship;

import com.utiles.Game;

import javafx.scene.image.Image;

public class Player extends Ship {
	public static int playerPositionX;
	private Barier barier;

	public Player(int posX, int posY) {
		super(posX, posY);
		image = new Image(getClass().getResourceAsStream("../pictures/ship.png"));
		barier = new Barier(posX, posY);
	}

	@Override
	public String toString() {
		return "Player: " + posX + " " + posY;
	}

	public Barier getBarier() {
		return barier;
	}

	public void update(int dx, int dy) {
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
	}

	
}
