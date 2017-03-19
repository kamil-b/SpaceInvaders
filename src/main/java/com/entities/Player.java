package com.entities;

import javafx.scene.image.Image;

public class Player extends Ship {


	public Player(int posX, int posY) {
		super(posX, posY);
		image = new Image(getClass().getResourceAsStream("../pictures/ship.png"));
	}

	@Override
	public String toString() {
		return "Player: " + posX + " " + posY ;
	}

	

}
