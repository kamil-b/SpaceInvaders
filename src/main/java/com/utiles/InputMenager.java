package com.utiles;

import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyEvent;

public class InputMenager {

	private Canvas canvas;
	private boolean left;
	private boolean right;
	private boolean space;

	private boolean stop = false;

	public InputMenager(Canvas canvas) {
		this.canvas = canvas;
	}

	public void checkIfKeyPressed() {

		canvas.setOnKeyPressed(new EventHandler<KeyEvent>() {

			public void handle(KeyEvent key) {
				if (stop == false) {
					switch (key.getCode()) {
					case A:
						left = true;
						break;
					case D:
						right = true;
						break;
					case SPACE:
						space = true;
					default:
						break;
					}
				}
			}
		});

		canvas.setOnKeyReleased(new EventHandler<KeyEvent>() {

			public void handle(KeyEvent key) {
				if (stop == false) {
					switch (key.getCode()) {
					case A:
						left = false;
						break;
					case D:
						right = false;
						break;
					case SPACE:
						space = false;
					default:
						break;
					}
				}
			}
		});
	}

	public boolean isLeft() {
		return left;
	}

	public boolean isRight() {
		return right;
	}

	public boolean isSpace() {
		return space;
	}

	public void stop() {
		this.stop = true;
		left = false;
		right = false;
		space = false;
	}

}
