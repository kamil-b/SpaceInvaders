package com.entities.missles.impl;

import com.entities.missles.MissleInterface;
import com.entities.missles.MissleType;
import com.entities.ship.Ship;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public abstract class Missle implements MissleInterface {
	protected static final int NORMAL_SPEED = 5;
	protected static final int SLOW_SPEED = 3;

	protected MissleType missleType;
	protected Color color;
	protected boolean isFiredByPlayer = false;
	protected int speed = NORMAL_SPEED;;

	protected Image image;
	protected int posX;
	protected int posY;
	protected int playerPosXatLaunch;

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

	public int getSpeed() {
		return speed;
	}

	public int getPosX(){
		return posX;
	}

}
