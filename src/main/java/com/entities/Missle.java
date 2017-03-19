package com.entities;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Missle extends Ship {

	private Color color;
	private boolean isFiredByPlayer = false;

	public Missle(int posX, int posY) {
		super(posX, posY);
	}

	public Missle(int posX, int posY, boolean isFiredByPlayer) {
		super(posX, posY);
		this.isFiredByPlayer = isFiredByPlayer;
		if (isFiredByPlayer) {
			color = Color.RED;
		}else{
			color = Color.LIGHTGREEN;
		}
	}

	@Override
	public void show(GraphicsContext gc) {
		gc.setFill(color);
		gc.fillRect(posX, posY, 3, 6);
	}

	public boolean isFiredByPlayer() {
		return isFiredByPlayer;
	}

}
