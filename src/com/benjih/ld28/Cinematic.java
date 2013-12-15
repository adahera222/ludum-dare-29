package com.benjih.ld28;

import org.lwjgl.input.Keyboard;

import com.benjih.ld28.components.GameDisplay;
import com.benjih.ld28.components.KeyboardUtils;
import com.benjih.ld28.components.Sprite;

public class Cinematic {
	
	GameDisplay display;
	
	public Cinematic (GameDisplay display) {
		this.display = display;
	}
	
	public void run () throws Exception {
		boolean running = true;
		
		KeyboardUtils.resetKeyboard();
		
		Sprite cinematic = new Sprite(0, 0, "res/opening_1.png");
		long start = display.getTime();
		
		while (running) {
			display.blit();
			
			cinematic.render();
			
			display.closeIfRequested();
			
			while (Keyboard.next()) {
			    if (Keyboard.getEventKeyState()) {
			        if (KeyboardUtils.isEventAction()) {
			        	running = false;
			        }
			    }
			}
			
			if (display.getTime() >= start + 15000) {
				running = false;
			} else if (display.getTime() >= start + 10000) {
				cinematic = new Sprite(0, 0, "res/opening_3.png");
			} else if (display.getTime() >= start + 5000) {
				cinematic = new Sprite(0, 0, "res/opening_2.png");
			}
			
			display.update();
		}
		
	}
	
}