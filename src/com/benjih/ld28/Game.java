package com.benjih.ld28;

import org.lwjgl.input.Keyboard;

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
		
		beforeFiringLoop(background, fireMessage, bow, arrow);
		afterFiringLoop(background, background2, bow, arrow);
		
		flightLoop(background, background2, arrow);
		
	}

	private void beforeFiringLoop(Sprite background, Sprite fireMessage, Sprite bow, Arrow arrow) {
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
	}
	
	private void afterFiringLoop(Sprite background, Sprite background2,
			Sprite bow, Arrow arrow) {
		display.generateDelta();
		
		boolean bowGone = false;
		
		while (!bowGone) {
			int delta = display.generateDelta();
			
			display.blit();
			
			background.render();
			background2.render();
			bow.render();
			arrow.render();
			
			scrollSprite(background, delta);
			scrollSprite(background2, delta);
			
			int move = (int) Math.floor(0.5 * delta);
			bow.setX(bow.getX() - move);
			
			if (bow.getX() <= -200) {
				bowGone = true;
			}
			
			display.update();
			
		}
	}
	
	private int flightLoop(Sprite background, Sprite background2, Arrow arrow) {
		display.generateDelta();
		
		Coin theCoin = new Coin(900, 70);
		
		boolean play = true;
		int score = 0;
		long shotFired = display.getTime();
		long timeInFlight = 0;
		while (timeInFlight < 30 && play) {
			int delta = display.generateDelta();
			
			display.blit();
			
			background.render();
			background2.render();
			arrow.render();
			
			theCoin.render();
			
			if (Keyboard.isKeyDown(Keyboard.KEY_W)) {
				arrow.increaseHeight(delta);
			} else {
				arrow.decreaseHeight(delta);
			}
			
			display.closeIfRequested();
			
			play = !arrow.hasHitGround();
			
			scrollSprite(background, delta);
			scrollSprite(background2, delta);
			scrollSprite(theCoin, delta);
			
			display.update();
			
			if (arrow.collidesWith(theCoin)) {
				score = score + theCoin.getScore();
			}
			
			timeInFlight = (display.getTime() - shotFired) / 1000;
		}
		
		return score;
	}
	
	private void scrollSprite(GLObject sprite, int delta) {
		sprite.setX(sprite.getX() - (int) Math.floor(1 * delta));
		if (sprite.getX() <= -800) {
			sprite.setX(800 + (sprite.getX() + 800));
		}
	}
	
}
