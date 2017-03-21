package com.entities.missles;

import com.entities.ship.Ship;

import javafx.scene.canvas.GraphicsContext;

public interface MissleInterface {

	boolean isFiredByPlayer();

	void show(GraphicsContext gc);

	void update(int i, int j);

	boolean checkColision(Ship ship);

	int getSpeed();

	int getPosX();
}
