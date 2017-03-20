package com.entities.ship;

import javafx.scene.image.Image;

public class Enemy extends Ship {
	
	private boolean isAlive = true;

	public Enemy(int posX, int posY) {
		super(posX, posY);
		image = new Image(getClass().getResourceAsStream("../pictures/green.png"));
		
	}
	
	public Enemy(int posX, int posY, Image image) {
		super(posX, posY);
		this.image = image;
		
	}

	public boolean isAlive() {
		return isAlive;
	}

	public void setAlive(boolean isAlive) {
		this.isAlive = isAlive;
	}
	
}
