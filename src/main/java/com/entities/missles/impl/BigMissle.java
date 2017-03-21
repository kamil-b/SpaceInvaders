package com.entities.missles.impl;

import com.entities.ship.Player;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class BigMissle extends Missle {

	public BigMissle(int posX, int posY) {
		super(posX, posY);
		this.color = Color.LIGHTGREEN;
		this.speed = SLOW_SPEED;
		this.playerPosXatLaunch = Player.playerPositionX;
	}

	public void show(GraphicsContext gc) {
		gc.setFill(color);
		gc.fillOval(posX, posY, 10, 10);
	}
	
	@Override
	public void update(int dx, int dy) {
		
		posX += dx;
		posY += dy;
	}
}