package com.entities.missles.impl;

import com.entities.missles.MissleType;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class BigMissle extends Missle {

	public BigMissle(int posX, int posY) {
		super(posX, posY);
		this.color = Color.LIGHTGREEN;
	}

	public void show(GraphicsContext gc) {
		gc.setFill(color);
		gc.fillOval(posX, posY, 10, 10);


	}
}