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
		Sprite background2 = new Sprite(800, 0, "res/background.png");
		Sprite fireMessage = new Sprite(0, 0, "res/fire.png");
		Sprite bow = new Sprite(0, 100, "res/bow.png");
		Arrow arrow = new Arrow();
		
		boolean fire = true;
		
		long start = display.getTime();
		
		while (fire) {
			display.blit();
			
			background.render();
			bow.render();
			arrow.render();
			
			display.closeIfRequested();
			
			if (display.getTime()  >= start + 3000) {
				fireMessage.render();
				
				display.closeIfRequested();
				
				if (Keyboard.isKeyDown(Keyboard.KEY_SPACE)) {
					fire = false;
				}
						
			}
			
			display.update();
		}
		
		boolean play = true;
		
		while (play) {
			display.blit();
			
			background.render();
			background2.render();
			arrow.render();
			
			if (Keyboard.isKeyDown(Keyboard.KEY_W)) {
				arrow.increaseHeight();
			} else {
				arrow.decreaseHeight();
			}
			
			display.closeIfRequested();
			
			play = !arrow.hasHitGround();
			
			background.setX(background.getX() - 4);
			if (background.getX() == -800) {
				background.setX(800);
			}
			background2.setX(background2.getX() - 4);
			if (background2.getX() == -800) {
				background2.setX(800);
			}
			
			display.update();
		}
		
		
	}
	
}
