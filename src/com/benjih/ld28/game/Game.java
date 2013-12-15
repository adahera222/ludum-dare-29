package com.benjih.ld28.game;

import java.awt.Font;
import java.io.InputStream;
import java.util.ArrayList;

import org.lwjgl.input.Keyboard;
import org.newdawn.slick.Color;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.openal.Audio;
import org.newdawn.slick.openal.AudioLoader;
import org.newdawn.slick.util.ResourceLoader;

import com.benjih.ld28.components.GLObject;
import com.benjih.ld28.components.GameDisplay;
import com.benjih.ld28.components.Sprite;

public class Game {
	
	private GameDisplay display;
	private Sprite background, background2,
					target, fireMessage, 
					bullseyeMessage, bow;
	private Arrow arrow;
	private int total = 1600;
	float speed = 0.5f;
	
	public Game (GameDisplay display) {
		this.display = display;
		background = new Sprite(0, 0, "res/background.png");
		background2 = new Sprite(800, 0, "res/background.png");
		target = new Sprite(14220, 200, "res/target.png");
		fireMessage = new Sprite(0, 0, "res/fire.png");
		bullseyeMessage = new Sprite(0, 0, "res/bullseye.png");
		bow = new Sprite(0, 100, "res/bow.png");
		arrow = new Arrow();
	}
	
	public void run () {
		beforeFiringLoop();
		int score = flightLoop();
		score = finalLoop(score);
		highScore(score);
	}

	private void beforeFiringLoop() {
		boolean fire = true;
		
		long start = display.getTime();
		
		while (fire) {
			display.blit();
			
			background.render();
			bow.render();
			arrow.render();
			
			display.closeIfRequested();
			
			if (display.getTime()  >= start + 1000) {
				fireMessage.render();
				
				display.closeIfRequested();
				
				if (Keyboard.isKeyDown(Keyboard.KEY_SPACE)) {
					fire = false;
				}
				
			}
			
			display.update();
		}
	}
	
	private int flightLoop() {
		display.generateDelta();
		
		ArrayList<Coin> coins = createCoins();
		
		boolean play = true;
		int score = 0;
		long shotFired = display.getTime();
		long timeInFlight = 0;
		
		Audio coinSfx = null;
		try {
			coinSfx = AudioLoader.getAudio("WAV", ResourceLoader.getResourceAsStream("res/coin.wav"));
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
		
		
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
			
			if (Keyboard.isKeyDown(Keyboard.KEY_W) || Keyboard.isKeyDown(Keyboard.KEY_UP)) {
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
					coinSfx.playAsSoundEffect(1.0f, 1.0f, false);
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
	
	private int finalLoop (int score) {
		boolean play = true;
		boolean control = true;
		display.generateDelta();
		
		long start = display.getTime();
		
		while (play) {
			display.blit();
			int delta = display.generateDelta();
			
			background.render();
			background2.render();
			target.render();
			arrow.render();
			
			if (control) {
				if (Keyboard.isKeyDown(Keyboard.KEY_W) || Keyboard.isKeyDown(Keyboard.KEY_UP)) {
					arrow.increaseHeight(speed / 4, delta);
				} else {
					arrow.decreaseHeight(speed / 4, delta);
				}
			}
			
			if (arrow.getX() + arrow.getWidth() < target.getX()) {
				arrow.setX(arrow.getX() + (int) Math.floor(speed / 2 * delta));
			} else {
				if ((arrow.getY() + arrow.getY() + arrow.getHeight()) / 2 < target.getY() ||
					(arrow.getY() + arrow.getY() + arrow.getHeight()) / 2 > target.getY() + 224) {
					arrow.setX(arrow.getX() + (int) Math.floor(speed * delta));
				} else {
					control = false;
					arrow.setX(300);
					if (arrow.getArrowPointY() > target.getY() + 74 &&
							arrow.getArrowPointY() < target.getY() + 74 + 74) {
						bullseyeMessage.render();
					}
					if (display.getTime()  >= start + 2000) {
						play = false;
						score = score + 5000;
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
		
		if (arrow.getArrowPointY() > target.getY() + 74 &&
				arrow.getArrowPointY() < target.getY() + 74 + 74) {
			score = score + 10000;
		}
		
		return score;
	}
	
	private void highScore (int score) {
		boolean play = true;
		
		Sprite scoreScreen = new Sprite(150, 170, "res/score.png");
		
		TrueTypeFont font2 = null;
		try {
			InputStream inputStream = ResourceLoader.getResourceAsStream("res/font.ttf");

			Font awtFont2 = Font.createFont(Font.TRUETYPE_FONT, inputStream);
			awtFont2 = awtFont2.deriveFont(60f); // set font size
			font2 = new TrueTypeFont(awtFont2, true);

		} catch (Exception e) {
			e.printStackTrace();
		}
		while (play) {
			display.blit();
			
			background.render();
			background2.render();
			target.render();
			arrow.render();
			scoreScreen.render();
			
			font2.drawString(400, 450, Integer.toString(score), new Color(108, 81, 38));
			
			if (Keyboard.isKeyDown(Keyboard.KEY_SPACE)) {
				play = false;
			}
			display.closeIfRequested();
			
			display.update();
		}
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
		
		list.add(new Coin(1000, 100));
		list.add(new Coin(1000, 300));
		list.add(new Coin(1000, 500));
		
		list.add(new Coin(2000, 300));
		list.add(new Coin(2200, 300));
		list.add(new Coin(2400, 300));
		list.add(new Coin(2600, 400));
		
		list.add(new Coin(3600, 200));
		list.add(new Coin(3700, 100));
		list.add(new Coin(3700, 300));
		list.add(new Coin(3800, 200));
		
		list.add(new Coin(4700, 200));
		list.add(new Coin(5700, 250));
		
		list.add(new Coin(7000, 300));
		list.add(new Coin(7200, 300));
		list.add(new Coin(7400, 300));
		list.add(new Coin(7600, 300));
		
		list.add(new Coin(9000, 200));
		list.add(new Coin(9200, 200));
		list.add(new Coin(9400, 200));
		list.add(new Coin(9600, 200));
		
		list.add(new Coin(11000, 400));
		list.add(new Coin(11200, 400));
		list.add(new Coin(11400, 400));
		list.add(new Coin(11600, 400));
		
		list.add(new Coin(13000, 200));
		list.add(new Coin(13200, 300));
		list.add(new Coin(13400, 200));
		list.add(new Coin(13600, 300));
		list.add(new Coin(13800, 300));
		list.add(new Coin(14000, 300));
		
		
	
		
		return list;
	}
	
}