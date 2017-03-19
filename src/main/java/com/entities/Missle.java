package com.entities;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Missle extends Ship{

	public Missle(int posX, int posY) {
		super(posX, posY);
	}

	@Override
	public void show(GraphicsContext gc) {
		gc.setFill(Color.RED);
		gc.fillRect(posX, posY, 2, 6);
	}
	
}
