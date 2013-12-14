package com.benjih.ld28;

import com.benjih.ld28.components.GameDisplay;
import com.benjih.ld28.components.Sprite;

public class DeveloperSplash {
	
	GameDisplay display;
	
	public DeveloperSplash (GameDisplay display) {
		this.display = display;
	}
	
	public void run () throws Exception {
		boolean running = true;
		
		Sprite splash = new Sprite(0, 0, "res/splash.png");
		long start = display.getTime();
		
		while (running) {
			display.blit();
			
			splash.render();
			
			display.closeIfRequested();
			
			display.update();
			
			if (display.getTime() >= start + 3000) {
				running = false;
			}
			
		}
		
	}
	
}