package com.entities;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Explosion extends Enemy {

	public Explosion(int posX, int posY) {
		super(posX, posY);
		image = new Image(getClass().getResourceAsStream("../pictures/explosion.png"));

	}

	@Override
	public void show(GraphicsContext gc) {
		
		gc.drawImage(image, posX, posY);

	}
}
