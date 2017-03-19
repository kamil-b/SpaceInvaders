package com.entities;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public abstract class Ship {

	protected Image image;
	protected int posX;
	protected int posY;
	
	public Ship(int posX, int posY) {
		this.posX = posX;
		this.posY = posY;
	}

	public void update(int dx, int dy ){
		posX += dx;
		posY += dy;
	}
	
	public void update(int dx){
		posX = posX + dx;
	}
	


	public void show(GraphicsContext gc) {
		gc.drawImage(image, posX, posY);
	}

	public int getPosX() {
		return posX;
	}

	public int getPosY() {
		return posY;
	}

	public boolean checkColision(Ship ship){
		Point2D me = new Point2D(posX, posY);
		Point2D notMe = new Point2D(ship.getPosX()+15,ship.getPosY()+15);
		if(me.distance(notMe) < 15){
			return true;
		}
		else {
			return false;
		}
	}
	
}
