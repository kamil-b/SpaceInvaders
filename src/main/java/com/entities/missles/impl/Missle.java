package com.entities.missles.impl;

import com.entities.missles.MissleInterface;
import com.entities.missles.MissleType;
import com.entities.ship.Ship;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public abstract class Missle implements MissleInterface {
	protected MissleType missleType;
	protected Color color;
	protected boolean isFiredByPlayer = false;

	protected Image image;
	protected int posX;
	protected int posY;

	/**
	 * 
	 * @param posX
	 * @param posY
	 */
	public Missle(int posX, int posY) {
		this.posX = posX;
		this.posY = posY;
	}

	public void update(int dx, int dy) {
		posX += dx;
		posY += dy;
	}

	/*
	 * public void show(GraphicsContext gc) {
	 * 
	 * if (isFiredByPlayer) { color = Color.RED; } else { color =
	 * Color.LIGHTGREEN; } gc.setFill(color);
	 * 
	 * if (missleType.equals(MissleType.BIG_MISSLE)) { gc.fillOval(posX, posY,
	 * 10, 10); } else { gc.fillRect(posX, posY, 2, 6); } }
	 */

	public boolean isFiredByPlayer() {
		return isFiredByPlayer;
	}

	public boolean checkColision(Ship ship) {

		Point2D me = new Point2D(posX, posY);
		Point2D notMe = new Point2D(ship.getPosX() + 15, ship.getPosY() + 15);
		if (me.distance(notMe) < 15) {
			return true;
		} else {
			return false;
		}
	}

}
