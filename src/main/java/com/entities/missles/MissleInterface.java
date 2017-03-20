package com.entities.missles;

import com.entities.ship.Ship;

import javafx.scene.canvas.GraphicsContext;

public interface MissleInterface {

	public boolean isFiredByPlayer();

	public void show(GraphicsContext gc);

	public void update(int i, int j);

	public boolean checkColision(Ship ship);


}
