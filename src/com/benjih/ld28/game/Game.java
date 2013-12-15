package com.benjih.ld28.game;

import java.util.ArrayList;
import java.util.Random;

import org.lwjgl.input.Keyboard;

import com.benjih.ld28.components.GLObject;
import com.benjih.ld28.components.GameDisplay;
import com.benjih.ld28.components.Sprite;

public class Game {
	
	GameDisplay display;
	private int total = 1600;
	float speed = 0.5f;
	
	public Game (GameDisplay display) {
		this.display = display;
	}
	
	public void run () {
		Sprite background = new Sprite(0, 0, "res/background.png");
		Sprite background2 = new Sprite(800, 0, "res/background.png");
		Sprite target = new Sprite(14220, 200, "/res/target.png");
		Sprite fireMessage = new Sprite(0, 0, "res/fire.png");
		Sprite bow = new Sprite(0, 100, "res/bow.png");
		Arrow arrow = new Arrow();
		
		beforeFiringLoop(background, fireMessage, bow, arrow);
		int score = flightLoop(background, background2, target, bow, arrow);
		score = finalLoop(background, background2, target, arrow, score);
		
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
			
			if (display.getTime()  >= start + 1) {
				fireMessage.render();
				
				display.closeIfRequested();
				
				if (Keyboard.isKeyDown(Keyboard.KEY_SPACE)) {
					fire = false;
				}
				
			}
			
			display.update();
		}
	}
	
	private int flightLoop(Sprite background, Sprite background2, Sprite target, Sprite bow, Arrow arrow) {
		display.generateDelta();
		
		ArrayList<Coin> coins = createCoins();
		
		boolean play = true;
		int score = 0;
		long shotFired = display.getTime();
		long timeInFlight = 0;
		
		while (timeInFlight < 15 && play) {
			int delta = display.generateDelta();
			
			display.blit();
			
			background.render();
			background2.render();
			target.render();
			bow.render();
			arrow.render();
			
			for(Coin coin : coins) {
				coin.render();
			}
			
			if (Keyboard.isKeyDown(Keyboard.KEY_W)) {
				arrow.increaseHeight(speed, delta);
			} else {
				arrow.decreaseHeight(speed, delta);
			}
			
			display.closeIfRequested();
			
			play = !arrow.hasHitGround();
			
			scrollAndRepeatSprite(background, delta);
			scrollAndRepeatSprite(background2, delta);
			scrollAndHideSprite(target, delta);
			scrollAndHideSprite(bow, delta);
			
			ArrayList<Coin> copyOfCoins = new ArrayList<Coin>(coins);
			for(Coin coin : copyOfCoins) {
				if (arrow.collidesWith(coin)) {
					score = score + coin.getScore();
					coin.hide();
					coins.remove(coin);
				}
				
				scrollAndHideSprite(coin, delta);
			}
			
			display.update();
			
			timeInFlight = (display.getTime() - shotFired) / 1000;
			speed = speed + 0.001f;
		}
		score = (int) (score + (timeInFlight * 60));
		
		return score;
	}
	
	private int finalLoop (GLObject background, GLObject background2, Sprite target, Arrow arrow, int score) {
		boolean play = true;
		display.generateDelta();
		
		long start = display.getTime();
		
		while (play) {
			display.blit();
			int delta = display.generateDelta();
			
			background.render();
			background2.render();
			target.render();
			arrow.render();
			
			if (arrow.getX() + arrow.getWidth() < target.getX()) {
				arrow.setX(arrow.getX() + (int) Math.floor(speed * delta));
			} else {
				if ((arrow.getY() + arrow.getY() + arrow.getHeight()) / 2 < target.getY() ||
					(arrow.getY() + arrow.getY() + arrow.getHeight()) / 2 > target.getY() + 224) {
					arrow.setX(arrow.getX() + (int) Math.floor(speed * delta));
				} else {
					arrow.setX(300);
					if (display.getTime()  >= start + 2000) {
						play = false;
					}
				}
			}
			
			if (arrow.getX() >= 700) {
				play = false;
				score = score + 1000;
			}
			display.closeIfRequested();
			
			display.update();
		}
		
//		int target = 300;
//		int finalPosition = arrow.getY();
//		
//		System.out.println("Trying to calculate bonus score");
//		int differance = Math.abs(finalPosition - target);
//		
//		if (differance < 50 ) {
//			score = score + 1000;
//			System.out.println(1000);
//		} else if (differance < 100) {
//			score = score + 500;
//			System.out.println(500);
//			
//		} else if (differance < 200) {
//			score = score + 250;
//			System.out.println(250);
//			
//		}
		
		return score;
	}
	
	private void scrollAndHideSprite (GLObject sprite, int delta) {
		sprite.setX(sprite.getX() - (int) Math.floor(speed * delta));
		if (sprite.getX() <= -200) {
			sprite.hide();
		}
	}

	private void scrollAndRepeatSprite (GLObject sprite, int delta) {
		sprite.setX(sprite.getX() - (int) Math.floor(speed * delta));
		if (sprite.getX() <= -800) {
			sprite.setX(800 + (sprite.getX() + 800));
			total = total + 800;
		}
	}
	
	private ArrayList<Coin> createCoins () {
		ArrayList<Coin> list = new ArrayList<Coin>();
		Random random = new Random();
		
		list.add(new Coin(random.nextInt(28000), random.nextInt(400)));
		list.add(new Coin(random.nextInt(28000), random.nextInt(400)));
		list.add(new Coin(random.nextInt(28000), random.nextInt(400)));
		list.add(new Coin(random.nextInt(28000), random.nextInt(400)));
		list.add(new Coin(random.nextInt(28000), random.nextInt(400)));
		list.add(new Coin(random.nextInt(28000), random.nextInt(400)));
		list.add(new Coin(random.nextInt(28000), random.nextInt(400)));
		list.add(new Coin(random.nextInt(28000), random.nextInt(400)));
		list.add(new Coin(random.nextInt(28000), random.nextInt(400)));
		list.add(new Coin(random.nextInt(28000), random.nextInt(400)));
		list.add(new Coin(random.nextInt(28000), random.nextInt(400)));
		list.add(new Coin(random.nextInt(28000), random.nextInt(400)));
		list.add(new Coin(random.nextInt(28000), random.nextInt(400)));
		list.add(new Coin(random.nextInt(28000), random.nextInt(400)));
		list.add(new Coin(random.nextInt(28000), random.nextInt(400)));
		list.add(new Coin(random.nextInt(28000), random.nextInt(400)));
		list.add(new Coin(random.nextInt(28000), random.nextInt(400)));
		list.add(new Coin(random.nextInt(28000), random.nextInt(400)));
		list.add(new Coin(random.nextInt(28000), random.nextInt(400)));
		list.add(new Coin(random.nextInt(28000), random.nextInt(400)));
		list.add(new Coin(random.nextInt(28000), random.nextInt(400)));
		list.add(new Coin(random.nextInt(28000), random.nextInt(400)));
		list.add(new Coin(random.nextInt(28000), random.nextInt(400)));
		list.add(new Coin(random.nextInt(28000), random.nextInt(400)));
		
		return list;
	}
	
}
