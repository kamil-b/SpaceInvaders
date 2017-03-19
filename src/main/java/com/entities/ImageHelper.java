package com.entities;

import javafx.scene.image.Image;

@Deprecated
//TODO
public class ImageHelper {

	public static final Image orangeShip;
	public static final Image greenShip;
	public static final Image redShip;
	public static final Image playerShip;
	public static final Image explosion;
	public static final Image lightRedShip;

	static {

		orangeShip = new Image(ImageHelper.class.getResourceAsStream("../pictures/orange.png"));
		greenShip = new Image(ImageHelper.class.getResourceAsStream("../pictures/green.png"));
		redShip = new Image(ImageHelper.class.getResourceAsStream("../pictures/red.png"));
		playerShip = new Image(ImageHelper.class.getResourceAsStream("../pictures/ship.png"));
		explosion = new Image(ImageHelper.class.getResourceAsStream("../pictures/explosion.png"));
		lightRedShip = new Image(ImageHelper.class.getResourceAsStream("../pictures/lightred.png"));
	}
	// public static InputStream orangeShip2 =
	// Image.class.getClass().getResourceAsStream("../pictures/orange.png");

}
