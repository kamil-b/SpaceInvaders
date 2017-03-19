package com.utiles;

import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyEvent;

public class InputMenager {

	private Canvas canvas;
	private boolean left;
	private boolean right;
	private boolean space;

	public InputMenager(Canvas canvas) {
		this.canvas = canvas;
	}

	public void checkIfKeyPressed() {
		canvas.setOnKeyPressed(new EventHandler<KeyEvent>() {

			public void handle(KeyEvent key) {
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
				System.out.println("pressed key:" + key.getCode());
			}
		});
		
		canvas.setOnKeyReleased(new EventHandler<KeyEvent>(){

			public void handle(KeyEvent key) {
				switch(key.getCode()){
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
				System.out.println("released key:" + key.getCode());
			}
			
		}
				);
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
}
