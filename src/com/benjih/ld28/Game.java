package com.benjih.ld28;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;

public class Game {
	
	GameDisplay display;
	
	public Game (GameDisplay display) {
		this.display = display;
	}
	
	public void run () {
		Sprite background = new Sprite(0, 0, "res/background.png");
		Sprite bow = new Sprite(0, 100, "res/bow.png");
		Arrow arrow = new Arrow();
		
		boolean fire = true;
		
		while(fire) {
			
			display.blit();
			
			background.render();
			bow.render();
			arrow.render();
			
			while(Keyboard.next()) {
				if (Keyboard.getEventKeyState()) {
			        if (KeyboardUtils.isEventAction()) {
			        	fire = false;
			        }
				}
			}
			
			display.update();
		}
		
//			if (Keyboard.isKeyDown(Keyboard.KEY_W)) {
//				arrow.increaseHeight();
//			} else {
//				arrow.decreaseHeight();
//			}
//			
//			if (Display.isCloseRequested()) {
//				display.end();
//			}
		
	}
	
}
