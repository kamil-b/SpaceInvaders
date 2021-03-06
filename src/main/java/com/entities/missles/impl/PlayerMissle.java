package com.entities.missles.impl;

import com.entities.missles.MissleType;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class PlayerMissle extends Missle {

	public PlayerMissle(int posX, int posY) {
		super(posX, posY);
		this.color = Color.RED;
		this.isFiredByPlayer = true;
	}

	public void show(GraphicsContext gc) {
		gc.setFill(color);
		gc.fillRect(posX, posY, 2, 6);

	}

}
