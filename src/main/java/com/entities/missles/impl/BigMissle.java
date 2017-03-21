package com.entities.missles.impl;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class BigMissle extends Missle {

	private int playerPositionXAtLaunch;
	private byte modifier;

	public BigMissle(int posX, int posY, int playerPositionX) {
		super(posX, posY);
		this.color = Color.LIGHTGREEN;
		this.speed = SLOW_SPEED;
		this.damage = 30;
		this.playerPositionXAtLaunch = playerPositionX;
		calculateMisslePath();
	}

	public void show(GraphicsContext gc) {
		gc.setFill(color);
		gc.fillOval(posX, posY, 10, 10);
	}

	@Override
	public void update(int dx, int dy) {

		posX += dx + modifier;
		posY += dy + modifier;
	}

	private void calculateMisslePath() {

		if (posX - playerPositionXAtLaunch > 0) {
			modifier = -1;
		} else {
			modifier = 1;
		}
	}

}