package com.entities.ship;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Explosion extends Enemy {

	private long timeWhenCreated;

	public Explosion(int posX, int posY) {
		super(posX, posY);
		image = new Image(getClass().getResourceAsStream("../pictures/explosion.png"));
		timeWhenCreated = System.currentTimeMillis();
	}

	@Override
	public void show(GraphicsContext gc) {

		gc.drawImage(image, posX, posY);

	}

	public long getTimeWhenCreated() {
		return timeWhenCreated;
	}

}
