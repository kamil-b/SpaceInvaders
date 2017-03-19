package com.entities;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class Barier extends Enemy {

	private boolean isVisiable = false;
	private boolean isHit = false;
	private int energy;

	public Barier(int posX, int posY) {
		super(posX - 5, posY - 5);
		image = new Image(getClass().getResourceAsStream("../pictures/shield.png"));
		energy = 100;
	}

	public boolean isVisiable() {
		return isVisiable;
	}

	public void setVisiable(boolean isVisiable) {
		this.isVisiable = isVisiable;
	}

	public void show(GraphicsContext gc) {
		showBarierStatusBar(gc);
		if (isVisiable == true) {
			gc.drawImage(image, posX, posY);
			isVisiable = false;

		}
	}

	private void showBarierStatusBar(GraphicsContext gc) {
		gc.setFill(Color.RED);
		gc.fillRect(20, 380, 200, 10);
		gc.setFill(Color.LIGHTSKYBLUE);
		gc.fillRect(20, 380, energy*2, 10);
	}
	
	public boolean isHit() {
		return isHit;
	}

	public void setHit(boolean isHit) {
		this.isHit = isHit;
	}
	
	public void updateBarierStatus(){
		if(isHit){
			energy = energy - 10;
			isHit = false;
		}
	}

}
