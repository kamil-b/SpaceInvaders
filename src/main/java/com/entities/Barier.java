package com.entities;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class Barier extends Enemy {

	private final int BARIER_SHOW_TIME = 200;
	private final int ENERGY_CAP = 100;
	
	private boolean isVisiable = false;
	private long timeAfterHit = 0;
	private boolean isHit = false;
	private int energy;
	private int recentPlayerScore;

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
			timeAfterHit = timeAfterHit + (timeAfterHit - System.currentTimeMillis()); // no idea why it works fine... kinda
			gc.drawImage(image, posX, posY);
			if (timeAfterHit > BARIER_SHOW_TIME) {
				isVisiable = false;
				timeAfterHit = 0;
			}
		}
	}

	private void showBarierStatusBar(GraphicsContext gc) {
		gc.setFill(Color.RED);
		gc.fillRect(20, 380, ENERGY_CAP * 2, 10);
		gc.setFill(Color.LIGHTSKYBLUE);
		gc.fillRect(20, 380, energy * 2, 10);
	}

	public boolean isHit() {
		return isHit;
	}

	public void setHit(boolean isHit) {
		this.isHit = isHit;
	}

	public void updateBarierStatus(int score) {
		System.out.println("score" + score);
		int scoreChange = score - recentPlayerScore;
		energy = energy + scoreChange;
		if (isHit) {
			energy = energy - 10 ;
			isHit = false;
		}
		recentPlayerScore = score;
		if(energy > ENERGY_CAP)
			energy = ENERGY_CAP;
	}

}
